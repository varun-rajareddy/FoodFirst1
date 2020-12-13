package com.example.foodfirst.models;

public class loginPerson {
    String personName;
    String personNumber;

public loginPerson(){}

    public loginPerson(String personName, String personNumber) {
        this.personName = personName;
        this.personNumber = personNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }




}
