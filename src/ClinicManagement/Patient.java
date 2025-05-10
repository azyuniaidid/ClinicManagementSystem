package ClinicManagement;

public class Patient extends User 
{

    Patient(String patientID,String patientName,String patientNumber,String gender,int age)
    {
        super(patientID, patientName, patientNumber, gender, age);
    }


    @Override
    public void displayDetails()
    {
        System.out.println("Patient ID : " + patientID);
        System.out.println("Patient Name : " + patientName);
        System.out.println("Patient Phone Number : " + patientNumber);
        System.out.println("Patient Age : " + age);
        System.out.println("Gender : " + gender);

    }


}
