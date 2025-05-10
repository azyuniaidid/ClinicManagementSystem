package ClinicManagement;

public class Doctor extends User {

  private String doc_name;
  private String doc_id;
  private String doc_number;
  private String availabiity;
  private double chgperappt; 

  Doctor(String doc_name,String doc_id,String doc_number,String availability,double chgperappt )
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

  
