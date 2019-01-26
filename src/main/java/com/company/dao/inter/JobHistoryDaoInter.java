/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.JobHistory;
import java.util.List;

/**
 *
 * @author TURAL
 */
public interface JobHistoryDaoInter {
     
    List<JobHistory> getAll();
     
    public JobHistory getById(int id);
     
    boolean updateJobHistory(JobHistory u);
     
    boolean removeJobHistory(int id);
    
}
