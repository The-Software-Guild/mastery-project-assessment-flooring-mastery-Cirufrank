/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.model;

import java.math.BigDecimal;

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
    
}
