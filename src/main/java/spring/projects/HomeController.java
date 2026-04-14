package spring.projects;

import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.projects.DTOs.DefaulterDTO;
import spring.projects.DTOs.PaymentStatusDTO;
import spring.projects.DTOs.TenantDTO;
import spring.projects.Model.Defaulter;
import spring.projects.Model.Payment;
import spring.projects.Model.Tenant;
import spring.projects.ModelAccess.DefaulterAccess;
import spring.projects.ModelAccess.PaymentAccess;
import spring.projects.ModelAccess.TenantAccess;

@Controller
public class HomeController
{
    private final TenantAccess tenantAccess;
    private final PaymentAccess paymentAccess;
    private final DefaulterAccess defaulterAccess;

    public HomeController(TenantAccess tenantAccess, PaymentAccess paymentAccess, DefaulterAccess defaulterAccess)
    {
        this.tenantAccess = tenantAccess;
        this.paymentAccess = paymentAccess;
        this.defaulterAccess = defaulterAccess;
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
        List<TenantDTO> overallDefaulters = tenantAccess.getOverallDefaulters();
        List<TenantDTO> currentDefaulters = tenantAccess.getCurrentDefaulters();
        //List<PaymentStatusDTO> paymentStatuses = new ArrayList<>();

        model.addAttribute("paymentStatuses", paymentStatuses);
        model.addAttribute("overallDefaulters", overallDefaulters);
        model.addAttribute("currentDefaulters", currentDefaulters);
    }

    // ADD CODE TO FIRST SEND A TENANT OBJECT THROUGH MODEL AND THEN ADD THE ATTRIBUTES IN ADD TENANT HTML
    // FIGURE OUT THE SAME FOR DELETING TENANT
    
    @RequestMapping("/home-action/add-tenant")
    public String addTenant(@ModelAttribute Tenant tenant, Model model)
    {
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
        Tenant tenantToBeUpdated = tenantAccess.findTenantByTenantID(tenant.getTenantID());

        float rentAmountIncreased = (tenantToBeUpdated.getRentAmount() > tenant.getRentAmount()) ? (tenantToBeUpdated.getRentAmount() - tenant.getRentAmount())
                                                                                                 : (tenant.getRentAmount() - tenantToBeUpdated.getRentAmount());

        tenant.setOverallOutstandingAmount(tenant.getOverallOutstandingAmount() + rentAmountIncreased);

        tenantAccess.updateTenant(tenant);

        return "forward:/home-action";
    }

    @RequestMapping("/home-action/add-payment-entry")
    public String addPaymentEntry(@ModelAttribute Payment payment, @RequestParam int tenantID, Model model)
    {
        Tenant tenant = tenantAccess.findTenantByTenantID(tenantID);
        float currentOutstandingAmount = tenant.getCurrentOutstandingAmount();
        float currentTransPaymentAmount = payment.getAmountPaid();

        tenant.setOverallOutstandingAmount(tenant.getOverallOutstandingAmount() - payment.getAmountPaid());

        payment.setTenant(tenant);
        
        if(currentTransPaymentAmount < currentOutstandingAmount)
        {
            currentOutstandingAmount -= currentTransPaymentAmount;
            currentTransPaymentAmount = 0;
        }
        else
        {
            currentTransPaymentAmount -= currentOutstandingAmount;
            currentOutstandingAmount = 0;
        }

        while(currentTransPaymentAmount > 0)
        {
            DefaulterDTO defaulterDTO = defaulterAccess.getEarliestDefaultByTenant(tenant);
            Defaulter defaulter = defaulterAccess.getDefaulterByID(defaulterDTO.getDefaulterID());

            if(defaulter == null) break;

            if(defaulter.getAmountRemaining() > currentTransPaymentAmount)
            {
                defaulter.setAmountRemaining(defaulter.getAmountRemaining() - currentTransPaymentAmount);
                currentTransPaymentAmount = 0;
            }
            else
            {
                currentTransPaymentAmount -= defaulter.getAmountRemaining();
                defaulter.setAmountRemaining(0);
                defaulter.setPaymentStatus(1);
            }
            defaulterAccess.addOrUpdateDefaulter(defaulter);
        }
        payment.setAmountRemaining(currentOutstandingAmount);
        tenant.setCurrentOutstandingAmount(currentOutstandingAmount);

        if(currentOutstandingAmount == 0.0f)
            payment.setPaymentStatus(2);
        else if(currentOutstandingAmount < tenant.getRentAmount())
            payment.setPaymentStatus(1);
        else
            payment.setPaymentStatus(0);

        payment.setPaymentTime(LocalTime.now());

        paymentAccess.addOrUpdatePayment(payment);

        return "forward:/home-action";
    }

    @RequestMapping("/home-action/add-defaulter")
    public String addDefaulter(@ModelAttribute DefaulterDTO defaulter)
    {
        Tenant tenant = tenantAccess.findTenantByTenantID(defaulter.getTenantID());

        Defaulter newDefaulter = new Defaulter();

        newDefaulter.setYearMonth(defaulter.getYearMonth());
        newDefaulter.setAmountRemaining(defaulter.getAmountRemaining());
        newDefaulter.setPaymentStatus(0);
        newDefaulter.setTenant(tenant);

        defaulterAccess.addOrUpdateDefaulter(newDefaulter);

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
    public String handlePageRenderRequests(@RequestParam String whatPage, Model model)
    {
        model.addAttribute("tenant", new Tenant());

        model.addAttribute("tenants", tenantAccess.listTenants());

        model.addAttribute("house0Tenants", tenantAccess.listTenants(0));
        model.addAttribute("house1Tenants", tenantAccess.listTenants(1));
        model.addAttribute("house2Tenants", tenantAccess.listTenants(2));

        model.addAttribute("payment", new Payment());

        model.addAttribute("defaulter", new DefaulterDTO());

        return whatPage;
    }

    @RequestMapping("/renderPageTenantID")
    public String handlePageRenderRequests(@RequestParam String tenantID, @RequestParam String whatPage, Model model)
    {
        Tenant tenantToBeUpdated = tenantAccess.findTenantByTenantID(Integer.parseInt(tenantID));

        model.addAttribute("tenant", tenantToBeUpdated);

        return whatPage;
    }
    
}