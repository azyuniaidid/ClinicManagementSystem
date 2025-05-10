package ClinicManagement;

public class Patient extends User
{

    private String patientID;
    private String gender;
    private int age;

    Patient(String patientName,String patientNumber,String patientID,String gender,int age )
    {
        super(patientName, patientNumber);
        this.patientID=patientID;
        this.gender=gender;
        this.age=age;
    }

    public String getPatientID()
    {
        return patientID;
    }


    public String getGender()
    {
        return gender;
    }

    public int getAge()
    {
        return age;
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
