package ClinicManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Doctor extends User {

  private String doctorID;
  private String specialization;
  private double chgperappt; 
  private final ArrayList<Doctor> doctors =new ArrayList<>();
  private final String filePath = "doctor.txt";

  Doctor(String name,String doctorID,String phoneNumber,String specialization,double chgperappt )
    {
        super(name, phoneNumber);
        this.doctorID = doctorID;
        this.specialization = specialization;
        this.chgperappt = chgperappt;
        System.out.println("Doctor instance has created");
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public double getChgperappt() {
        return chgperappt;
    }

    public void setChgperappt(double chgperappt) {
        this.chgperappt = chgperappt;
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
    public String toString(){

        return doctorID + " " + name + " " + phoneNumber + " " + specialization + " " + chgperappt ;
    }
    
    public void addDoctor(Doctor doctor){
        
        doctors.add(doctor);
        System.out.println("Doctor added to the array");
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))){
            for (Doctor Doctor : doctors){
                writer.println(doctor);
            }
            System.out.println("Data writtern to doctor.txt");
        }catch (IOException e){
            System.out.println("Error writing to file");
            e.printStackTrace();
        }
    }
    
    public ArrayList<Doctor> getDoctors(){
        return doctors;
    }
      
}

  
