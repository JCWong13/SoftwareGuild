/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VMAuditDao;
import com.sg.vendingmachine.dao.VMDaoPersistenceException;
import com.sg.vendingmachine.dto.Transaction;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author janie
 */
public class LoggingAdvice {

    VMAuditDao auditDao;

    public LoggingAdvice(VMAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp, Transaction item) {
        String auditEntry = jp.getSignature().getName() + " : " 
                + item.getItembought().getNameOfItem() 
                + " " + item.getItembought().getLocationItem(); 
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VMDaoPersistenceException ex) {
            System.out.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createAuditEntryException(JoinPoint jp, Throwable blah) {
        String auditEntry = jp.getSignature().getName() + " : " + blah.toString();
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VMDaoPersistenceException ex) {
            System.out.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
