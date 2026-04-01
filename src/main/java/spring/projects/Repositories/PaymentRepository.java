package spring.projects.Repositories;

import java.time.YearMonth;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.projects.DTOs.PaymentDTO;
import spring.projects.DTOs.PaymentStatusDTO;
import spring.projects.Model.Payment;
import spring.projects.Model.Tenant;

public interface PaymentRepository extends JpaRepository<Payment, Integer>
{
    List<Payment> findByTenant(Tenant tenant);

    @Query("""
        select new spring.projects.DTOs.PaymentStatusDTO(
                t.tenantID, 
                t.tenantName, 
                t.flatNo, 
                t.rentAmount,
                t.houseID,
                t.phoneNo,
                p.paymentStatus) from
                
                from Tenant t 
            left join t.payments p
                where p.paymentTime = (
                    select max(p1.paymentTime) 
                    from Payment p1 
                    where p1.tenant = t and paymentDate = :yearMonth
                )
                or p.paymentTime is null
            """)
    List<PaymentStatusDTO> listPaymentStatuses(@Param("yearMonth") YearMonth yearMonth);

    @Query("""
        select new spring.projects.DTOs.PaymentDTO(
            p.paymentID,
            p.paymentDate,
            p.paymentStatus,
            p.amountPaid,
            p.amountRemaining,
            p.paymentTime
        ) from
        Payment p
        where p.paymentTime = (
                select max(p1.paymentTime) from
                Payment p1
                where p1.tenant = :tenant and p1.paymentDate = :yearMonth
            )
    """)
    PaymentDTO getLatestPaymentByTenantAndDate(@Param("tenant") Tenant tenant, @Param("yearMonth") YearMonth yearMonth);

}