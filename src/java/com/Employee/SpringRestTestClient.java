/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Employee;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.client.RestTemplate;

public class SpringRestTestClient {

    public static final String REST_SERVICE_URI = "http://localhost:8080/LevartiExercise/";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllUsers() {
        System.out.println("Testing list All Employees API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> employeesMap = restTemplate.getForObject(REST_SERVICE_URI + "/employee/", List.class);

        if (employeesMap != null) {
            for (LinkedHashMap<String, Object> map : employeesMap) {
                System.out.println("Employee : Emp id=" + map.get("EMPID") + ",First Name=" + map.get("FIRSTNAME") + ", Last Name=" + map.get("LASTNAME") + ", Date=" + map.get("DATE") + ", Department=" + map.get("DEPARTMENT"));
            }
        } else {
            System.out.println("No Employee exist----------");
        }
    }

    /* GET */
    private static void getEmployeeByID() {
        System.out.println("Testing get Employee by ID API----------");
        RestTemplate restTemplate = new RestTemplate();
        Employee user = restTemplate.getForObject(REST_SERVICE_URI + "/employee/id/123456", Employee.class);
        System.out.println(user);
    }

    /* GET */
    private static void getEmployeeByLastName() {
        System.out.println("Testing get Employee by Last Name API----------");
        RestTemplate restTemplate = new RestTemplate();
        Employee user = restTemplate.getForObject(REST_SERVICE_URI + "/employee/lastname/vyas", Employee.class);
        System.out.println(user);
    }

    /* POST */
    private static void createOrUpdateEmployee() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        Employee employee = new Employee(123456, "Arpita", "Vyas", "23/04/1993", "IT");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "employee/add/", employee, Employee.class);
        System.out.println("Location : " + uri.toASCIIString());
       
    }

    public static void main(String args[]) {

        listAllUsers();
        getEmployeeByID();
        getEmployeeByLastName();
        createOrUpdateEmployee();
        listAllUsers();

    }
}






