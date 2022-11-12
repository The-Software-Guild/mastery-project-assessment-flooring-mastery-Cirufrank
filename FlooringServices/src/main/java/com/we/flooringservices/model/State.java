/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class acts of the model for a state within the application
 */
public class State {
    private int stateId;
    private String stateAbbrv, stateName;
    private BigDecimal taxRate;
    
    public State(int stateId, String stateAbbrv, 
            String stateName, BigDecimal taxRate) {
        this.stateId = stateId;
        this.stateAbbrv = stateAbbrv;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }
    
    public int getStateId() {
        return stateId;
    }
    
    public String getStateAbbrv() {
        return stateAbbrv;
    }
    public String getStateName() {
        return stateName;
    }
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    @Override
    public String toString() {
        return "State{" + "stateId=" + stateId + ", stateAbbrv=" + stateAbbrv + ", stateName=" + stateName + ", taxRate=" + taxRate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.stateId;
        hash = 71 * hash + Objects.hashCode(this.stateAbbrv);
        hash = 71 * hash + Objects.hashCode(this.stateName);
        hash = 71 * hash + Objects.hashCode(this.taxRate);
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
        final State other = (State) obj;
        if (this.stateId != other.stateId) {
            return false;
        }
        if (!Objects.equals(this.stateAbbrv, other.stateAbbrv)) {
            return false;
        }
        if (!Objects.equals(this.stateName, other.stateName)) {
            return false;
        }
        return Objects.equals(this.taxRate, other.taxRate);
    }
    
}
