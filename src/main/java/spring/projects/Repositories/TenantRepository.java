package spring.projects.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.projects.Model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Integer>
{
    List<Tenant> findByHouseID(int houseID);
}