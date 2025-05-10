package PACKAGE_NAME;

public class Patient extends User
{
    private String patientID;
    private String patientName;
    private String patientNumber;
    private String gender;
    private int age;

    Patient ()
    {
        this.patientID=patientID;
        this.patientName=patientName;
        this.patientNumber=patientNumber;
        this.gender=gender;
        this.age=age;
    }

    public String getPatientID()
    {
        return patientID;
    }

    public String getPatientName()
    {
        return patientName;
    }

    public String getPatientNumber()
    {
        return patientNumber;
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
