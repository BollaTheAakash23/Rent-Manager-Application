package spring.projects.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.projects.Model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>
{
    
}