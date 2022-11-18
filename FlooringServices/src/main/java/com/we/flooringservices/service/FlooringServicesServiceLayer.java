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
//    public void addOrder(Order order);
}
