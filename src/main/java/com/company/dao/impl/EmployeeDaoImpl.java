/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmployeeDaoInter;
import com.company.entity.Department;
import com.company.entity.Employee;
import com.company.entity.Job;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TURAL
 */
public class EmployeeDaoImpl extends AbstractDAO implements EmployeeDaoInter {

    public Employee getEmployee(ResultSet rs) throws SQLException {

        int id = rs.getInt("Id");
        String fullname = rs.getString("fullname");
        String email = rs.getString("email");
        String phoneNuber = rs.getString("phone_nuber");
        Date hireDate = rs.getDate("hire_date");
        int jobId = rs.getInt("job_id");
        double salary = rs.getDouble("salary");
        double commissionPct = rs.getDouble("commission_pct");
        int managerId = rs.getInt("manager_id");
        int departmentId = rs.getInt("department_id");

        Employee contry = new Employee(id,fullname, email, phoneNuber, hireDate, new Job(jobId), salary, commissionPct,new Employee(managerId),new Department(departmentId));
        System.out.println(contry);
        return contry;

    }

    @Override
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM Employees;");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                Employee contry = getEmployee(rs);
                list.add(contry);

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    @Override
    public Employee getById(int userId) {
        Employee el = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employees WHERE ID = ?;");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                el = getEmployee(rs);

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return el;
    }

    @Override
    public boolean updateEmployee(Employee u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Employees SET JOB_TITLE =?,MIN_SALARY=?, MAX_SALARY=? WHERE id= ?;");
            stmt.setString(1, u.getFullname());
            stmt.setString(2, u.getEmail());
            stmt.setString(2, u.getPhoneNumber());
            stmt.setDate(2, u.getHireDate());
            stmt.setInt(2, u.getDepartment().getId());

            stmt.setDouble(3, u.getSalary());
            stmt.setInt(4, u.getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeEmployee(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Employees WHERE id=?;");
            stmt.setInt(1, id);

            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean insertEmployee(Employee u) {

        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Employees (JOB_TITLE ,MIN_SALARY, MAX_SALARY) VALUES (?,?,?);");
            stmt.setString(1, u.getTitle());
            stmt.setDouble(2, u.getMinSalary());
            stmt.setDouble(3, u.getMaxSalary());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

}