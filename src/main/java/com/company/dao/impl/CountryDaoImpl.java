/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;
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
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    public Country getCountry(ResultSet rs) throws SQLException {

        int id = rs.getInt("Id");
        String name = rs.getString("NAME");
        int regionId = rs.getInt("REGION_ID");

        Country contry = new Country(id, name,new Region(regionId));
        System.out.println(contry);
        return contry;

    }

    @Override
    public List<Country> getAll() {
        List<Country> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM countries;");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                Country contry = getCountry(rs);
                list.add(contry);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    @Override
    public Country getById(int userId) {
        Country el = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM countries WHERE ID = ?;");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                el = getCountry(rs);

            }
        } catch (Exception ex) {

        }
        return el;
    }

    @Override
    public boolean updateCountry(Country u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE countries SET name=?,REGION_ID=? WHERE id= ?;");
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getRegion().getId());
            stmt.setInt(3, u.getId());
            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeCountry(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM countries WHERE id=?;");
            stmt.setInt(1, id);

            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

}
