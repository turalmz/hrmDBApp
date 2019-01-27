/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.LocationDaoInter;
import com.company.entity.Country;
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
public class LocationDaoImpl extends AbstractDAO implements LocationDaoInter {

    public Location getLocation(ResultSet rs) throws SQLException {

        int id = rs.getInt("Id");
        String streetAddress= rs.getString("street_address");
        String postalCode = rs.getString("postal_code");
        String city= rs.getString("city");
        String stateProvince= rs.getString("state_province");
        int countryId= rs.getInt("country_id");
        Location contry = new Location(id,streetAddress, postalCode, city, stateProvince, new Country(countryId));
        System.out.println(contry);
        return contry;

    }

    @Override
    public List<Location> getAll() {
        List<Location> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM Locations;");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                Location contry = getLocation(rs);
                list.add(contry);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    @Override
    public Location getById(int userId) {
        Location el = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Locations WHERE ID = ?;");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                el = getLocation(rs);

            }
        } catch (Exception ex) {

        }
        return el;
    }

    @Override
    public boolean updateLocation(Location u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Locations SET street_address =?,postal_code =?,city=?, state_province=?,country_id=? WHERE id= ?;");
            stmt.setString(1, u.getStreetAddress());
            stmt.setString(2, u.getPostalCode());
            stmt.setString(3, u.getCity());
            stmt.setString(4, u.getStateProvince());
            stmt.setInt(5, u.getCountry().getId());
            stmt.setInt(6, u.getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeLocation(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Locations WHERE id=?;");
            stmt.setInt(1, id);

            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean insertLocation(Location u) {

        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Locations (street_address,postal_code,city, state_province,country_id) VALUES (?,?,?,?,?);");
            stmt.setString(1, u.getStreetAddress());
            stmt.setString(2, u.getPostalCode());
            stmt.setString(3, u.getCity());
            stmt.setString(4, u.getStateProvince());
            stmt.setInt(5, u.getCountry().getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

}
