package rest;

public class Employee
{
    public int employeeId;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    
    public Employee(int employeeId, String firstName, String lastName, String email, String phone)
    {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}