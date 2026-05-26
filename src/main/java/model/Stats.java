package model;

public class Stats {

    private int totalUsers;
    private int totalBooks;
    private int borrowedBooks;
    private int pendingRequests;
    private int overdueBooks;
    private double totalPenalty;

    public int getTotalUsers() { return totalUsers; }
    public void setTotalUsers(int totalUsers) { this.totalUsers = totalUsers; }

    public int getTotalBooks() { return totalBooks; }
    public void setTotalBooks(int totalBooks) { this.totalBooks = totalBooks; }

    public int getBorrowedBooks() { return borrowedBooks; }
    public void setBorrowedBooks(int borrowedBooks) { this.borrowedBooks = borrowedBooks; }

    public int getPendingRequests() { return pendingRequests; }
    public void setPendingRequests(int pendingRequests) { this.pendingRequests = pendingRequests; }

    public int getOverdueBooks() { return overdueBooks; }
    public void setOverdueBooks(int overdueBooks) { this.overdueBooks = overdueBooks; }

    public double getTotalPenalty() { return totalPenalty; }
    public void setTotalPenalty(double totalPenalty) { this.totalPenalty = totalPenalty; }
}