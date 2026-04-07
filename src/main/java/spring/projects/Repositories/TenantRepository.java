package spring.projects.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.projects.DTOs.TenantDTO;
import spring.projects.Model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Integer>
{
    List<Tenant> findByHouseID(int houseID);
    Tenant findByTenantID(int tenantID);

    @Query("""
        select new spring.projects.DTOs.TenantDTO(
            t.tenantID,
            t.tenantName,
            t.houseID,
            t.flatNo,
            t.rentAmount,
            t.phoneNo,
            t.overallOutstandingAmount
        )
        from Tenant t
          where houseID = :houseID and flatNo = :flatNo
    """)
    TenantDTO findByHouseAndFlat(int houseID, int flatNo);

    @Query("""
        select new spring.projects.DTOs.TenantDTO(
                t.tenantID,
                t.tenantName,
                t.houseID,
                t.flatNo,
                t.rentAmount,
                t.phoneNo,
                t.overallOutstandingAmount
            )
            from Tenant t
            where t.overallOutstandingAmount <> 0
    """)
    List<TenantDTO> listOverallDefaulters();
}