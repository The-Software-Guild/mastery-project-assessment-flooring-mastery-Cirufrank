/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.flooringservices.dao;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This interface provides the methods needed to log 
 * that services have been requested from a state not currently available
 * to purchase services from
 */
public interface StateRequestDao {
    /**
     * Log a request to the state specified
     *
     * @param String Abbreviation of the state to log the 
     * request to
     * @return void
     */
    public void logStateRequest(String stateAbbrv) throws 
            FlooringServicesDaoPersistenceException;
}
