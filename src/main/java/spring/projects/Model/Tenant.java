package spring.projects.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tenants")
public class Tenant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tenantID;

    private String tenantName;
    private Integer houseID; // 0 - old house | 1 - white house | 2 - new house
    private Integer flatNo;
    private Float rentAmount;
    private Float advanceAmount;
    private Float currentOutstandingAmount;
    private Float overallOutstandingAmount;
    private Long phoneNo;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Payment> payments;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Defaulter> defaults;

    public Tenant()
    {
        tenantID = 0;
        tenantName = "";
        houseID = 0;
        flatNo = 0;
        rentAmount = 0.0f;
        advanceAmount = 0.0f;
        currentOutstandingAmount = 0.0f;
        overallOutstandingAmount = 0.0f;
        phoneNo = Long.MIN_VALUE;
    }

    public Tenant(int tenantID, String tenantName, int houseID, int flatNo, float rentAmount, float advanceAmount,float currentOutstandingAmount,float overallOutstandingAmount,long phoneNo)
    {
        this.tenantID = tenantID;
        this.tenantName = tenantName;
        this.houseID = houseID;
        this.flatNo = flatNo;
        this.rentAmount = rentAmount;
        this.advanceAmount = advanceAmount;
        this.currentOutstandingAmount = currentOutstandingAmount;
        this.overallOutstandingAmount = overallOutstandingAmount;
        this.phoneNo = phoneNo;
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

    // Tenant Name
    public String getTenantName()
    {
        return tenantName;
    }
    public void setTenantName(String tenantName)
    {
        this.tenantName = tenantName;
    }

    // House ID
    public int getHouseID()
    {
        return houseID;
    }
    public void setHouseID(int houseID)
    {
        this.houseID = houseID;
    }

    // Flat No.
    public int getFlatNo()
    {
        return flatNo;
    }
    public void setFlatNo(int flatNo)
    {
        this.flatNo = flatNo;
    }

    // Rent Amount
    public float getRentAmount()
    {
        return rentAmount;
    }
    public void setRentAmount(float rentAmount)
    {
        this.rentAmount = rentAmount;
    }

    // Advance Amount
    public float getAdvanceAmount()
    {
        return advanceAmount;
    }
    public void setAdvanceAmount(float advanceAmount)
    {
        this.advanceAmount = advanceAmount;
    }

    // Current Outstanding Amount
    public float getCurrentOutstandingAmount()
    {
        return currentOutstandingAmount;
    }
    public void setCurrentOutstandingAmount(float currentOutstandingAmount)
    {
        this.currentOutstandingAmount = currentOutstandingAmount;
    }

    // Overall Outstanding Amount
    public float getOverallOutstandingAmount()
    {
        return overallOutstandingAmount;
    }
    public void setOverallOutstandingAmount(float overallOutstandingAmount)
    {
        this.overallOutstandingAmount = overallOutstandingAmount;
    }

    // Phone No.
    public long getPhoneNo()
    {
        return phoneNo;
    }
    public void setPhoneNo(long phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    // Payments List
    public List<Payment> getPayments()
    {
        return payments;
    }
    public void setPayments(List<Payment> payments)
    {
        this.payments = payments;
    }

    // Default list
    public List<Defaulter> getDefaults()
    {
        return defaults;
    }
    public void setDefaults(List<Defaulter> defaults)
    {
        this.defaults = defaults;
    }
}