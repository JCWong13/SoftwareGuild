/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.FMAuditDao;
import com.sg.flooringmastery.dao.FMDaoPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author janie
 */
public class LoggingAdvice {
    
    FMAuditDao auditDao;
   
    public LoggingAdvice(FMAuditDao auditDao) {
        this.auditDao=auditDao;
    }
    
       public void createAuditEntryException(JoinPoint jp, Throwable exception) {
        String auditEntry = jp.getSignature().getName() + " : " + 
                exception.getClass().getSimpleName() + 
                " : " + exception.getMessage();
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FMDaoPersistenceException ex) {
            System.out.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}
