package ClinicManagement;

public class Billing {
    private String billId;
    private double amount;
    private boolean isPaid;

    public Billing(String billId, double amount, boolean isPaid) {
     this.billId = billId;
     this.amount = amount;
     this.isPaid = isPaid;
   }

    
    public void generateBill(double amount) {
        this.amount = amount;
        System.out.println("Bill generated successfully with amount: RM" + amount);
    }

    
    public void viewBillingInfo() {
        System.out.println("\n=== Billing Information ===");
        System.out.println("Bill ID      : " + billId);
        System.out.printf("Amount       : RM%.2f\n", amount);
        System.out.println("Status       : " + (isPaid ? "Paid" : "Unpaid"));
        System.out.println("============================");
    }

    public void makePayment() {
        if (!isPaid) {
            isPaid = true;
            System.out.println("Payment completed.");
        } else {
            System.out.println("This bill is already marked as paid.");
        }
    }

    // Getters and setters (optional)
}
  

