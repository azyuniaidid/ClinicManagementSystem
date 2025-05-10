package ClinicManagement;

public class Appointment {
  private String appointmentID;
  private Date appointmentDate;
  private String appointmentStatus;

  public Appointment(String appointmentID, Date appointmentDate, String appointmentStatus){
    this.appointmentID = appointmentID;
    this.appointmentDate = appointmentDate;
    this.appointmentStatus = appointmentStatus;
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

  public void displayAppointment(){
    System.out.println("Appointmemnt ID : "+appointmentID);
    System.out.println("Appointmemnt Date : "+appointmentDate);
    System.out.println("Appointmemnt status : "+appointmentStatus);
  }
  
}
  
