package ClinicManagement;

public abstract class User
{
    protected String patientID;
    protected String patientName;
    protected String patientNumber;
    protected String gender;
    protected int age;

    User (String patientID,String patientName,String patientNumber,String gender,int age)
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


    public abstract void displayDetails();
}
