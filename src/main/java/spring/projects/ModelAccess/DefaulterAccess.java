package spring.projects.ModelAccess;

import org.springframework.stereotype.Service;

import spring.projects.DTOs.DefaulterDTO;
import spring.projects.Model.Defaulter;
import spring.projects.Model.Tenant;
import spring.projects.Repositories.DefaulterRepository;

@Service
public class DefaulterAccess
{
    private final DefaulterRepository defaulterRepository;

    public DefaulterAccess(DefaulterRepository defaulterRepository)
    {
        this.defaulterRepository = defaulterRepository;
    }

    public void addOrUpdateDefaulter(Defaulter defaulter)
    {
        defaulterRepository.save(defaulter);
    }

    public Defaulter getDefaulterByID(int defaulterID)
    {
        return defaulterRepository.findByDefaulterID(defaulterID);
    }

    public DefaulterDTO getEarliestDefaultByTenant(Tenant tenant)
    {
        return defaulterRepository.findEarliestDefaultByTenant(tenant);
    }
}