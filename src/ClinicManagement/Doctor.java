package ClinicManagement;

public class Doctor extends User {

  private String doctorID;
  private String specialization;
  private double chgperappt; 

  Doctor(String name,String doctorID,String phoneNumber,String specialization,double chgperappt )
    {
        super(name, phoneNumber);
        this.doctorID = doctorID;
        this.specialization = specialization;
        this.chgperappt = chgperappt;
    }

    public String getdoctorID()
    {
        return doctorID;
    }

    public String getSpecialization()
    {
        return specialization;
    }

    public double getChgperappt()
    {
        return chgperappt;
    }

    @Override
    public void displayDetails()
    {
        System.out.println("Doctor ID : " + doctorID);
        System.out.println("Doctor Name : " + name);
        System.out.println("Doctor Phone Number : " + phoneNumber);
        System.out.println("Doctor Specialization : " + specialization);
        System.out.println("Charge per appointment : " + chgperappt);

    }

    @Override
    public String toString()
    {

        return doctorID + " " + name + " " + phoneNumber + " " + specialization + " " + chgperappt ;
    }
    
}

  
