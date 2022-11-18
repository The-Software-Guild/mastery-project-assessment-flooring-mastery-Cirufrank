/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.Availability;
import com.we.flooringservices.model.State;
import com.we.flooringservices.model.StateRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @description This class implements the StateRequestDao interface and provides 
 * the methods needed to log that services have been requested from a state not 
 * currently available to purchase services from
 */

@Component
@Primary
public class StateRequestDaoFileImpl implements StateRequestDao {
    final private static String STATE_ID_HEADER = "stateId",
            STATE_ABBRV_HEADER = "stateAbbrv",
            STATE_NAME_HEADER = "stateName",
            STATE_AVAILABILITY_HEADER = "availability",
            TOTAL_REQUESTS_HEADER = "totalRequests",
            stateRequestsHeadersLine = DaoHelper.createDelimiterSeparatedString(
                    DaoHelper.DELIMITER,STATE_ID_HEADER,
                    STATE_ABBRV_HEADER,STATE_AVAILABILITY_HEADER,
                    TOTAL_REQUESTS_HEADER);
    final private static int ONE_REQUEST = 1;
    private String stateRequestsFileName;
    private Map<String, StateRequest> allRequests = new HashMap<>();
    
    public StateRequestDaoFileImpl() {
        stateRequestsFileName  = "Data/StateRequests.txt";
    }
    
    public StateRequestDaoFileImpl(String stateRequestsFileName) {
        this.stateRequestsFileName = stateRequestsFileName;
    }
    
    public void logStateRequest(String stateAbbrv) 
        throws FlooringServicesDaoPersistenceException{
        loadAllRequests();
        final StateRequest request = allRequests.get(stateAbbrv);
        final int updatedRequests = request.getTotalRequests() + ONE_REQUEST;
        request.setTotalRequests(updatedRequests);
        allRequests.put(stateAbbrv, request);
        writeRequestsToFile();
    }
    
    private StateRequest unMarshallStateRequest(String requestAsText) {
        final int ID_INDEX = 0, ABBREVIATION_INDEX = 1, NAME_INDEX = 2,
                AVAILABILITY_INDEX = 3, TOTAL_INDEX = 4;
        final String[] requestTokens = requestAsText.split(DaoHelper.DELIMITER);
        final StateRequest request = new StateRequest(
                Integer.parseInt(requestTokens[ID_INDEX]),
                requestTokens[ABBREVIATION_INDEX],
                requestTokens[NAME_INDEX],
                Availability.valueOf(requestTokens[AVAILABILITY_INDEX]),
                Integer.parseInt(requestTokens[TOTAL_INDEX])
            );
        return request;
    }
    
    private String marshallStateRequest(StateRequest request) {
        final String requestAsText = 
                DaoHelper.createDelimiterSeparatedString(
                        DaoHelper.DELIMITER, 
                        Integer.toString(request.getStateId()),
                        request.getStateAbbreviation(),
                        request.getStateName(),
                        request.getAvailability().toString(),
                        Integer.toString(request.getTotalRequests()));
        return requestAsText;                   
    }
     private void loadAllRequests() 
        throws FlooringServicesDaoPersistenceException{
         try {
             Scanner scanner = new Scanner(
                                    new BufferedReader(
                                        new FileReader(stateRequestsFileName)));
             //Here so the header line is not parsed to StateRequest
             scanner.nextLine();
             while(scanner.hasNextLine()) {
                 final String requestAsText = scanner.nextLine();
                 final StateRequest request = unMarshallStateRequest(requestAsText);
                 allRequests.put(request.getStateAbbreviation(), request);
             }
             scanner.close();
         } catch(FileNotFoundException error) {
             throw new FlooringServicesDaoPersistenceException("-_- Could not load state requests");
         }
     }
     
     private void writeRequestsToFile() 
        throws FlooringServicesDaoPersistenceException{
         final List<StateRequest> requests = new ArrayList<>(allRequests.values());
         try {
             PrintWriter output = 
                     new PrintWriter(
                        new FileWriter(stateRequestsFileName));
             //Prints the headers line to file once
             output.println(stateRequestsHeadersLine);
             for (StateRequest currentRequest: requests) {
                 final String requestAsText = marshallStateRequest(currentRequest);
                 output.println(requestAsText);
                 output.flush();
             }
             output.close();
         } catch(IOException error) {
             throw new FlooringServicesDaoPersistenceException("-_- Could not save stae Requests to file");
         }
     }
}
