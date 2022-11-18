/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.service;

import com.we.flooringservices.dao.FlooringServicesDaoPersistenceException;
import com.we.flooringservices.dao.OrderDao;
import com.we.flooringservices.model.Order;
import java.time.LocalDateTime;
import java.util.List;
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
    FlooringServicesServiceLayerImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
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
}
