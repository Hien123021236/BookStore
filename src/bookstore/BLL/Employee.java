/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.BLL;

/**
 *
 * @author Orics
 */
public class Employee {
    private String EmployeeID;
    private String Password;
    private String Role;
    private String Name;
    private String Gender;
    private String DateOfBirth;

    public Employee() {
    }

    public Employee(String EmployeeID, String Password, String Role, String Name, String Gender, String DateOfBirth) {
        this.EmployeeID = EmployeeID;
        this.Password = Password;
        this.Role = Role;
        this.Name = Name;
        this.Gender = Gender;
        this.DateOfBirth = DateOfBirth;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public String getPassword() {
        return Password;
    }

    public String getRole() {
        return Role;
    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return Gender;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setDateOfBirth(String DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    
    
}
