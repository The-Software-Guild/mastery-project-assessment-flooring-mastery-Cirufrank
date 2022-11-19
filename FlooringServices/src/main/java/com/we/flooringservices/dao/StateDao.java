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
 * @description This interface declares the methods needed to read and 
 * write information to and from the Taxes file
 */
public interface StateDao {
    /**
     * Returns a state object filled with the state's current
     * tax and id information from the database
     *CHANGE
     * @param stateId int of the state's id within the Taxes file
     * @return the State object associated with the given id if
     * it exists within the Taxes file, null otherwise
     */
    public State getState(String stateAbbrv)
            throws FlooringServicesDaoPersistenceException;
    
    /**
     * Returns a List of all the states available to order flooring 
     * services and product from
     *
     * @param N/A
     * @return a List of State objects constructed from their information
     * within the Taxes file
     */
    public List<State> getAllStates()
            throws FlooringServicesDaoPersistenceException;
    
    /**
     * Writes a new state to the Taxes file
     *
     * @param State state object of the state to add,
     * thus providing us all of the needed information to 
     * add the state due to the State object's public 
     * interface
     * 
     * @return void
     */
    public void addState(State state)
            throws FlooringServicesDaoPersistenceException;
}
