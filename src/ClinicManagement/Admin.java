package ClinicManagement;

import javax.print.Doc;
import java.util.ArrayList;

public class Admin {
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Appointment> appointments;
    private ArrayList<MedicalHistory> medicalHistories;
    private ArrayList<Billing> billings;

    Admin(){
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        medicalHistories = new ArrayList<>();
        billings = new ArrayList<>();
    }

    public void AddPatient(Patient newPatient){
        patients.add(newPatient);
    }

    public void AddDoctor(Doctor newDoctor){
        doctors.add(newDoctor);
    }

    public void AddAppointment(Appointment appnt){
        appointments.add(appnt);
    }

    public void AddMedicalHistories(MedicalHistory history){
        medicalHistories.add(history);
    }

    public void AddBillings(Billing bill){
        billings.add(bill);
    }

    public ArrayList<Patient> getAllPatients(){
        return patients;
    }

    public ArrayList<Doctor> getAllDoctors(){
        return doctors;
    }

    public ArrayList<Appointment> getAllAppointments(){
        return appointments;
    }

    public ArrayList<MedicalHistory> getAllHistories(){
        return medicalHistories;
    }

    public ArrayList<Billing> getAllbills(){
        return billings;
    }
}
