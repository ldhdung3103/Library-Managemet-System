package model;

public class Penalty {

    private int penaltyId;
    private String username;
    private String bookTitle;
    private double amount;
    private String reason;
    private String paymentStatus;

    public int getPenaltyId() {
        return penaltyId;
    }

    public void setPenaltyId(int penaltyId) {
        this.penaltyId = penaltyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}