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
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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

@Component
@Primary
public class StateDaoFileImpl implements StateDao {
    final private static String STATE_ID_HEADER = "stateId",
            STATE_ABBRV_HEADER = "stateAbbrv",
            STATE_NAME_HEADER = "stateName",
            STATE_TAXES_HEADER = "taxRate",
            stateHeadersLine = DaoHelper.createDelimiterSeparatedString(
                    DaoHelper.DELIMITER,STATE_ID_HEADER,
                    STATE_ABBRV_HEADER,STATE_NAME_HEADER,
                    STATE_TAXES_HEADER);
    private static String taxesFileName;
    private Map<String, State> allStates = new HashMap<>();
    
    public StateDaoFileImpl() {
        taxesFileName  = "Data/Taxes.txt";
    }
    
    public StateDaoFileImpl(String taxesFileName) {
        this.taxesFileName = taxesFileName;
    }
    
    @Override
    public State getState(String stateAbbrv) 
        throws FlooringServicesDaoPersistenceException {
        loadStates();
        State stateRetrieved = allStates.get(stateAbbrv);
        return stateRetrieved;
    }
    
    @Override
    public List<State> getAllStates() 
        throws FlooringServicesDaoPersistenceException{
        loadStates();
        final List<State> currentStates = new ArrayList<>(allStates.values());
        return currentStates;
    }
    
    @Override
    public void addState(State state) 
        throws FlooringServicesDaoPersistenceException{
        loadStates();
        allStates.put(state.getStateAbbrv(), state);
        writeStates();
    }
    
    private State unMarshallState(String stringOfState) {
        final State currentState;
        final int ID_INDEX = 0, ABBRV_INDEX = 1, NAME_INDEX = 2, 
                TAX_RATE_INDEX = 3;
        final String[] stateTokens = stringOfState.split(DaoHelper.DELIMITER);
        final int stateId = Integer.valueOf(stateTokens[ID_INDEX]);
        final String stateAbbrv = stateTokens[ABBRV_INDEX];
        final String stateName = stateTokens[NAME_INDEX];
        final BigDecimal taxRate = new BigDecimal(stateTokens[TAX_RATE_INDEX]);
        currentState = new State(stateId, stateAbbrv, stateName, taxRate);
        return currentState;
    }
    
    private String marshallState(State state) {
        final String stateAsText = DaoHelper.createDelimiterSeparatedString(
                DaoHelper.DELIMITER,
                Integer.toString(state.getStateId()), 
                state.getStateAbbrv(),
                state.getStateName(),
                state.getTaxRate().toString());
        return stateAsText;
    }
    
    private void loadStates() 
        throws FlooringServicesDaoPersistenceException{
        
        try {
            Scanner scanner = new Scanner(
                                new BufferedReader(
                                    new FileReader(taxesFileName)));
            //Ensures we don't include the header cells in 
            //our results
            scanner.nextLine();
            while(scanner.hasNextLine()) {
                final State currentState = unMarshallState(scanner.nextLine()); 
                allStates.put(currentState.getStateAbbrv(), currentState);
            }
            scanner.close();
        } catch(FileNotFoundException error) {
            throw new FlooringServicesDaoPersistenceException("-_- Could not load states from file");
        }
        
    }
    
    private void writeStates() 
        throws FlooringServicesDaoPersistenceException {
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
            throw new FlooringServicesDaoPersistenceException("-_- Could not write states to file");
        }
    }  
    
}
