package spring.projects.ModelAccess;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.projects.DTOs.TenantDTO;
import spring.projects.Model.Tenant;
import spring.projects.Repositories.TenantRepository;

@Service
public class TenantAccess
{
    private final TenantRepository tenantRepository;

    public TenantAccess(TenantRepository tenantRepository)
    {
        this.tenantRepository = tenantRepository;
    }

    public void addTenant(Tenant tenant)
    {
        tenantRepository.save(tenant);
    }

    public void updateTenant(Tenant tenant)
    {
        tenantRepository.save(tenant);
    }

    public void deleteTenant(int tenantID)
    {
        tenantRepository.deleteById(tenantID);      
    }

    public List<Tenant> listTenants()
    {
        return tenantRepository.findAll();
    }

    public List<Tenant> listTenants(int houseID)
    {
        return tenantRepository.findByHouseID(houseID);
    }

    public Tenant findTenantByTenantID(int tenantID)
    {
        return tenantRepository.findByTenantID(tenantID);
    }

    public TenantDTO findTenantByHouseAndFlat(int houseID, int flatNo)
    {
        return tenantRepository.findByHouseAndFlat(houseID, flatNo);
    }
}