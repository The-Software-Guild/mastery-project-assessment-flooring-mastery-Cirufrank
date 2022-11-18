/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.flooringservices.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class tests the AuditDaoFileImpl class which 
 * provides the methods needed to write information to the audit log
 */
@ExtendWith(AuditDaoFileImplParameterResolver.class)
public class AuditDaoFileImplTest {
    
    public AuditDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of writeAuditEntry method, of class AuditDaoFileImpl.
     */
    @Test
    public void testWriteAuditEntry(AuditDaoFileStubImpl testAuditDao) 
        throws FlooringServicesDaoPersistenceException{
        final Random randGenerator = new Random();
        final String messageWithPadding = "This is a test " + randGenerator.nextInt();
        testAuditDao.writeAuditEntry(messageWithPadding);
        String mostRecentAuditMessage = testAuditDao.getLastAuditEntry();
        assertTrue(mostRecentAuditMessage.contains(messageWithPadding));
        
    }
    
}
