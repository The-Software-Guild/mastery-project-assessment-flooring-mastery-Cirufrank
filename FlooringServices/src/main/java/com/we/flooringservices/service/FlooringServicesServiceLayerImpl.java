/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.service;

import com.we.flooringservices.dao.FlooringServicesDaoPersistenceException;
import com.we.flooringservices.dao.OrderDao;
import com.we.flooringservices.dao.ProductDao;
import com.we.flooringservices.dao.StateDao;
import com.we.flooringservices.dao.StateRequestDao;
import com.we.flooringservices.model.Availability;
import com.we.flooringservices.model.Order;
import com.we.flooringservices.model.Product;
import com.we.flooringservices.model.ServiceCalculator;
import com.we.flooringservices.model.State;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private ProductDao productDao;
    @Autowired
    private StateRequestDao requestDao;
    
    public FlooringServicesServiceLayerImpl(OrderDao orderDao, StateDao stateDao,
            ProductDao productDao, StateRequestDao requestDao) {
        this.orderDao = orderDao;
        this.stateDao = stateDao;
        this.productDao = productDao;
        this.requestDao = requestDao;
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
    public void addOrder(LocalDateTime orderDate, String customerName,
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
        orderDao.addOrder(order);
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
    }
}
