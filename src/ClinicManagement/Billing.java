package ClinicManagement;

import java.util.Date;

public class Billing {
    private String billID;
    private double amount;
    private String payMethod;
    private Date date;
    private Patient patient;
    private Doctor doctor;
    

    public Billing(String billID, double amount, String payMethod, Date date,  Patient patient, Doctor doctor) {
        this.billID = billID;
        this.amount = amount;
        this.payMethod = payMethod;
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getBillID() {
        return billID;
    }

    public double getAmount() {
        return amount;
    }

     public String getPayMethod() {
        return payMethod;
    }

    public Date getDate()
  {
    return date;
  }

    public Patient getPatient()
  {
    return patient;
  }

  public Doctor getDoctor()
  {
    return doctor;
  }

    public void generateBill(String billID, double amount, String payMethod, Date date) {
        this.billID = billID;
        this.amount = amount;
        this.payMethod = payMethod;
        this.date = date;
        System.out.println("Bill generated successfully" );
        
    }

    public void viewBillingInfo() {
        System.out.println("\n=== Billing Information ===");
        System.out.println("Bill ID      : " + billID);
        System.out.printf("Amount       : RM%.2f\n", amount);
        System.out.println("Payment Method       : "+ payMethod);
        System.out.println("Bill generated on       : " + date);
        System.out.println("============================");
    }

    

    @Override
    public String toString() {
        return billID + " " + amount + " " + payMethod + " " + 
                (patient != null ? patient.getPatientID() : "N/A") + " " +
                (patient != null ? patient.getName() : "N/A");
    }
}
