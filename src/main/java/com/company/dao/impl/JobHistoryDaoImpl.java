/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.JobHistoryDaoInter;
import com.company.entity.Department;
import com.company.entity.Employee;
import com.company.entity.JobHistory;
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
public class JobHistoryDaoImpl extends AbstractDAO implements JobHistoryDaoInter {

    public JobHistory getJobHistory(ResultSet rs) throws SQLException {

        int id = rs.getInt("Id");
        int empId = rs.getInt("EMPLOYEE_ID");
        Date startDate = rs.getDate("START_DATE");
        Date endDate = rs.getDate("END_DATE");
        int jobId = rs.getInt("JOB_ID");
        int departmentId =  rs.getInt("DEPARTMENT_ID");

        JobHistory contry = new JobHistory(id,new Employee(empId), startDate, endDate,new Job(jobId), new Department(departmentId));

        System.out.println(contry);
        return contry;

    }

    @Override
    public List<JobHistory> getAll() {
        List<JobHistory> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM JobHistory;");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                JobHistory contry = getJobHistory(rs);
                list.add(contry);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    @Override
    public JobHistory getById(int userId) {
        JobHistory el = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM JobHistory WHERE ID = ?;");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                el = getJobHistory(rs);

            }
        } catch (Exception ex) {

        }
        return el;
    }

    @Override
    public boolean updateJobHistory(JobHistory u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE JobHistory SET employee_id =?,start_date=?, end_date=?,job_id=?,department_id=? WHERE id= ?;");
            stmt.setInt(1, u.getEmployee().getId());
            stmt.setDate(2, u.getStartDate());
            stmt.setDate(3, u.getEndDate());
            stmt.setInt(4, u.getJob().getId());
            stmt.setInt(5, u.getDepartment().getId());

            stmt.setInt(6, u.getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeJobHistory(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM JobHistory WHERE id=?;");
            stmt.setInt(1, id);

            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean insertJobHistory(JobHistory u) {

        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO JobHistory (employee_id, start_date, end_date,job_id,department_id) VALUES (?,?,?,?,?);");
            stmt.setInt(1, u.getEmployee().getId());
            stmt.setDate(2, u.getStartDate());
            stmt.setDate(3, u.getEndDate());
            stmt.setInt(4, u.getJob().getId());
            stmt.setInt(5, u.getDepartment().getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

}
