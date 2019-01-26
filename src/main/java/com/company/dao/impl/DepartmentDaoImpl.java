/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.DepartmentDaoInter;
import com.company.entity.Department;
import com.company.entity.Employee;
import com.company.entity.Location;
import java.sql.Connection;
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
public class DepartmentDaoImpl extends AbstractDAO implements DepartmentDaoInter {

    public Department getDepartment(ResultSet rs) throws SQLException {

        int id = rs.getInt("Id");
        String name = rs.getString("name");
        int managerId = rs.getInt("manager_id");
        int locationId = rs.getInt("location_Id");

        Department contry = new Department(id, name,new Employee(managerId),new Location(locationId));
        System.out.println(contry);
        return contry;

    }

    @Override
    public List<Department> getAll() {
        List<Department> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM Departments;");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                Department contry = getDepartment(rs);
                list.add(contry);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    @Override
    public Department getById(int userId) {
        Department el = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Departments WHERE ID = ?;");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                el = getDepartment(rs);

            }
        } catch (Exception ex) {

        }
        return el;
    }

    @Override
    public boolean updateDepartment(Department u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Departments SET NAME =?,MANAGER_ID=?, LOCATION_ID=? WHERE id= ?;");
            stmt.setString(1, u.getName());
            stmt.setDouble(2, u.getManager().getId());
            stmt.setDouble(3, u.getLocation().getId());
            stmt.setInt(4, u.getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeDepartment(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Departments WHERE id=?;");
            stmt.setInt(1, id);

            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean insertDepartment(Department u) {

        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Departments (NAME ,MANAGER_ID, LOCATION_ID) VALUES (?,?,?) ;");
            stmt.setString(1, u.getName());
            stmt.setDouble(2, u.getManager().getId());
            stmt.setDouble(3, u.getLocation().getId());
            stmt.setInt(4, u.getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }


}
