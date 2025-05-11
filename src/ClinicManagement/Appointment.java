package ClinicManagement;

import java.util.Date;

public class Appointment {
  private String appointmentID;
  private Date appointmentDate;
  private String appointmentStatus;
  private Patient patient;
  private Doctor doctor;

    public Appointment(String appointmentID, Date appointmentDate, String appointmentStatus, Patient patient, Doctor doctor){
    this.appointmentID = appointmentID;
    this.appointmentDate = appointmentDate;
    this.appointmentStatus = appointmentStatus;
    this.patient = patient;
    this.doctor = doctor;
  }

  public String getAppointmentID(){

        return appointmentID;
  }

  public Date getAppointmentDate(){

        return appointmentDate;
  }

  public String getAppointmentStatus(){

        return appointmentStatus;
  }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

  public void displayAppointment(){
    System.out.println("Appointmemnt ID : "+appointmentID);
    System.out.println("Appointmemnt Date : "+appointmentDate);
    System.out.println("Appointmemnt status : "+appointmentStatus);
    System.out.println("Patient: " + patient.getName());
    System.out.println("Doctor: " + doctor.getName());
  }

    @Override
    public String toString()
    {

        return appointmentID + " " + patient.getPatientID() + " " + patient.getName() + " " + appointmentDate + " " + appointmentStatus + " " + doctor.getName();
    }
}
  
