/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.Location;
import java.util.List;

/**
 *
 * @author TURAL
 */
public interface LocationDaoInter {
     
     
    List<Location> getAll();
     
    public Location getById(int id);

    boolean insertLocation(Location u);

    boolean updateLocation(Location u);
     
    boolean removeLocation(int id);
}
