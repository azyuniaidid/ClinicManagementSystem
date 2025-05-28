package ClinicManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClinicManagementSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Admin admin = new Admin();

        admin.readDataFromTextFiles();

        int choice;

        do {
            System.out.println("\n===== Clinic Management System =====");
            System.out.println("1. Register Patient");
            System.out.println("2. Register Doctor");
            System.out.println("3. Create Appointment");
            System.out.println("4. Add Medical History");
            System.out.println("5. Generate Bill");
            System.out.println("6. View All Data");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: // Register Patient
                    System.out.print("Enter Patient ID: ");
                    String patientID = input.nextLine();
                    System.out.print("Enter Name: ");
                    String patientName = input.nextLine();
                    System.out.print("Enter Phone: ");
                    String patientPhone = input.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = input.nextLine();
                    System.out.print("Enter Age: ");
                    int age = input.nextInt();
                    input.nextLine();
                    Patient newPatient = new Patient(patientName,patientPhone, patientID, gender, age);
                    admin.addPatient(newPatient);
                    admin.dataToTextFiles();
                    System.out.println("Patient registered successfully!");
                    break;

                case 2: // Register Doctor
                    System.out.print("Enter Doctor ID: ");
                    String doctorID = input.nextLine();
                    System.out.print("Enter Name: ");
                    String doctorName = input.nextLine();
                    System.out.print("Enter Phone: ");
                    String doctorPhone = input.nextLine();
                    System.out.print("Enter Specialization: ");
                    String specialize = input.nextLine();
                    System.out.print("Enter Charge Per Appointment(RM): ");
                    Double chgperappt = input.nextDouble();
                    Doctor newDoctor = new Doctor(doctorName, doctorID, doctorPhone, specialize, chgperappt);
                    admin.addDoctor(newDoctor);
                    admin.dataToTextFiles();
                    System.out.println("Doctor registered successfully!");
                    break;

                case 3: // Create Appointment
                    System.out.print("Enter Appointment ID: ");
                    String appointmentID = input.nextLine();
                    ArrayList<Patient> allPatients = admin.getAllPatients();
                    System.out.println("\n--- List of Patients ---");
                    for (int i = 0; i < allPatients.size(); i++) {
                        System.out.println(i + " - " + allPatients.get(i).getName() + " (ID: " + allPatients.get(i).getPatientID() + ")");
                    }
                    System.out.print("Enter Patient Index (0-" + (allPatients.size() - 1) + "): ");
                    int patientIndex = input.nextInt();
                    input.nextLine();

                    ArrayList<Doctor> allDoctors = admin.getAllDoctors();
                    System.out.println("\n--- List of Doctors ---");
                    for (int i = 0; i < allDoctors.size(); i++) {
                        System.out.println(i + " - " + allDoctors.get(i).getName() + " (ID: " + allDoctors.get(i).getDoctorID() + ")");
                    }
                    System.out.print("Enter Doctor Index (0-" + (allDoctors.size() - 1) + "): ");
                    int doctorIndex = input.nextInt();
                    input.nextLine();

                    input.nextLine();
                    Patient selectedPatient = allPatients.get(patientIndex);
                    Doctor selectedDoctor = allDoctors.get(doctorIndex);
                    Appointment appt = new Appointment(appointmentID, new java.util.Date(), "Scheduled", selectedPatient, selectedDoctor);
                    admin.addAppointment(appt);
                    admin.dataToTextFiles();
                    System.out.println("Appointment created!");
                    break;

                case 4: // Add Medical History
                    ArrayList<Patient> allPatient = admin.getAllPatients();
                    if (allPatient.isEmpty()) {
                        System.out.println("No patients available. Please add patients first.");
                        break;
                    }

                    System.out.println("\n--- List of Patients ---");
                    for (int i = 0; i < allPatient.size(); i++) {
                        System.out.println(i + " - " + allPatient.get(i).getName() + " (ID: " + allPatient.get(i).getPatientID() + ")");
                    }
                    System.out.print("Enter Patient Index (0-" + (allPatient.size() - 1) + "): ");
                    int patientIndex2 = input.nextInt();
                    input.nextLine();

                    ArrayList<Doctor> allDoctor = admin.getAllDoctors();
                    if (allDoctor.isEmpty()) {
                        System.out.println("No doctors available. Please add doctors first.");
                        break;
                    }

                    System.out.println("\n--- List of Doctors ---");
                    for (int i = 0; i < allDoctor.size(); i++) {
                        System.out.println(i + " - " + allDoctor.get(i).getName() + " (ID: " + allDoctor.get(i).getDoctorID() + ")");
                    }
                    System.out.print("Enter Doctor Index (0-" + (allDoctor.size() - 1) + "): ");
                    int doctorIndex2 = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter Diagnosis: ");
                    String diagnosis = input.nextLine();
                    System.out.print("Enter Prescription: ");
                    String prescription = input.nextLine();

                    String currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

                    MedicalHistory history = new MedicalHistory(
                            allPatient.get(patientIndex2),
                            allDoctor.get(doctorIndex2),
                            diagnosis,
                            prescription,
                            currentDate
                    );

                    admin.addMedicalHistories(history);
                    admin.dataToTextFiles();
                    System.out.println("Medical history added successfully.");
                    break;


                case 5: // Generate Bill
                    System.out.println("\n--- List of Patients ---");
                    for (int i = 0; i < admin.getAllPatients().size(); i++) {
                        System.out.println(i + " - " + admin.getAllPatients().get(i).getName() + " (ID: " + admin.getAllPatients().get(i).getPatientID() + ")");
                    }
                    System.out.print("Enter Patient Index (0-" + (admin.getAllPatients().size() - 1) + "): ");
                    int patientIndexForBill = input.nextInt();
                    input.nextLine();

                    System.out.println("\n--- List of Doctors ---");
                    for (int i = 0; i < admin.getAllDoctors().size(); i++) {
                        System.out.println(i + " - " + admin.getAllDoctors().get(i).getName() + " (ID: " + admin.getAllDoctors().get(i).getDoctorID() + ")");
                    }
                    System.out.print("Enter Doctor Index (0-" + (admin.getAllDoctors().size() - 1) + "): ");
                    int doctorIndexForBill = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter Billing ID: ");
                    String billID = input.nextLine();

                    // Create Billing and generate it
                    double amount = admin.getAllDoctors().get(doctorIndexForBill).getChgperappt();
                    Billing billing = new Billing(billID, amount, new java.util.Date(), admin.getAllPatients().get(patientIndexForBill), admin.getAllDoctors().get(doctorIndexForBill));
                    billing.generateBill(billID, amount, new java.util.Date());
                    admin.addBillings(billing);
                    admin.dataToTextFiles();
                    System.out.println("Billing generated successfully.");
                    break;


                case 6: // View All Data
                    System.out.println("\n-- Patients --");
                    for (Patient p : admin.getAllPatients()) p.displayDetails();

                    System.out.println("\n-- Doctors --");
                    for (Doctor d : admin.getAllDoctors()) d.displayDetails();

                    System.out.println("\n-- Appointments --");
                    for (Appointment a : admin.getAllAppointments()) a.displayAppointment();

                    System.out.println("\n-- Medical Histories --");
                    for (MedicalHistory mh : admin.getAllHistories()) mh.displayDetails();

                    for (Billing b : admin.getAllBills()) b.viewBillingInfo();
                    break;

                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        input.close();
    }
}
