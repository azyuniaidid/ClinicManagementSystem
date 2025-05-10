package ClinicManagement;

public abstract class User
{

    protected String name;
    protected String phoneNumber;

    User (String name,String phoneNumber)
    {
        this.name=name;
        this.phoneNumber=phoneNumber;
    }

    public String getName()
    {
        return name;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public abstract void displayDetails();
}
