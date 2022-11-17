/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Vending Machine with Spring DI
 * 
 * @description TEST STUB: This class implements the audit file of our 
 * Flooring Services App that keeps track of when users purchased items
 */
@Component
public class AuditDaoFileStubImpl implements AuditDao {
    final private String DATE_FORMAT_PATTERN = "yyyy-MM-dd hh:mm:ss";
    private String auditFileName;
    
    public AuditDaoFileStubImpl() {
        auditFileName = "audit.txt";
    }
    @Autowired
    public AuditDaoFileStubImpl(@Value("test-audit.txt")String auditFileName) {
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
    
    public String getLastAuditEntry() {
        String mostRecentEntry = "";
        try {
            Scanner scanner = new Scanner(
                                new BufferedReader(
                                    new FileReader(auditFileName)));
             while(scanner.hasNextLine()) {
                 final String currentEntry = scanner.nextLine();
                 mostRecentEntry = currentEntry;
             }
             scanner.close();
        } catch(FileNotFoundException error) {
            System.out.println("-_- Could not find audit file");
        }
        return mostRecentEntry;
    }
}
