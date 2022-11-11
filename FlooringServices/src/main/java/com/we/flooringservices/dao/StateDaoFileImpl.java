/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class represents a file implementation of the 
 * StateDao interface and provides the methods needed to read and 
 * write information to and from the Taxes file
 */

public class StateDaoFileImpl implements StateDao {
    final private static String DELIMITTER = ",", 
            TAXES_FILE_NAME = "Data/Taxes.txt";
    private Map<Integer, State> allStates = new HashMap<>();
    
    @Override
    public State getState(int stateId) {
        loadStates();
        State stateRetrieved = allStates.get(stateId);
        return stateRetrieved;
    }
    
    @Override
    public List<State> getAllStates() {
        loadStates();
        final List<State> currentStates = new ArrayList<>(allStates.values());
        return currentStates;
    }
    
    @Override
    public void addState(State state) {
        loadStates();
        allStates.put(state.getStateId(), state);
        writeStates();
    }
    
    private State unMarshallState(String stringOfState) {
        final State currentState;
        final int ID_INDEX = 0, ABBRV_INDEX = 1, NAME_INDEX = 2, 
                TAX_RATE_INDEX = 3;
        final String[] stateTokens = stringOfState.split(DELIMITTER);
        final int stateId = Integer.valueOf(stateTokens[ID_INDEX]);
        final String stateAbbrv = stateTokens[ABBRV_INDEX];
        final String stateName = stateTokens[NAME_INDEX];
        final BigDecimal taxRate = new BigDecimal(stateTokens[TAX_RATE_INDEX]);
        currentState = new State(stateId, stateAbbrv, stateName, taxRate);
        return currentState;
    }
    
    private String marshallState(State state) {
        String stateAsText = ""; 
        stateAsText += state.getStateId() + DELIMITTER;
        stateAsText += state.getStateAbbrv() + DELIMITTER;
        stateAsText += state.getStateName() + DELIMITTER;
        stateAsText += state.getTaxRate();
        return stateAsText;
    }
    
    private void loadStates() {
        try {
            Scanner scanner = new Scanner(
                                new BufferedReader(
                                    new FileReader(TAXES_FILE_NAME)));
            while(scanner.hasNextLine()) {
                final State currentState = unMarshallState(scanner.nextLine()); 
                allStates.put(currentState.getStateId(), currentState);
            }
            scanner.close();
        } catch(FileNotFoundException error) {
            System.out.println("-_- Could not load states from file");
        }
        
    }
    
    private void writeStates() {
        try {
            PrintWriter output = new PrintWriter(
                                    new FileWriter(TAXES_FILE_NAME));
        } catch(IOException error) {
            System.out.println("-_- Could not write states to file");
        }
    }  
    
}
