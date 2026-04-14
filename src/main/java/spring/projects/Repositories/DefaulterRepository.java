package spring.projects.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.projects.DTOs.DefaulterDTO;
import spring.projects.Model.Defaulter;
import spring.projects.Model.Tenant;

public interface DefaulterRepository extends JpaRepository<Defaulter, Integer>
{

    public Defaulter findByDefaulterID(int defaulterID);

    @Query("""
        select new spring.projects.DTOs.DefaulterDTO(
            t.tenantID,
            t.tenantName,
            t.houseID,
            t.flatNo,
            t.rentAmount,
            t.phoneNo,
            d.defaulterID,
            d.yearMonth,
            d.amountRemaining,
            d.paymentStatus
        )
        from
        Tenant t left join t.defaults d
        where d.defaulterID = (
            select min(d1.defaulterID)
            from
            Defaulter d1
            where d1.tenant = :tenant and d1.amountRemaining > 0
        )
    """)
    DefaulterDTO findEarliestDefaultByTenant(Tenant tenant);
    
    @Query("""
        select new spring.projects.DTOs.DefaulterDTO(
            t.tenantID,
            t.tenantName,
            t.houseID,
            t.flatNo,
            t.rentAmount,
            t.phoneNo,
            d.defaulterID,
            d.yearMonth,
            d.amountRemaining,
            d.paymentStatus
        )
        from
        Tenant t left join t.defaults d
        where d.defaulterID = (
            select max(d1.defaulterID)
            from
            Defaulter d1
            where d1.tenant = :tenant and d1.amountRemaining > 0
        )
    """)
    DefaulterDTO findLatestDefaultByTenant(Tenant tenant);
}