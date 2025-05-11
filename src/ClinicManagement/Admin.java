package ClinicManagement;

import java.io.*;
import java.util.Date;
import java.util.ArrayList;

public class Admin {
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Appointment> appointments;
    private ArrayList<MedicalHistory> medicalHistories;
    private ArrayList<Billing> billings;

    Admin() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        medicalHistories = new ArrayList<>();
        billings = new ArrayList<>();
    }

    public void addPatient(Patient newPatient) {
        patients.add(newPatient);
    }

    public void addDoctor(Doctor newDoctor) {
        doctors.add(newDoctor);
    }

    public void addAppointment(Appointment appnt) {
        appointments.add(appnt);
    }

    public void addMedicalHistories(MedicalHistory history) {
        medicalHistories.add(history);
    }

    public void addBillings(Billing bill) {
        billings.add(bill);
    }

    public ArrayList<Patient> getAllPatients() {
        return patients;
    }

    public ArrayList<Doctor> getAllDoctors() {
        return doctors;
    }

    public ArrayList<Appointment> getAllAppointments() {
        return appointments;
    }

    public ArrayList<MedicalHistory> getAllHistories() {
        return medicalHistories;
    }

    public ArrayList<Billing> getAllBills() {
        return billings;
    }

    public void dataToTextFiles() {
        saveToFile("patients.txt", patients);
        saveToFile("doctors.txt", doctors);
        saveToFile("appointments.txt", appointments);
        saveToFile("histories.txt", medicalHistories);
        saveToFile("billings.txt", billings);
    }

    private void saveToFile(String fileName, Iterable<?> list, Date date) {
        PrintWriter writer = null;
        File myFile;

        try {
            myFile = new File(fileName);
            writer = new PrintWriter(myFile);

            for (Object obj : list) {
                writer.println(obj.toString());
            }
            writer.println("Last updated: " + date);
            System.out.println("Data written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to " + fileName);
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
