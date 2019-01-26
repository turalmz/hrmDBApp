package com.company.dao.inter;



import com.company.entity.Department;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TURAL
 */
public interface DepartmentDaoInter {

    List<Department> getAll();

    public Department getById(int id);

    boolean insertDepartment(Department u);

    boolean updateDepartment(Department u);

    boolean removeDepartment(int id);

}
