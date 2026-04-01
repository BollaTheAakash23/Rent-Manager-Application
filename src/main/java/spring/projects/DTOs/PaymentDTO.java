package spring.projects.DTOs;

import java.time.LocalTime;
import java.time.YearMonth;

public class PaymentDTO
{
    private Integer paymentID;
    
    private YearMonth paymentDate;
    private Integer paymentStatus; // 0 - Not Paid | 1 - Partially Paid | 2 - Paid in Full
    private Float amountPaid;
    private Float amountRemaining;
    private LocalTime paymentTime;

    public PaymentDTO()
    {
        paymentID = 0;
        paymentDate = YearMonth.now();
        paymentStatus = 0;
        amountPaid = 0.0f;
        amountRemaining = 0.0f;
        paymentTime = LocalTime.now();
    }

    public PaymentDTO(int paymentID, YearMonth paymentDate, int paymentStatus, float amountPaid, float amountRemaining, LocalTime paymentTime)
    {
        this.paymentID = paymentID;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.amountPaid = amountPaid;
        this.amountRemaining = amountRemaining;
        this.paymentTime = paymentTime;
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
    public void setAmountRemaining(float amountRemaining)
    {
        this.amountRemaining = amountRemaining;
    }

    // Payment Time
    public LocalTime getPaymentTime()
    {
        return paymentTime;
    }
    public void setPaymentTime(LocalTime paymentTime)
    {
        this.paymentTime = paymentTime;
    }
}