package spring.projects.DTOs;

public class PaymentStatusDTO
{
    private int tenantID;
    private String tenantName;
    private int flatNo;
    private float rentAmount;
    private float amountRemaining;
    private int houseID;
    private long phoneNo;

    private int paymentStatus;

    public PaymentStatusDTO(int tenantID, String tenantName, int flatNo, float rentAmount, float amountRemaining, int houseID, long phoneNo, int paymentStatus)
    {
        this.tenantID = tenantID;
        this.tenantName = tenantName;
        this.flatNo = flatNo;
        this.rentAmount = rentAmount;
        this.amountRemaining = amountRemaining;
        this.houseID = houseID;
        this.phoneNo = phoneNo;
        this.paymentStatus = paymentStatus;
    }

    // tenantID
    public int getTenantID()
    {
        return tenantID;
    }
    public void setTenantID(int tenantID)
    {
        this.tenantID = tenantID;
    }

    // tenantName
    public String getTenantName()
    {
        return tenantName;
    }
    public void setTenantName(String tenantName)
    {
        this.tenantName = tenantName;
    }

    // flatNo
    public int getFlatNo()
    {
        return flatNo;
    }
    public void setFlatNo(int flatNo)
    {
        this.flatNo = flatNo;
    }

    // rentAmount
    public float getRentAmount()
    {
        return rentAmount;
    }
    public void setRentAmount(float rentAmount)
    {
        this.rentAmount = rentAmount;
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

    // houseID
    public int getHouseID()
    {
        return houseID;
    }
    public void setHouseID(int houseID)
    {
        this.houseID = houseID;
    }

    // phoneNo
    public long getPhoneNo()
    {
        return phoneNo;
    }
    public void setPhoneNo(long phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    // paymentStatus
    public int getPaymentStatus()
    {
        return paymentStatus;
    }
    public void setPaymentStatuses(int paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }
}