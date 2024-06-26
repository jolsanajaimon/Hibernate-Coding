package com.project.crud.Util;

import java.util.Iterator;
import java.util.List;

import com.project.crud.DAO.EmployeeDAO;
import com.project.crud.DTO.EmployeeDTO;

public class EmployeeTester {

    public static void main(String[] args) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployee_name("Amanda");
        dto.setEmployee_salary(5500);
        
        EmployeeDAO dao = new EmployeeDAO();
        
        dao.save(dto);
        dao.selectEmployeesByNameStartingWithA();
        dao.selectEmployeesBySalaryRange();
        dao.selectSecondMinMaxSalaries();
        dao.updateDuplicateSalaries();
        System.out.println("Successful");
    }
}
