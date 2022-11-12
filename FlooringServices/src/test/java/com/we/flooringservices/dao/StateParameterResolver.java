/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.State;
import java.math.BigDecimal;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author ciruf
 */
public class StateParameterResolver implements ParameterResolver {
    final static int MONTANA_ID = 27;
    private State stateToAdd = new State(MONTANA_ID, 
            "MT", "Montana", 
            new BigDecimal("4.75"));
    
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
       return parameterContext.getParameter().getType() == State.class;
    }
    
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return stateToAdd;
    }
}


