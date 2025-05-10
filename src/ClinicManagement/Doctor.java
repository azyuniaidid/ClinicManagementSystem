package ClinicManagement;

public class Doctor extends User {

  private String doc_name;
  private String doc_id;
  private String doc_number;
  private String availabiity;
  private double chgperappt; 

  public Doctor(String username, String password, String doc_name, String doc_id, String doc_number, String availability, double chgperappt){
    super(username, password);
    this.doc_name = doc_name;
    this.doc_id = doc_id;
    this.doc_number = doc_number;
    this.availability = availability;
    this.chgperappt = chgperappt;
  }

  public void viewMedicalHistory(ArrayList[] medicalHistory, String patient_id){
    System.out.println("Medical history for patient id : "+patient_id);
    for (String history : medicalHistory){
      System.out.println(history);
    }
  }

  public void viewAppointment(ArrayList[] appointments, String patient_id){
    System.out.println("Appointments for patient id : "+patient_id);
    for (String appointment : appointments){
      System.out.println(appointment);
    }
  }

  public void updatePatientsVisit(String patient_id, String visitDetail){
    System.out.println("Updating visit details for patient id : "+patient_id);
    System.out.println("New visit details : "+visitDetails);
  }

  public void updatePatientPrescription(String patient_id, String prescriptionDetails){
    System.out.println("Updating prescription for Patient ID: " + patientId);
    System.out.println("New Prescription Details: " + prescriptionDetails);
  }
    
}

  
