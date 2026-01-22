package spring.projects.Model;


import java.time.YearMonth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentID;
    
    private YearMonth paymentDate;
    private int tenantID;
    private int paymentStatus; // 0 - Not Paid | 1 - Partially Paid | 2 - Paid in Full
    private float amountPaid;
    private float amountRemaining;

    public Payment(int paymentID, YearMonth paymentDate, int tenantID, int paymentStatus, float amountPaid, float amountRemaining)
    {
        this.paymentID = paymentID;
        this.paymentDate = paymentDate;
        this.tenantID = tenantID;
        this.paymentStatus = paymentStatus;
        this.amountRemaining = amountRemaining;
    }

    // Payment ID
    public int getPaymentID()
    {
        return paymentID;
    }
    public void setPaymentID(int paymentID)
    {
        this.paymentID = paymentID;
    }

    // Payment Date
    public YearMonth getPaymentDate()
    {
        return paymentDate;
    }
    public void setPaymentDate(YearMonth paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    // Tenant ID
    public int getTenantID()
    {
        return tenantID;
    }
    public void setTenantID(int tenantID)
    {
        this.tenantID = tenantID;
    }

    // Payment Status
    public int getPaymentStatus()
    {
        return paymentStatus;
    }
    public void setPaymentStatus(int paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    // Amount paid
    public float getAmountPaid()
    {
        return amountPaid;
    }
    public void setAmountPaid(float amountPaid)
    {
        this.amountPaid = amountPaid;
    }

    // Amount Remaining
    public float getAmountRemaining()
    {
        return amountRemaining;
    }
    public void setAmountRemaining(int amountRemaining)
    {
        this.amountRemaining = amountRemaining;
    }
}