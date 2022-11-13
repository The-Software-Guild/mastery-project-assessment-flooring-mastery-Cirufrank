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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description STUB FILE FOR TESTING: This class represents a file implementation of the 
 * StateDao interface and provides the methods needed to read and 
 * write information to and from the Taxes file
 */

@Component
public class StateDaoFileStubImpl implements StateDao {
    final private static String DELIMITER = ",", 
            STATE_ID_HEADER = "stateId",
            STATE_ABBRV_HEADER = "stateAbbrv",
            STATE_NAME_HEADER = "stateName",
            STATE_TAXES_HEADER = "taxRate",
            stateHeadersLine = STATE_ID_HEADER +
            DELIMITER + STATE_ABBRV_HEADER +
            DELIMITER + STATE_NAME_HEADER
            + DELIMITER + STATE_TAXES_HEADER;
    private static String taxesFileName;
    private Map<Integer, State> allStates = new HashMap<>();
    
    public StateDaoFileStubImpl() {
        taxesFileName  = "Data/Taxes.txt";
    }
    
    @Autowired
    public StateDaoFileStubImpl(@Value("TestData/Test-Taxes.txt") String taxesFileName) {
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
    //For testing and for availability if we are to create
    //an admin role for the application
    public void removeState(int stateId) {
        loadStates();
        allStates.remove(stateId);
        writeStates();
    }
    
    private State unMarshallState(String stringOfState) {
        final State currentState;
        final int ID_INDEX = 0, ABBRV_INDEX = 1, NAME_INDEX = 2, 
                TAX_RATE_INDEX = 3;
        final String[] stateTokens = stringOfState.split(DELIMITER);
        String test = stateTokens[ID_INDEX];
        final int stateId = Integer.parseInt(stateTokens[ID_INDEX]);
        final String stateAbbrv = stateTokens[ABBRV_INDEX];
        final String stateName = stateTokens[NAME_INDEX];
        final BigDecimal taxRate = new BigDecimal(stateTokens[TAX_RATE_INDEX]);
        currentState = new State(stateId, stateAbbrv, stateName, taxRate);
        return currentState;
    }
    
    private String marshallState(State state) {
        final String stateAsText = DaoHelper.createDelimiterSeparatedString(DELIMITER,
                Integer.toString(state.getStateId()), 
                state.getStateAbbrv(),
                state.getStateName(),
                state.getTaxRate().toString());
        return stateAsText;
    }
    
    private void loadStates() {
        
        try {
            Scanner scanner = new Scanner(
                                new BufferedReader(
                                    new FileReader(taxesFileName)));
            //Ensures we don't include the header cells in 
            //our results
            scanner.nextLine();
            while(scanner.hasNextLine()) {
                final State currentState = unMarshallState(scanner.nextLine()); 
                allStates.put(currentState.getStateId(), currentState);
            }
            scanner.close();
        } catch(FileNotFoundException error) {
            System.out.println("-_- Could not load states from file");
            System.out.println(error.getMessage());
        }
        
    }
    
    private void writeStates() {
        final ArrayList<State> currentStates = new ArrayList<>(allStates.values());
        try {
            PrintWriter output = new PrintWriter(
                                    new FileWriter(taxesFileName));
            //Write the first line as the headers to the columns
            output.println(stateHeadersLine);
            for (State currentState : currentStates) {
                final String stateAsText = marshallState(currentState);
                output.println(stateAsText);
                output.flush();
            }
            output.close();
        } catch(IOException error) {
            System.out.println("-_- Could not write states to file");
        }
    }      
    
    
}

