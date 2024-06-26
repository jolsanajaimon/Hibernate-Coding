package com.project.crud.DTO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "employee_table")
public class EmployeeDTO implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "employee_id")
    private int employee_id;

    @Column(name = "employee_name")
    private String employee_name;

    @Column(name = "employee_salary")
    private int employee_salary;

    public EmployeeDTO() {
        System.out.println(this.getClass().getSimpleName() + " created");
    }


    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(int employee_salary) {
        this.employee_salary = employee_salary;
    }

    @Override
    public String toString() {
        return "EmployeeDTO [employee_id=" + employee_id + ", employee_name=" + employee_name + ", employee_salary=" + employee_salary + "]";
    }
}
