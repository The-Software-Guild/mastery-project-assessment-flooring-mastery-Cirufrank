/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.StateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class tests the StateRequestDaoFileImpl class which 
 * provides the methods needed to read and 
 * write information to and from the StateRequests file
 */

@ExtendWith(StateRequestDaoFileImplParameterResolver.class)
public class StateRequestDaoFileImplTest {
    
    public StateRequestDaoFileImplTest() {
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
     * Test of logStateRequest method, of class StateRequestDaoFileImpl.
     */
    @Test
    public void testLogStateRequest(StateRequestDaoFileStubImpl testRequestDao) {
        final String stateAbbrv = "CA";
        final StateRequest prevRequest = testRequestDao.getRequest(stateAbbrv);
        final int ONE_REQUEST = 1;
        testRequestDao.logStateRequest(stateAbbrv);
        final StateRequest newRequest = testRequestDao.getRequest(stateAbbrv);
        assertEquals(prevRequest.getTotalRequests(), newRequest.getTotalRequests() - ONE_REQUEST);
    }
    
}
