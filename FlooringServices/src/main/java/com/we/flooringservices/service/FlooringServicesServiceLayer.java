/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.flooringservices.service;

import com.we.flooringservices.dao.FlooringServicesDaoPersistenceException;
import com.we.flooringservices.model.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author ciruf
 */
public interface FlooringServicesServiceLayer {
    /**
     * Ensures there are orders to display and returns those orders to user
     *
     * @param LocalDateTime date of the orders to view
     * @return List<Order> list of order object representing the orders 
     * placed on the date specified
     */
    public List<Order> getOrders(LocalDateTime orderDate) throws FlooringServicesNoOrdersFoundExeception,
            FlooringServicesDaoPersistenceException;
    /**
     * Allows user to add new Order
     *UPDATE PARAMS
     * @param Order Order object instance representing order to add
     * @return void
     */
    public void addOrder(LocalDateTime orderdate, String customerName,
            String orderState, String orderProductType, 
            BigDecimal orderArea) throws FlooringServicesDaoPersistenceException,
            FlooringServicesNoOrdersFoundExeception;
    /**
     * Determines if input state is available to purchase services for
     *
     * @param String abbreviation of state the user is attempting to 
     * purchase orders from
     * @return boolean
     */
    public boolean isStateAvailable(String stateAbbrv) 
    throws FlooringServicesDaoPersistenceException;
    /**
     * Returns a list of all product types currently
     * available for purchase
     *
     * @param None
     * @return List<String> order available product types
     */
    public List<String> getAvailableProductTypes() 
        throws FlooringServicesDaoPersistenceException;
    /**
     * Logs to a data file the state that has currently 
     * been requested to purchase services from
     *
     * @param String abbreviation of state to log request 
     * for
     * @return void
     */
    public void logStateRequest(String stateAbbrv) 
    throws FlooringServicesDaoPersistenceException;
}
