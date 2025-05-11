package ClinicManagement;

public class Billing {
    private String billID;
    private double amount;
    private boolean isPaid;
    private Patient patient;
    private Doctor doctor;

    public Billing(String billID, double amount, boolean isPaid) {
     this.billID = billID;
     this.amount = amount;
     this.isPaid = isPaid;
     this.patient = patient;
     this.doctor = doctor;
   }

    public String getBillID(){

        return billID;
  }

    public double getAmount () {
        amount = doctor.getChgperappt;
        return amount;
    }

    
    public void generateBill(double amount) {
        this.amount = amount;
        System.out.println("Bill generated successfully with amount: RM" + amount);
    }

    
    public void viewBillingInfo() {
        System.out.println("\n=== Billing Information ===");
        System.out.println("Bill ID      : " + billID);
        System.out.printf("Amount       : RM%.2f\n", amount);
        System.out.println("Status       : " + (isPaid ? "Paid" : "Unpaid"));
        System.out.println("Patient Name  : " + patient.getName());
        System.out.println("============================");
    }

    public void makePayment() {
        if (!isPaid) {
            isPaid = true;
            System.out.println("Payment completed for " + patient.getName() + ".");
        } else {
            System.out.println("This bill is already marked as paid.");
        }
    }

    @Override
    public String toString()
    {
        return billId + " " + amount + " " + isPaid + " " + patient.getPatientID() + " " + patient.getName();
    }
}
  

