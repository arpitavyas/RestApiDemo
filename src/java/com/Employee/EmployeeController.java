/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Employee;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public class EmployeeController {

    EmployeeService employeeService = new EmployeeService();

    //-------------------Retrieve All Employees--------------------------------------------------------
    @RequestMapping(value = "/employee/", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllEmployees() {
        List<Employee> employees = employeeService.findAllUsers();
        if (employees.isEmpty()) {
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    //-------------------Retrieve Single Employee by employee id--------------------------------------------------------
    @RequestMapping(value = "/employee/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
        System.out.println("Fetching Employee with id " + id);
        Employee employee = employeeService.findByEmpId(id);
        if (employee == null) {
            System.out.println("Employee with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    //-------------------Retrieve Single Employee by employee last name--------------------------------------------------------
    @RequestMapping(value = "/employee/lastname/{lastname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable("lastname") String lname) {
        System.out.println("Fetching Employee with id " + lname);
        Employee employee = employeeService.findByLastName(lname);
        if (employee == null) {
            System.out.println("Employee with last name " + lname + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    //-------------------Create/Update an Employee--------------------------------------------------------
    @RequestMapping(value = "/employee/add/", method = RequestMethod.POST)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucbuilder) {
        System.out.println("Creating Employee " + employee.getEMPID());

        //check if employee id's length is 6 or not before add/update
        if (String.valueOf(employee.getEMPID()).length() != 6) {
            System.out.println("Invalid length for Employee ID");
            return new ResponseEntity<Employee>(HttpStatus.NOT_ACCEPTABLE);

            //check if employee with Id exist then update
        } else if (employeeService.isEmpoyeeExist(employee)) {
            System.out.println("Employee with ID " + employee.getEMPID() + " already exist");
            System.out.println("Updating Employee " + employee.getEMPID());

            //before update check the age
            if (employeeService.employeeAge(employee) < 18) {
                System.out.println("Inappropriate Age");
                return new ResponseEntity<Employee>(HttpStatus.UNPROCESSABLE_ENTITY);
            }

            Employee currentEmp = employeeService.findByEmpId(employee.getEMPID());

            if (currentEmp == null) {
                System.out.println("Employee with id " + employee.getEMPID() + " not found");
                return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
            }

            currentEmp.setFIRSTNAME(employee.getFIRSTNAME());
            currentEmp.setLASTNAME(employee.getLASTNAME());
            currentEmp.setDEPARTMENT(employee.getDEPARTMENT());
            currentEmp.setDATE(employee.getDATE());
            currentEmp.setEMPID(employee.getEMPID());

            employeeService.updateEmployee(currentEmp);
            return new ResponseEntity<Employee>(HttpStatus.OK);

        } //before add check employee age>18
        else if (employeeService.employeeAge(employee) < 18) {
            System.out.println("Inappropriate Age");
            return new ResponseEntity<Employee>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        //add employee
        employeeService.addEmployee(employee);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucbuilder.path("/employee/id/{id}").buildAndExpand(employee.getEMPID()).toUri());
        return new ResponseEntity<Employee>(HttpStatus.OK);
    }
}






