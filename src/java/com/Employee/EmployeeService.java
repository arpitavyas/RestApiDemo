/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmployeeService {

   public static  List<Employee> employees;
   
   static {
       employees=populateDummyEmployees();
   }

    public List<Employee> findAllUsers() {
        return employees;
    }

    public Employee findByEmpId(int id) {
        for (Employee employee : employees) {
            if (employee.getEMPID() == id) {
                return employee;
            }
        }
        return null;
    }

    public Employee findByLastName(String lastname) {
        for (Employee employee : employees) {
            if (employee.getLASTNAME().equalsIgnoreCase(lastname)) {
                return employee;
            }
        }
        return null;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void updateEmployee(Employee employee) {
        int index = employees.indexOf(employee);
        employees.set(index, employee);
    }

    public boolean isEmpoyeeExist(Employee employee) {
        return findByEmpId(employee.getEMPID()) != null;
    }

    public int employeeAge(Employee employee) {
        try {
            String s = employee.getDATE();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d = sdf.parse(s);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int date = c.get(Calendar.DATE);
            LocalDate l1 = LocalDate.of(year, month, date);
            LocalDate now1 = LocalDate.now();
            Period diff1 = Period.between(l1, now1);
            System.out.println("age:" + diff1.getYears() + "years");
            return diff1.getYears();
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private static List<Employee> populateDummyEmployees() {
        List<Employee> employee = new ArrayList<Employee>();
        employee.add(new Employee(123455, "Arpita", "Vyas", "12/04/1993", "IT"));
        employee.add(new Employee(123465, "Smith", "Mathew", "22/04/1993", "IT2"));
        employee.add(new Employee(123475,"Jack","Brown","23/04/1993","IT2"));
        employee.add(new Employee(123485,"Cody","White","24/04/1993","IT3"));
        employee.add(new Employee(123495,"Krina","Patek","25/04/1993","IT4"));
        return employee;
    }

}




