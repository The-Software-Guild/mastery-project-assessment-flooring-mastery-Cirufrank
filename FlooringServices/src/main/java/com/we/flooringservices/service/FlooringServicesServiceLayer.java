/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.flooringservices.service;

import com.we.flooringservices.dao.FlooringServicesDaoPersistenceException;
import com.we.flooringservices.model.Order;
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
     *
     * @param Order Order object instance representing order to add
     * @return void
     */
    public void addOrder(Order order) throws FlooringServicesDaoPersistenceException,
            FlooringServicesNoOrdersFoundExeception;
    /**
     * Determines if input state is available to purchase services for
     *
     * @param String abbreviation of state the user is attempting to 
     * purchase orders from
     * @return boolean
     */
    public boolean isStateAvailale(String stateAbbrv) 
    throws FlooringServicesDaoPersistenceException;
}
