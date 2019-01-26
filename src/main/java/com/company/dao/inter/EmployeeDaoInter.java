/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.Employee;
import java.util.List;

/**
 *
 * @author TURAL
 */
public interface EmployeeDaoInter {

    List<Employee> getAll();

    public Employee getById(int id);

    boolean updateEmployee(Employee u);

    boolean removeEmployee(int id);

    boolean insertEmployee(Employee skl);
}
