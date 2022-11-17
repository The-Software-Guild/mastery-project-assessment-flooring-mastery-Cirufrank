/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Vending Machine with Spring DI
 * 
 * @description This class implements the audit file of our Flooring Services App that 
 * keeps track of when users purchased items
 */

@Component
@Primary
public class AuditDaoFileImpl implements AuditDao {
    final private String DATE_FORMAT_PATTERN = "yyyy-MM-dd hh:mm:ss";
    private String auditFileName;
    
    public AuditDaoFileImpl() {
        auditFileName = "audit.txt";
    }
    public AuditDaoFileImpl(String audiFileName) {
        this.auditFileName = auditFileName;
    }
    
    public void writeAuditEntry(String message) {
        final LocalDateTime currentLocalDateTime = LocalDateTime.now();
        final String timeStamp = currentLocalDateTime.format(
                                    DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)); 
        try {
            if (!DaoHelper.fileExists(auditFileName))
                DaoHelper.createNewFile(auditFileName);
            PrintWriter output =
                    new PrintWriter(
                        new FileWriter(auditFileName, true));
            final String entry = timeStamp + ": " + message;
            output.println(entry);
            output.flush();
            output.close();
        } catch(IOException error) {
            System.out.println("-_- Could not write audit entry to file");
        }
    }
}
