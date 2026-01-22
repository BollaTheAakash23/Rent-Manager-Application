package spring.projects.Repositories;

import java.time.YearMonth;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.projects.DTOs.PaymentStatusDTO;
import spring.projects.Model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>
{
    //Payment findByTenantIDAndDate(int tenantID, YearMonth yearMonth);

    @Query("""
        select t.tenantID, 
               t.tenantName, 
               t.flatNo, 
               t.rentAmount,
               t.houseID,
               t.phoneNo,
               p.paymentStatus from Tenant t 
        left join Payment p
            on t.tenantID = p.tenantID      
    """)
    List<PaymentStatusDTO> listPaymentStatuses(YearMonth yearMonth);
}