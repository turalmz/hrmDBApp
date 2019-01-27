/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

/**
 *
 * @author TURAL
 */
public class Department {
    Integer id;
    String name;
    
    Employee manager;
    Location location;

    public Department(Integer id, String name, Employee manager, Location location) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.location = location;
    }

    public Department(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", manager=" + manager + ", location=" + location + '}';
    }


}
