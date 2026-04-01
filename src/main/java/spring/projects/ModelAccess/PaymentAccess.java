package spring.projects.ModelAccess;

import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import spring.projects.DTOs.PaymentDTO;
import spring.projects.DTOs.PaymentStatusDTO;
import spring.projects.Model.Payment;
import spring.projects.Model.Tenant;
import spring.projects.Repositories.PaymentRepository;

@Service
public class PaymentAccess
{
    private final PaymentRepository paymentRepository;

    public PaymentAccess(PaymentRepository paymentRepository)
    {
        this.paymentRepository = paymentRepository;
    }

    public void addOrUpdatePayment(Payment payment)
    {
        paymentRepository.save(payment);
    }

    public void deletePayment(Payment payment)
    {
        paymentRepository.delete(payment);
    }

    public List<Payment> listPayments()
    {
        return paymentRepository.findAll();
    }

    // public Payment getPayment(int tenantID, Month monthID, Year year)
    // {
    //     YearMonth paymentDate = YearMonth.of(year.getValue(), monthID.getValue());
    //     return paymentRepository.findByTenantIdAndDate(tenantID, paymentDate);
    // }

    public List<PaymentStatusDTO> getPaymentStatuses(YearMonth yearMonth)
    {
        return paymentRepository.listPaymentStatuses(yearMonth);
    }

    public PaymentDTO getPaymentByTenant(Tenant tenant, YearMonth yearMonth)
    {
        return paymentRepository.getLatestPaymentByTenantAndDate(tenant, yearMonth);
    }
}