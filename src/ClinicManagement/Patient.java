package ClinicManagement;

public class Patient extends User
{

    private String patientID;
    private String gender;
    private int age;

    Patient(String name,String phoneNumber,String patientID,String gender,int age )
    {
        super(name, phoneNumber);
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
        System.out.println("Patient Name : " + name);
        System.out.println("Patient Phone Number : " + phoneNumber);
        System.out.println("Patient Age : " + age);
        System.out.println("Gender : " + gender);

    }
}

