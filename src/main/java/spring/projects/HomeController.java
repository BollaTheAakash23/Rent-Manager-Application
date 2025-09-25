package spring.projects;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.projects.Model.Tenant;
import spring.projects.ModelAccess.TenantAccess;

@RestController
@RequestMapping("/home-action")
public class HomeController
{
    private final TenantAccess tenantAccess;

    public HomeController(TenantAccess tenantAccess)
    {
        this.tenantAccess = tenantAccess;
    }

    @RequestMapping("/")
    public String mapToIntermediaries(Model model)
    {
        viewTenants(model);
        
        model.addAttribute("tenant", new Tenant(0, "", 0, 0, 0.0f, 0));

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
    }

    // ADD CODE TO FIRST SEND A TENANT OBJECT THROUGH MODEL AND THEN ADD THE ATTRIBUTES IN ADD TENANT HTML
    // FIGURE OUT THE SAME FOR DELETING TENANT
    
    @RequestMapping("/add-tenant")
    public String addTenant(@ModelAttribute Tenant tenant, Model model)
    {
        tenantAccess.addTenant(tenant);

        return "/home-action/";
    }

    @RequestMapping("/remove-tenant")
    public void deleteTenant(@ModelAttribute Tenant tenant, Model model)
    {
        
    }
}



