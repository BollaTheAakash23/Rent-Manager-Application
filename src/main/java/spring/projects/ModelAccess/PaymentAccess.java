package spring.projects.ModelAccess;

import java.util.List;

import org.springframework.stereotype.Service;

import spring.projects.Model.Payment;
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
}