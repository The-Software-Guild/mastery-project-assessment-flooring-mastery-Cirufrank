/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.model;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class acts as a model for the StateRequest class which 
 * represents the information logged to the StareRequests file when a user tries 
 * to order from a state that the flooring services do not yet sell to
 */

public class StateRequest {
    final private static int INCREMENTER = 1, ONE_REQUEST = 1;
    private int stateId, totalRequests;
    private String stateAbbreviation, stateName;
    private Availability availability;
    
    public StateRequest(String stateAbbreviation, String stateName, 
            Availability availability, int currentRequests, int totalStates) {
        this.stateId = totalStates + INCREMENTER;
        this.stateName = stateName;
        this.availability = availability;
        this.totalRequests = currentRequests + ONE_REQUEST;
    }

    public int getStateId() {
        return stateId;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public String getStateName() {
        return stateName;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
    
}
