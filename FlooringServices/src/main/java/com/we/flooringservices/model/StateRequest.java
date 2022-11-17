/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.model;

import java.util.Objects;

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
    
    public StateRequest(int stateId, String stateAbbreviation, String stateName, 
            Availability availability, int currentRequests) {
        this.stateId = stateId;
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.availability = availability;
        this.totalRequests = currentRequests;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.stateId;
        hash = 97 * hash + this.totalRequests;
        hash = 97 * hash + Objects.hashCode(this.stateAbbreviation);
        hash = 97 * hash + Objects.hashCode(this.stateName);
        hash = 97 * hash + Objects.hashCode(this.availability);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StateRequest other = (StateRequest) obj;
        if (this.stateId != other.stateId) {
            return false;
        }
        if (this.totalRequests != other.totalRequests) {
            return false;
        }
        if (!Objects.equals(this.stateAbbreviation, other.stateAbbreviation)) {
            return false;
        }
        if (!Objects.equals(this.stateName, other.stateName)) {
            return false;
        }
        return this.availability == other.availability;
    }

    @Override
    public String toString() {
        return "StateRequest{" + "stateId=" + stateId + ", totalRequests=" + totalRequests + ", stateAbbreviation=" + stateAbbreviation + ", stateName=" + stateName + ", availability=" + availability + '}';
    }
    
}
