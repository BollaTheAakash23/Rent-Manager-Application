package spring.projects.DTOs;

import java.time.YearMonth;

public class DefaulterDTO
{
    // From Entity - Tenant
    private int tenantID;
    private String tenantName;
    private int houseID;
    private int flatNo;
    private float rentAmount;
    private long phoneNo;

    // From Entity - Defaulter
    private int defaulterID;
    private YearMonth yearMonth;
    private float amountRemaining;
    private int paymentStatus;

    public DefaulterDTO()
    {
        tenantID = 0;
        tenantName = "";
        houseID = 0;
        flatNo = 0;
        rentAmount = 0;
        phoneNo = 0;
        defaulterID = 0;
        yearMonth = YearMonth.now().minusMonths(1);
        amountRemaining = 0;
        paymentStatus = 0;
    }

    public DefaulterDTO(int tenantID, String tenantName, int houseID, int flatNo, float rentAmount, long phoneNo, int defaulterID, YearMonth yearMonth, float amountRemaining, int paymentStatus)
    {
        this.tenantID = tenantID;
        this.tenantName = tenantName;
        this.houseID = houseID;
        this.flatNo = flatNo;
        this.rentAmount = rentAmount;
        this.phoneNo = phoneNo;

        this.defaulterID = defaulterID;
        this.yearMonth = yearMonth;
        this.amountRemaining = amountRemaining;
        this.paymentStatus = paymentStatus;
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
}