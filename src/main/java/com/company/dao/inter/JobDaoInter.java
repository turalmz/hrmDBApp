/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.Job;
import java.util.List;

/**
 *
 * @author TURAL
 */
public interface JobDaoInter {
     
    List<Job> getAll();
     
    public Job getById(int id);

    boolean insertJob(Job u);

    boolean updateJob(Job u);
     
    boolean removeJob(int id);
    
}
