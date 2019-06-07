/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Employee;

public class Employee {
    
    int EMPID;
    String FIRSTNAME,LASTNAME,DATE,DEPARTMENT;

    public Employee(int EMPID, String FIRSTNAME, String LASTNAME, String DATE, String DEPARTMENT) {
        this.EMPID = EMPID;
        this.FIRSTNAME = FIRSTNAME;
        this.LASTNAME = LASTNAME;
        this.DATE = DATE;
        this.DEPARTMENT = DEPARTMENT;
    }

    public int getEMPID() {
        return EMPID;
    }

    public void setEMPID(int EMPID) {
        this.EMPID = EMPID;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }
        
}







