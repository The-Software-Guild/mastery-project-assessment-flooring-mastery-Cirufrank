/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.flooringservices.service;

import com.we.flooringservices.model.Order;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author ciruf
 */
public interface FlooringServicesServiceLayer {
    public List<Order> displayOrders(LocalDateTime orderDate);
    public void addOrder(Order order);
}
