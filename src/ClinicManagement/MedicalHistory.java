package ClinicManagement;

public class MedicalHistory
{
  private Patient patient;
  private Doctor doctor;
  private String diagnosis;
  private String prescrition;
  private String date;

  MedicalHistory (Patient patient; Doctor doctor, String diagnosis, String prescription, String date)
  {
    this.patient = patient;
    this.doctor = doctor;
    this.diagnosis = diagnosis;
    this.prescription = prescription;
    this.date = date;
  }

  public Patient getPatient()
  {
    return patient;
  }

  public Doctor getDoctor()
  {
    return doctor;
  }
  
  public String getDiagnosis() 
  {
      return diagnosis;
  }

  public String getPrescription() 
  {
      return prescription;
  }

  public String getDate() 
  {
      return date;
  }

  public void displayDetails()
  {
    System.out.println("Medical History Details");
    System.out.println("Diagnosis: " + diagnosis);
    System.out.println("Prescription: " + prescription);
    System.out.println("Date: " + date);
  }
}
