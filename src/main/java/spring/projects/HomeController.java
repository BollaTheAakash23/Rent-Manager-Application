package spring.projects;

import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.projects.DTOs.PaymentDTO;
import spring.projects.DTOs.PaymentStatusDTO;
import spring.projects.DTOs.TenantDTO;
import spring.projects.Model.Payment;
import spring.projects.Model.Tenant;
import spring.projects.ModelAccess.PaymentAccess;
import spring.projects.ModelAccess.TenantAccess;

@Controller
public class HomeController
{
    private final TenantAccess tenantAccess;
    private final PaymentAccess paymentAccess;

    public HomeController(TenantAccess tenantAccess, PaymentAccess paymentAccess)
    {
        this.tenantAccess = tenantAccess;
        this.paymentAccess = paymentAccess;
    }

    @RequestMapping("/home-action")
    public String mapToIntermediaries(Model model)
    {
        viewTenants(model);

        return "home";
    }

    public void viewTenants(Model model)
    {
        List<Tenant> tenants = tenantAccess.listTenants();
        model.addAttribute("tenantList", tenants);

        List<Tenant> tenantsOfHouse0 = tenantAccess.listTenants(0);
        List<Tenant> tenantsOfHouse1 = tenantAccess.listTenants(1);
        List<Tenant> tenantsOfHouse2 = tenantAccess.listTenants(2);

        model.addAttribute("house0Tenants", tenantsOfHouse0);
        model.addAttribute("house1Tenants", tenantsOfHouse1);
        model.addAttribute("house2Tenants", tenantsOfHouse2);

        YearMonth yearMonth = YearMonth.now();

        List<PaymentStatusDTO> paymentStatuses = paymentAccess.getPaymentStatuses(yearMonth);
        //List<PaymentStatusDTO> paymentStatuses = new ArrayList<>();

        model.addAttribute("paymentStatuses", paymentStatuses);
    }

    // ADD CODE TO FIRST SEND A TENANT OBJECT THROUGH MODEL AND THEN ADD THE ATTRIBUTES IN ADD TENANT HTML
    // FIGURE OUT THE SAME FOR DELETING TENANT
    
    @RequestMapping("/home-action/add-tenant")
    public String addTenant(@ModelAttribute Tenant tenant, Model model)
    {
        tenant.setOverallOutstandingAmount(tenant.getRentAmount());

        tenantAccess.addTenant(tenant);

        TenantDTO newTenant = tenantAccess.findTenantByHouseAndFlat(tenant.getHouseID(), tenant.getFlatNo());

        Payment payment = new Payment();
        
        payment.setAmountRemaining(tenant.getRentAmount());
        payment.setTenant(tenantAccess.findTenantByTenantID(newTenant.getTenantID()));

        paymentAccess.addOrUpdatePayment(payment);

        return "forward:/home-action";
    }

    @RequestMapping("/home-action/remove-tenant")
    public String deleteTenant(@ModelAttribute Tenant tenant, Model model)
    {
        int tenantID = tenant.getTenantID();
        tenantAccess.deleteTenant(tenantID);

        System.out.println(tenantID);

        return "forward:/home-action";
    }

    @RequestMapping("/home-action/update-tenant")
    public String updateTenant(@ModelAttribute Tenant tenant, Model model)
    {
        tenantAccess.updateTenant(tenant);

        return "forward:/home-action";
    }

    @RequestMapping("/home-action/add-payment-entry")
    public String addPaymentEntry(@ModelAttribute Payment payment, @RequestParam int tenantID, Model model)
    {
        Tenant tenant = tenantAccess.findTenantByTenantID(tenantID);

        tenant.setOverallOutstandingAmount(tenant.getOverallOutstandingAmount() - payment.getAmountPaid());

        payment.setTenant(tenant);

        PaymentDTO paymentToBeUpdated = paymentAccess.getPaymentByTenant(tenant, YearMonth.now());

        float tenantRentAmountRemaining = paymentToBeUpdated.getAmountRemaining();
        float amountRemainingAfterCurrTrans = tenantRentAmountRemaining - payment.getAmountPaid();
        payment.setAmountRemaining(amountRemainingAfterCurrTrans);

        if(amountRemainingAfterCurrTrans == 0.0f)
            payment.setPaymentStatus(2);
        else if(amountRemainingAfterCurrTrans < tenant.getRentAmount())
            payment.setPaymentStatus(1);
        else
            payment.setPaymentStatus(0);

        payment.setPaymentTime(LocalTime.now());

        paymentAccess.addOrUpdatePayment(payment);

        return "forward:/home-action";
    }

    @RequestMapping("/login")
    public String handleLogin()
    {
        return "login";
    }

    // @RequestMapping("/add-tenant")
    // public String handleAddTenantRequest(Model model)
    // {
    //     model.addAttribute("tenant", new Tenant());
    //     return "addTenant";
    // }

    @RequestMapping("/renderPage")
    public String handlePageRenderRequests(@ModelAttribute Tenant tenant, @RequestParam String whatPage, Model model)
    {
        model.addAttribute("tenant", new Tenant());

        model.addAttribute("tenants", tenantAccess.listTenants());

        model.addAttribute("house0Tenants", tenantAccess.listTenants(0));
        model.addAttribute("house1Tenants", tenantAccess.listTenants(1));
        model.addAttribute("house2Tenants", tenantAccess.listTenants(2));

        model.addAttribute("tenantToBeUpdated", tenant);

        model.addAttribute("payment", new Payment());

        return whatPage;
    }
    
}



