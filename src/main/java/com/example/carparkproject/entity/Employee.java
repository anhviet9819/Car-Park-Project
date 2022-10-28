package com.example.carparkproject.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "employee")
public class Employee {
    public static final java.util.regex.Pattern VALID_EMAIL_ADDRESS_REGEX = java.util.regex.Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", java.util.regex.Pattern.CASE_INSENSITIVE);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "account")
    @NotNull
    @Size(min = 2, max = 50, message = "Account has min 2 char and max 50 char")
    private String account;

    @Column(name = "department")
    @NotNull
    @Size(min = 2, max = 10, message = "Department has min 2 char and max 50 char")
    private String department;

    @Column(name = "employeeAddress")
    @Size(min = 2, max = 50, message = "Address has min 2 char and max 50 char")
    private String employeeAddress;

    @Column(name = "employeeBirthdate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date employeeBirthdate;

    @Column(name = "employeeEmail")
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[A-Z0-9._%+-|a-z0-9._%+-]+@[A-Z0-9.-|a-z0-9.-]+\\.[A-Z|a-z]{2,6}$", message = "Email invalid!")
    private String employeeEmail;

    @Column(name = "employeeName")
    @NotNull
//    @Size(min = 3, max = 50, message = "")
    @Pattern(regexp = "^[a-zA-Z\\s]{10,50}", message = "Employee name must have only alphabet character and have 10-50 char")
    private String employeeName;

    @Column(name = "employeePhone")
    @NotNull
//    @Size(min = 2, max = 10)
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Phone number must be 10 digit and starts with 84 or 03, 05, 07, 08, 09!")
    private String employeePhone;

    @Column(name = "password")
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "Password must contain at least one digit [0-9].\n" +
                    "Password must contain at least one lowercase Latin character [a-z].\n" +
                    "Password must contain at least one uppercase Latin character [A-Z].\n" +
                    "Password must contain at least one special character like ! @ # & ( ).\n" +
                    "Password must contain a length of at least 8 characters and a maximum of 20 characters.")
//    @Size(min = 8, max = 20, message = "message = \"Password must contain at least one digit [0-9].\\n\" +\n" +
//            "                    \"Password must contain at least one lowercase Latin character [a-z].\\n\" +\n" +
//            "                    \"Password must contain at least one uppercase Latin character [A-Z].\\n\" +\n" +
//            "                    \"Password must contain at least one special character like ! @ # & ( ).\\n\" +\n" +
//            "                    \"Password must contain a length of at least 8 characters and a maximum of 20 characters.\"")
    private String password;

    @Column(name = "sex")
    @NotNull
//    @Size(max = 1)
    @Pattern(regexp = "M|F|O", message = "Gender must be M(male), F(female), O(other)!")
    private String sex;

    public Employee(String account, String department, String employeeAddress, Date employeeBirthdate, String employeeEmail, String employeeName, String employeePhone, String password, String sex) {
        this.account = account;
        this.department = department;
        this.employeeAddress = employeeAddress;
        this.employeeBirthdate = employeeBirthdate;
        this.employeeEmail = employeeEmail;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.password = password;
        this.sex = sex;
    }

    public Employee() {

    }

    public Employee(String account, String department, String employeeAddress, String employeeName, String employeePhone, String password, String sex) {
        this.account = account;
        this.department = department;
        this.employeeAddress = employeeAddress;
        this.employeeName = employeeName;
        this.employeePhone = employeePhone;
        this.password = password;
        this.sex = sex;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public Date getEmployeeBirthdate() {
        return employeeBirthdate;
    }

    public void setEmployeeBirthdate(Date employeeBirthdate) {
        this.employeeBirthdate = employeeBirthdate;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
