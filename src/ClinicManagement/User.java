package ClinicManagement;

public abstract class User
{
    
    protected String patientName;
    protected String patientNumber;
    
    User (String patientName,String patientNumber)
    {
        this.patientName=patientName;
        this.patientNumber=patientNumber;
    }
    
    public String getPatientName()
    {
        return patientName;
    }

    public String getPatientNumber()
    {
        return patientNumber;
    }
    
    public abstract void displayDetails();
}
