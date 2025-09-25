package spring.projects.Model;

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

    private int monthID;
    private int tenantID;
    private int paymentStatus; // 0 - Not Paid | 1 - Partially Paid | 2 - Paid in Full
    private float amountRemaining;

    public Payment(int paymentID, int monthID, int tenantID, int paymentStatus, float amountRemaining)
    {
        this.paymentID = paymentID;
        this.monthID = monthID;
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

    // Month ID
    public int getMonthID()
    {
        return monthID;
    }
    public void setMonthID(int monthID)
    {
        this.monthID = monthID;
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