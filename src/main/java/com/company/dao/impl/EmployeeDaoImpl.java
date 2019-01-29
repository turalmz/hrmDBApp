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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TURAL
 */
public class EmployeeDaoImpl extends AbstractDAO implements EmployeeDaoInter {

    public Employee getEmployee(ResultSet rs) throws SQLException {

        int id = rs.getInt("Id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String email = rs.getString("email");
        String phoneNumber = rs.getString("phone_number");
        Date hireDate = rs.getDate("hire_date");
        int jobId = rs.getInt("job_id");
        double salary = rs.getDouble("salary");
        double commissionPct = rs.getDouble("commission_pct");
        int managerId = rs.getInt("manager_id");
        int departmentId = rs.getInt("department_id");

        Employee contry = new Employee(id, firstname, lastname, email, phoneNumber, hireDate, new Job(jobId), salary, commissionPct, new Employee(managerId), new Department(departmentId));
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
            PreparedStatement stmt = conn.prepareStatement("UPDATE Employees SET firstname =?,lastname =?,email=?, phone_number=?,hire_date=?,job_id=?,salary=?,commission_pct=?,manager_id=?,department_id=? WHERE id= ?;");
            stmt.setString(1, u.getFirstname());
            stmt.setString(2, u.getLastname());

            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhoneNumber());
            stmt.setDate(5, u.getHireDate());
            try {
                stmt.setInt(6, u.getJob().getId());
            } catch (SQLException ex) {
                try {
                    System.err.println(ex);
                    stmt.setNull(6, java.sql.Types.INTEGER);
                } catch (SQLException ex1) {
                    System.err.println(ex1);

                }
            }
            stmt.setDouble(7, u.getSalary());
            stmt.setDouble(8, u.getCommissionPct());
            try {
                stmt.setInt(9, u.getManager().getId());
            } catch (Exception ex) {
                stmt.setNull(9, java.sql.Types.INTEGER);
            }
            try {
                stmt.setInt(10, u.getDepartment().getId());
            } catch (Exception ex) {
                stmt.setNull(10, java.sql.Types.INTEGER);
            }

            stmt.setInt(11, u.getId());

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
        PreparedStatement stmt = null;
        try {
            conn = connect();
            stmt = conn.prepareStatement("INSERT INTO Employees "
                    + "(firstname,lastname ,email, phone_number, hire_date, job_id,salary, commission_pct, manager_id, department_id) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?);");
            stmt.setString(1, u.getFirstname());
            stmt.setString(2, u.getLastname());
            System.err.println("hire");
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhoneNumber());
            if (u.getHireDate() != null) {
                stmt.setDate(5, u.getHireDate());
            } else {
                stmt.setNull(5,java.sql.Types.DATE);

            }

    
            if (u.getJob() != null) {
                stmt.setInt(6, u.getJob().getId());
            } else{
                stmt.setString(6, null);

            }
            stmt.setDouble(7, u.getSalary());
            stmt.setDouble(8, u.getCommissionPct());
            try {
                stmt.setInt(9, u.getManager().getId());

            } catch (Exception ex) {
                stmt.setString(9, null);

            }
            try {
                stmt.setInt(10, u.getDepartment().getId());
            } catch (Exception ex) {
                stmt.setString(10, null);
            }
            System.out.println(stmt);

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            System.out.println(stmt);

            b = false;
        }
        return b;
    }

}
