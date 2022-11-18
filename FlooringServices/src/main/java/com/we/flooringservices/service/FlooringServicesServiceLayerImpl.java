/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.service;

import com.we.flooringservices.dao.FlooringServicesDaoPersistenceException;
import com.we.flooringservices.dao.OrderDao;
import com.we.flooringservices.dao.ProductDao;
import com.we.flooringservices.dao.StateDao;
import com.we.flooringservices.model.Order;
import com.we.flooringservices.model.State;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author ciruf
 */

@Component
@Primary
public class FlooringServicesServiceLayerImpl implements FlooringServicesServiceLayer {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StateDao stateDao;
    
    public FlooringServicesServiceLayerImpl(OrderDao orderDao, StateDao stateDao) {
        this.orderDao = orderDao;
        this.stateDao = stateDao;
    }
    public List<Order> getOrders(LocalDateTime orderDate) throws 
            FlooringServicesNoOrdersFoundExeception,
            FlooringServicesDaoPersistenceException{
        final int NO_ORDERS = 0;
        final List<Order> ordersForDate = orderDao.getAllOrdersForDate(orderDate);
        if (ordersForDate.size() == NO_ORDERS) {
            throw new FlooringServicesNoOrdersFoundExeception("No orders found for "
                    + "date specified");
        }
        return ordersForDate;
    }
    @Override
    public void addOrder(Order order) 
    throws FlooringServicesDaoPersistenceException,
         FlooringServicesNoOrdersFoundExeception   {       
        orderDao.addOrder(order);
    }
    
    public boolean isStateAvailale(String stateAbbrv) 
    throws FlooringServicesDaoPersistenceException{
        final List<State> currentStates = stateDao.getAllStates();
        final List<String> stateAbbrvs = currentStates.stream().
                map(state -> state.getStateAbbrv()).collect(Collectors.toList());
        return stateAbbrvs.contains(stateAbbrv);
        
    }
}
