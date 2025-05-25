package ClinicManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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
        Date currentDate = new Date();
        saveToFile("patients.txt", patients, currentDate);
        saveToFile("doctors.txt", doctors, currentDate);
        saveToFile("appointments.txt", appointments, currentDate);
        saveToFile("medicalHistories.txt", medicalHistories, currentDate);
        saveToFile("billings.txt", billings, currentDate);
    }

    private void saveToFile(String fileName, Iterable<?> list, Date date) {
        PrintWriter writer = null;
        File myFile;
        try {
            myFile = new File(fileName);
            writer = new PrintWriter(myFile);
            for (Object objects : list) {
                writer.println(objects.toString());
            }
            writer.println("Last updated: " + date.toString());
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

    public void readDataFromTextFiles() {
        // Read Patient datas
        try {
            Scanner scanner = new Scanner(new File("patients.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.startsWith("Last updated:")) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        Patient p = new Patient(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
                        patients.add(p);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("patients.txt not found.");
        }

        // Read Doctor datas
        try {
            Scanner scanner = new Scanner(new File("doctors.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.startsWith("Last updated:")) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        Doctor d = new Doctor(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));
                        doctors.add(d);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("doctors.txt not found.");
        }

        // Read Appointment datas
        try {
            Scanner scanner = new Scanner(new File("appointments.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.startsWith("Last updated:")) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        Patient patient = getPatientByID(parts[1]);
                        Doctor doctor = getDoctorByID(parts[3]);

                        if (patient != null && doctor != null) {
                            Appointment a = new Appointment(parts[0], new Date(), parts[4], patient, doctor);
                            appointments.add(a);
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("appointments.txt not found.");
        }

        // Read MedicalHistory data
        try {
            Scanner scanner = new Scanner(new File("medicalHistories.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.startsWith("Last updated:")) {
                    String[] parts = line.split(",", 3);
                    if (parts.length == 3) {
                        Patient patient = getPatientByID(parts[1]);
                        Doctor doctor = getDoctorByID(parts[2]);

                        if (patient != null && doctor != null) {
                            MedicalHistory mh = new MedicalHistory(patient, doctor, parts[0], parts[1], parts[2]);
                            medicalHistories.add(mh);
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("medicalHistories.txt not found.");
        }

        // Read Billing data
        try {
            Scanner scanner = new Scanner(new File("billings.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.startsWith("Last updated:")) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        Patient patient = getPatientByID(parts[1]);
                        Doctor doctor = getDoctorByID(parts[2]);

                        if (patient != null && doctor != null) {
                            Billing b = new Billing(parts[0], Double.parseDouble(parts[1]), new Date(), patient, doctor);
                            billings.add(b);
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("billings.txt not found.");
        }
    }

    private Patient getPatientByID(String patientID) {
        for (Patient p : patients) {
            if (p.getPatientID().equals(patientID)) {
                return p;
            }
        }
        return null;
    }

    private Doctor getDoctorByID(String doctorID) {
        for (Doctor d : doctors) {
            if (d.getDoctorID().equals(doctorID)) {
                return d;
            }
        }
        return null;
    }

}
