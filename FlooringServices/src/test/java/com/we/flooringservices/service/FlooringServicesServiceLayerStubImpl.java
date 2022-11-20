/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.service;

import com.we.flooringservices.dao.AuditDao;
import com.we.flooringservices.dao.FlooringServicesDaoPersistenceException;
import com.we.flooringservices.dao.OrderDao;
import com.we.flooringservices.dao.ProductDao;
import com.we.flooringservices.dao.StateDao;
import com.we.flooringservices.dao.StateRequestDao;
import com.we.flooringservices.dao.TestDaoHelper;
import com.we.flooringservices.model.Availability;
import com.we.flooringservices.model.Order;
import com.we.flooringservices.model.Product;
import com.we.flooringservices.model.ServiceCalculator;
import com.we.flooringservices.model.State;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description TEST STUB: This is the implementation of the application's 
 * Service Layer interface and defines the methods responsible for the Flooring
 * Services business logic of our application
 * 
 * These methods should be available to create, read, update, and delete 
 * orders with custom validation that makes sense for our 
 * application
 */
@Component
public class FlooringServicesServiceLayerStubImpl implements FlooringServicesServiceLayer {
    @Autowired
    @Qualifier("orderDaoFileStubImpl")
    private OrderDao orderDao;
    @Autowired
    @Qualifier("stateDaoFileStubImpl")
    private StateDao stateDao;
    @Autowired
    @Qualifier("productDaoFileStubImpl")
    private ProductDao productDao;
    @Autowired
    @Qualifier("stateRequestDaoFileStubImpl")
    private StateRequestDao requestDao;
    @Autowired
    @Qualifier("auditDaoFileStubImpl")
    private AuditDao auditDao;
    
    public FlooringServicesServiceLayerStubImpl(OrderDao orderDao, StateDao stateDao,
            ProductDao productDao, StateRequestDao requestDao,
            AuditDao auditDao) {
        this.orderDao = orderDao;
        this.stateDao = stateDao;
        this.productDao = productDao;
        this.requestDao = requestDao;
        this.auditDao = auditDao;
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
        auditDao.writeAuditEntry("Orders retrieved for date " + orderDate.toString());
        return ordersForDate;
    }
    public List<Order> getAllOrders() 
        throws FlooringServicesNoOrdersFoundExeception,
            FlooringServicesDaoPersistenceException {
        final List<Order> allOrdersExported = orderDao.getAllOrders();
        return allOrdersExported;
    }
    
    @Override
    public List<Order> getAllExportedOrders() 
        throws FlooringServicesNoOrdersFoundExeception,
            FlooringServicesDaoPersistenceException {
        final List<Order> allOrdersExported = orderDao.getAllExportedOrders();
        auditDao.writeAuditEntry("Exported orders retrieved");
        return allOrdersExported;
    }
    @Override
    public void exportAllOrders() 
    throws FlooringServicesNoOrdersFoundExeception,
            FlooringServicesDaoPersistenceException {
        orderDao.exportAllActiveOrders();
    }
    @Override
    public boolean orderExistsForDate(int orderNumber, LocalDateTime orderDate) 
        throws 
            FlooringServicesDaoPersistenceException {
        try {
            boolean orderExistsForDate = false;
            final List<Order> ordersForDate = getOrders(orderDate);
            for (Order currentOrder: ordersForDate) {
                final int currentOrderNumber = currentOrder.getOrderNumber();
                if (currentOrderNumber == orderNumber)
                    orderExistsForDate = true;
            }
            return orderExistsForDate;
        } catch(FlooringServicesNoOrdersFoundExeception error) {
            return false;
        }
    }
    @Override
    public Order getOrder(int orderNumber) {
        Order order;
        try {
            order = orderDao.getOrder(orderNumber);
        } catch(FlooringServicesNoOrdersFoundExeception error) {
            order = null;
        }
        return order;
        
    }
    @Override
    public boolean orderExists(int orderNumber) {
        try {
            final List<Order> allOrders = orderDao.getAllOrders();
            boolean orderExists = false;
            for (Order currentOrder: allOrders) {
                final int currentOrderNumber = currentOrder.getOrderNumber();
                if (currentOrderNumber == orderNumber)
                    orderExists = true;
            }
            return orderExists;
        } catch(FlooringServicesNoOrdersFoundExeception error){
            return false; 
        }   
    }
    @Override
    public void addOrder(Order order) 
    throws FlooringServicesDaoPersistenceException,
         FlooringServicesNoOrdersFoundExeception   {   
        orderDao.addOrder(order);
        auditDao.writeAuditEntry("Order added: " + order.toString());
    }
    @Override
    public Order createOrder(LocalDateTime orderDate, String customerName,
            String orderState, String orderProductType, 
            BigDecimal orderArea) 
    throws FlooringServicesDaoPersistenceException,
         FlooringServicesNoOrdersFoundExeception   {   
        final State state = stateDao.getState(orderState);
        final BigDecimal taxRate = state.getTaxRate();
        final Product product = productDao.getProduct(orderProductType);
        final BigDecimal costPerSquareFoot = product.getCostPerSquareFoot();
        final BigDecimal laborCostPerSquareFoot = product.getLaborCostPerSquareFoot();
        final ServiceCalculator serviceCalculator = new ServiceCalculator(orderArea, costPerSquareFoot,
            laborCostPerSquareFoot, taxRate);
        final BigDecimal materialCost = serviceCalculator.getMaterialCost();
        final BigDecimal laborCost = serviceCalculator.getLaborCost();
        final BigDecimal tax = serviceCalculator.getTax();
        final BigDecimal total = serviceCalculator.getTotalCost();
        final List<Order> allOrders = orderDao.getAllOrders();
        Order order = new Order(customerName, orderState,
                    taxRate, orderProductType, orderArea,
                     costPerSquareFoot, laborCostPerSquareFoot,
                    materialCost, laborCost, tax,
                    total, orderDate, allOrders.size());
        return order;
    }
    @Override
    public void updateOrder(Order order) throws FlooringServicesDaoPersistenceException,
           FlooringServicesNoOrdersFoundExeception {
           orderDao.updateOrder(order);
           auditDao.writeAuditEntry("Order updated: " + order.toString());
        
    }
    @Override
    public Order updateOrderCalculations(Order order, boolean recalculate) throws FlooringServicesDaoPersistenceException  {  
        if (recalculate == false) {
            return order;
        }
        final State state = stateDao.getState(order.getState());
        final BigDecimal taxRate = state.getTaxRate();
        final Product product = productDao.getProduct(order.getProductType());
        final BigDecimal costPerSquareFoot = product.getCostPerSquareFoot();
        final BigDecimal laborCostPerSquareFoot = product.getLaborCostPerSquareFoot();
        final ServiceCalculator serviceCalculator = new ServiceCalculator(order.getArea(), costPerSquareFoot,
            laborCostPerSquareFoot, taxRate);
        final BigDecimal materialCost = serviceCalculator.getMaterialCost();
        final BigDecimal laborCost = serviceCalculator.getLaborCost();
        final BigDecimal tax = serviceCalculator.getTax();
        final BigDecimal total = serviceCalculator.getTotalCost();
        Order updatedOrder = new Order(order.getOrderNumber(),order.getCustomerName(), 
                order.getState(),
                    taxRate, order.getProductType(), order.getArea(),
                     costPerSquareFoot, laborCostPerSquareFoot,
                    materialCost, laborCost, tax,
                    total, order.getOrderDate());
        return updatedOrder;
    }
    @Override
    public void removeOrder(Order orderToRemove) 
    throws FlooringServicesDaoPersistenceException,
            FlooringServicesNoOrdersFoundExeception {
        orderDao.removeOrder(orderToRemove);
        auditDao.writeAuditEntry("Order removed: " + orderToRemove.toString());
    }
    @Override
    public boolean isStateAvailable(String stateAbbrv) 
    throws FlooringServicesDaoPersistenceException{
        final List<State> currentStates = stateDao.getAllStates();
        final List<String> stateAbbrvs = currentStates.stream().
                map(state -> state.getStateAbbrv()).collect(Collectors.toList());
        return stateAbbrvs.contains(stateAbbrv);
        
    }
    @Override
    public List<String> getAvailableProductTypes() 
        throws FlooringServicesDaoPersistenceException{
        final List<Product> allProducts = new ArrayList<>(productDao.getAllProducts());
        final List<String> productTypes =
                allProducts.stream().
                        filter(product ->
                                product.getProductStatus() == Availability.AVAILABLE).map(product -> product.getProductType())
                .collect(Collectors.toList());
        return productTypes;
                
        
    }
    @Override
    public void logStateRequest(String stateAbbrv) 
    throws FlooringServicesDaoPersistenceException{
        requestDao.logStateRequest(stateAbbrv);
        auditDao.writeAuditEntry("Request logged for services "
                + "to be expanded to state: " + stateAbbrv);
    }
    
}
