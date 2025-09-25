package spring.projects.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tenants")
public class Tenant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tenantID = 0;

    private String tenantName;
    private int houseID; // 0 - old house | 1 - white house | 2 - new house
    private int flatNo;
    private float rentAmount;
    private long phoneNo;

    public Tenant(int tenantID, String tenantName, int houseID, int flatNo, float rentAmount, long phoneNo)
    {
        this.tenantID = tenantID;
        this.tenantName = tenantName;
        this.houseID = houseID;
        this.flatNo = flatNo;
        this.rentAmount = rentAmount;
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

    // Phone No.
    public long getPhoneNo()
    {
        return phoneNo;
    }
    public void setPhoneNo(long phoneNo)
    {
        this.phoneNo = phoneNo;
    }
}