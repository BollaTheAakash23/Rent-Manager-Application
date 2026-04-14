package spring.projects.Model;

import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "defaulters")
public class Defaulter
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int defaulterID;

    private YearMonth yearMonth;
    private float amountRemaining;
    private int paymentStatus;

    @ManyToOne
    @JoinColumn(name="tenantID")
    @JsonBackReference
    private Tenant tenant;

    public Defaulter()
    {
        defaulterID = 0;
        yearMonth = YearMonth.now().minusMonths(1);
        amountRemaining = 0.0f;
        paymentStatus = 0;
    }

    public Defaulter(int defaulterID, YearMonth yearMonth, float amountRemaining, int paymentStatus)
    {
        this.defaulterID = defaulterID;
        this.yearMonth = yearMonth;
        this.amountRemaining = amountRemaining;
        this.paymentStatus = paymentStatus;
    }

    // Defaulter ID
    public int getDefaulterID()
    {
        return defaulterID;
    }
    public void setDefaulterID(int defaulterID)
    {
        this.defaulterID = defaulterID;
    }

    // Year Month
    public YearMonth getYearMonth()
    {
        return yearMonth;
    }
    public void setYearMonth(YearMonth yearMonth)
    {
        this.yearMonth = yearMonth;
    }

    // Amount Remaining
    public float getAmountRemaining()
    {
        return amountRemaining;
    }
    public void setAmountRemaining(float amountRemaining)
    {
        this.amountRemaining = amountRemaining;
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

    // Tenant
    public Tenant getTenant()
    {
        return tenant;
    }
    public void setTenant(Tenant tenant)
    {
        this.tenant = tenant;
    }
}