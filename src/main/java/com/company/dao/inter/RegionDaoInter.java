package com.company.dao.inter;

import com.company.entity.Region;
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
public interface RegionDaoInter {

    List<Region> getAll();

    public Region getById(int id);

    boolean insertRegion(Region u);

    boolean updateRegion(Region u);

    boolean removeRegion(int id);

}
