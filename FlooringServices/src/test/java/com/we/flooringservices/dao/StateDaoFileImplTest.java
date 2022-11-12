/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.State;
import java.math.BigDecimal;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class tests the StateDaoFileImpl class which 
 * provides the methods needed to read and 
 * write information to and from the Taxes file
 */

@ExtendWith(StateDaoFileImplParameterResolver.class)
@ExtendWith(StateParameterResolver.class)
@TestMethodOrder(OrderAnnotation.class)
public class StateDaoFileImplTest {
    
    public StateDaoFileImplTest() {
    }

    @Test
    @Order(1)
    public void testGetAllStates(State stateToAdd, StateDaoFileStubImpl testStateDao) {
        final int TOTAL_AVAILABLE_STATES_INT = 26, ONE_STATE = 1;
        ArrayList<State> allStates = new ArrayList<>(
                testStateDao.getAllStates());
        for (State currentState : allStates) {
            System.out.println(currentState.toString());
        }
        //Optimization goals: use async await operations to 
        //ensure this test resolves all of its methods before 
        //the other tests are ran so that this if-else statement
        //and the Order annotations are not needed
        if (testStateDao.getState(stateToAdd.getStateId()) == null) {
            assertEquals(TOTAL_AVAILABLE_STATES_INT, allStates.size());
        } else {
            assertEquals(TOTAL_AVAILABLE_STATES_INT + ONE_STATE, allStates.size());
        }    
    }
    
    @ParameterizedTest
    @ValueSource(ints = {5})
    @Order(2)
    public void testGetState(int stateId, StateDaoFileStubImpl testStateDao) {
        final State accurateState = new State(stateId, "CA", "California", new BigDecimal("4.60"));
        final State testState = testStateDao.getState(stateId);
        assertEquals(accurateState, testState);
    }
    
    
    @Test
    @Order(3)
    public void testAddState(State stateToAdd, StateDaoFileStubImpl testStateDao) {
        testStateDao.addState(stateToAdd);
        final State stateJustAdded = 
                testStateDao.getState(
                        stateToAdd.getStateId());
        assertEquals(stateToAdd, stateJustAdded);
        
    }
    
    @Test
    @Order(4)
    public void testRemoveState(State stateToRemove, StateDaoFileStubImpl testStateDao) {
        testStateDao.removeState(stateToRemove.getStateId());
        assertTrue(testStateDao.getState(stateToRemove.getStateId()) == null);
    }
    
}
