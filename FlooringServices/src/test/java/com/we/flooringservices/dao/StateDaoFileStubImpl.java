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
 * @author ciruf
 */
public class StateDaoFileStubImpl implements StateDao {
    final private static String DELIMITTER = ",";
    private static String taxesFileName;
    private Map<Integer, State> allStates = new HashMap<>();
    
    public StateDaoFileStubImpl() {
        taxesFileName  = "Data/Taxes.txt";
    }
    
    public StateDaoFileStubImpl(@Value("TestDate/Test-Taxes.txt") String taxesFileName) {
        this.taxesFileName = taxesFileName;
    }
    
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
                                    new FileReader(taxesFileName)));
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
                                    new FileWriter(taxesFileName));
        } catch(IOException error) {
            System.out.println("-_- Could not write states to file");
        }
    }  
    
}

