/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.config;

import com.company.dao.impl.CountryDaoImpl;
import com.company.dao.impl.JobHistoryDaoImpl;
import com.company.dao.impl.DepartmentDaoImpl;
import com.company.dao.impl.EmployeeDaoImpl;
import com.company.dao.impl.JobDaoImpl;
import com.company.dao.impl.LocationDaoImpl;
import com.company.dao.impl.RegionDaoImpl;
import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.DepartmentDaoInter;
import com.company.dao.inter.LocationDaoInter;
import com.company.dao.inter.EmployeeDaoInter;
import com.company.dao.inter.JobHistoryDaoInter;
import com.company.dao.inter.RegionDaoInter;
import com.company.dao.inter.JobDaoInter;

/**
 *
 * @author TURAL
 */
public class Context {

    public static EmployeeDaoInter instanceEmployeeDao() {
        return new EmployeeDaoImpl();
    }

    public static DepartmentDaoInter instanceDepartmentDao() {
        return new DepartmentDaoImpl();
    }

    public static JobDaoInter instanceJobDao() {
        return new JobDaoImpl();
    }

    public static LocationDaoInter instanceLocationDao() {
        return new LocationDaoImpl();
    }

    public static JobHistoryDaoInter instanceJobHistoryDao() {
        return new JobHistoryDaoImpl();
    }

    public static RegionDaoInter instanceRegionDao() {
        return new RegionDaoImpl();
    }

    public static CountryDaoInter instanceCountryDao() {
        return new CountryDaoImpl();
    }
}
