package com.project.crud.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import com.project.crud.DTO.EmployeeDTO;

public class EmployeeDAO 
{

    public void save(EmployeeDTO dto) 
    {
        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(EmployeeDTO.class);
        SessionFactory fact = cfg.buildSessionFactory();
        Session session = fact.openSession();
        Transaction tx = session.beginTransaction();
        try 
        {
            session.save(dto);
            tx.commit();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            session.close();
        }
    }

    public void selectEmployeesByNameStartingWithA() 
    {
        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(EmployeeDTO.class);
        SessionFactory fact = cfg.buildSessionFactory();
        Session session = fact.openSession();
        Transaction tx = session.beginTransaction();
        try 
        {
            Query<EmployeeDTO> qry = session.createQuery("from EmployeeDTO e where e.employee_name like 'A%'", EmployeeDTO.class);
            List<EmployeeDTO> employees = qry.list();
            employees.forEach(System.out::println);
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            session.close();
        }
    }

    public void selectEmployeesBySalaryRange() 
    {
        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(EmployeeDTO.class);
        SessionFactory fact = cfg.buildSessionFactory();
        Session session = fact.openSession();
        Transaction tx = session.beginTransaction();
        try 
        {
            Query<EmployeeDTO> qry = session.createQuery("from EmployeeDTO e where e.employee_salary between 5000 and 7000", EmployeeDTO.class);
            List<EmployeeDTO> employees = qry.list();
            employees.forEach(System.out::println);
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            session.close();
        }
    }

    public void selectSecondMinMaxSalaries() 
    {
        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(EmployeeDTO.class);
        SessionFactory fact = cfg.buildSessionFactory();
        Session session = fact.openSession();
        Transaction tx = session.beginTransaction();
        try 
        {
            Query<Integer> qryMin = session.createQuery("select distinct e.employee_salary from EmployeeDTO e order by e.employee_salary asc", Integer.class);
            qryMin.setFirstResult(1);
            qryMin.setMaxResults(1);
            Integer secondMinSalary = (Integer) qryMin.uniqueResult();
            System.out.println("2nd Minimum Salary: " + secondMinSalary);
            Query<Integer> qryMax = session.createQuery("select distinct e.employee_salary from EmployeeDTO e order by e.employee_salary desc", Integer.class);
            qryMax.setFirstResult(1);
            qryMax.setMaxResults(1);
            Integer secondMaxSalary = (Integer) qryMax.uniqueResult();
            System.out.println("2nd Maximum Salary: " + secondMaxSalary);
            tx.commit();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            session.close();
        }
    }

    public void updateDuplicateSalaries() 
    {
        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(EmployeeDTO.class);
        SessionFactory fact = cfg.buildSessionFactory();
        Session session = fact.openSession();
        Transaction tx = session.beginTransaction();
        try 
        {
            Query qry = session.createQuery("update EmployeeDTO e set e.employee_salary = 5000 where e.employee_salary in duplicate");
            Query subquery = session.createQuery("select e.employee_salary from EmployeeDTO e group by e.employee_salary having count(*) > 1");     
            List<Integer> duplicateSalaries = subquery.list();
            qry.setParameterList("duplicates", duplicateSalaries);  
            int updatedCount = qry.executeUpdate();
            System.out.println(updatedCount + " employees' salaries updated to 5000.");
            tx.commit();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            session.close();
        }
    }

 
}
