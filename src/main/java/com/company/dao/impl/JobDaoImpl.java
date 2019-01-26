/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.JobDaoInter;
import com.company.entity.Country;
import com.company.entity.Job;
import com.company.entity.Region;
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
public class JobDaoImpl extends AbstractDAO implements JobDaoInter {

    public Job getJob(ResultSet rs) throws SQLException {

        int id = rs.getInt("Id");
        String title = rs.getString("JOB_TITLE");
        double minSalary = rs.getDouble("MIN_SALARY");
        double maxSalary = rs.getDouble("MAX_SALARY");

        Job contry = new Job(id, title, minSalary, maxSalary);
        System.out.println(contry);
        return contry;

    }

    @Override
    public List<Job> getAll() {
        List<Job> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM Jobs;");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                Job contry = getJob(rs);
                list.add(contry);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    @Override
    public Job getById(int userId) {
        Job el = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Jobs WHERE ID = ?;");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                el = getJob(rs);

            }
        } catch (Exception ex) {

        }
        return el;
    }

    @Override
    public boolean updateJob(Job u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Jobs SET JOB_TITLE =?,MIN_SALARY=?, MAX_SALARY=? WHERE id= ?;");
            stmt.setString(1, u.getTitle());
            stmt.setDouble(2, u.getMinSalary());
            stmt.setDouble(3, u.getMaxSalary());
            stmt.setInt(4, u.getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeJob(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Jobs WHERE id=?;");
            stmt.setInt(1, id);

            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean insertJob(Job u) {

        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Jobs (JOB_TITLE ,MIN_SALARY, MAX_SALARY) VALUES (?,?,?);");
            stmt.setString(1, u.getTitle());
            stmt.setDouble(2, u.getMinSalary());
            stmt.setDouble(3, u.getMaxSalary());
            stmt.setInt(4, u.getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

}
