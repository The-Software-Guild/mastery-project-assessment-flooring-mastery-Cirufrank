/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.State;
import java.util.List;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description
 */
public interface StateDao {
    /**
     * Returns a state object filled with the state's current
     * tax and id information from the database
     *
     * @param stateAbbrv String of the state's abbreviation
     * @return the State object associated with the given abbreviation if
     * it exists within the Taxes file, null otherwise
     */
    public State getState(int StateId);
    
    /**
     * Returns a List of all the states available to order flooring 
     * services and product from
     *
     * @return a List of State objects constructed from their information
     * within the Taxes file
     */
    public List<State> getAllStates();
    
    /**
     * Writes a new state to the Taxes file
     *
     * @param stateId int representing the id of the state
     * (the id is passed in since it must be consistent with
     * the StateRequest id of the state
     * 
     * @return void
     */
    public void addState(State state);
}
