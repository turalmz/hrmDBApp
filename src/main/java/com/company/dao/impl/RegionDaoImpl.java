/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.RegionDaoInter;
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
public class RegionDaoImpl extends AbstractDAO implements RegionDaoInter {

    public Region getRegion(ResultSet rs) throws SQLException {

        String id = rs.getString("Id");
        String name = rs.getString("NAME");

        Region contry = new Region(id, name);
        System.out.println(contry);
        return contry;

    }

    @Override
    public List<Region> getAll() {
        List<Region> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM Regions;");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                Region contry = getRegion(rs);
                list.add(contry);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    @Override
    public Region getById(int userId) {
        Region el = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Regions WHERE ID = ?;");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                el = getRegion(rs);

            }
        } catch (Exception ex) {

        }
        return el;
    }

    @Override
    public boolean updateRegion(Region u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Regions SET name=? WHERE id= ?;");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeRegion(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Regions WHERE id=?;");
            stmt.setInt(1, id);

            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean insertRegion(Region u) {

        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Regions (id,name) VALUES (?,?);");
            stmt.setString(1, u.getId());
            stmt.setString(1, u.getName());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

}
