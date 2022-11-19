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
     * @return List<Order> list of order objects representing the orders 
     * placed on the date specified
     */
    public List<Order> getOrders(LocalDateTime orderDate) throws FlooringServicesNoOrdersFoundExeception,
            FlooringServicesDaoPersistenceException;
    /**
     * Ensures there are orders to display and returns those orders to user
     *
     * @param None
     * @return List<Order> list of order objects representing all available 
     * orders ever placed
     */
    public List<Order> getAllOrders() 
        throws FlooringServicesNoOrdersFoundExeception,
            FlooringServicesDaoPersistenceException;
    /**
     * Saves all active orders that have been successfully
     * created to a Backup/DataExport file
     *
     * @param None
     * @return void
     */
    public void exportAllOrders() 
    throws FlooringServicesNoOrdersFoundExeception,
            FlooringServicesDaoPersistenceException; 
    /**
     * Retrieves an order with the number specified for the user
     *
     * @param int number of the order to retirieve
     * @return Order object instance of Order retrieved, null otherwise
     */
    public Order getOrder(int orderNumber);
    /**
     * Creates an order from customer information specified
     *UPDATE
     * @param int number of the order to retirieve
     * @return Order object instance of Order created
     */
    public Order createOrder(LocalDateTime orderDate, String customerName,
            String orderState, String orderProductType, 
            BigDecimal orderArea) 
    throws FlooringServicesDaoPersistenceException,
         FlooringServicesNoOrdersFoundExeception;
    /**
     * Allows user to add new Order
     *
     * @param Order Order object instance representing order to add
     * @return void
     */
    public void addOrder(Order order) throws FlooringServicesDaoPersistenceException,
            FlooringServicesNoOrdersFoundExeception;
    /**
     * Allows user to update order and by saving order updates to file storage
     *
     * @param Order Order object instance representing order to update
     * 
     * 
     * @return void
     */
    public void updateOrder(Order order) throws FlooringServicesDaoPersistenceException,
            FlooringServicesNoOrdersFoundExeception;
    /**
     * Removes order from storage
     *
     * @param Order Order object instance representing order to remove
     * 
     * 
     * @return void
     */
    public void removeOrder(Order orderToRemove) 
    throws FlooringServicesDaoPersistenceException,
            FlooringServicesNoOrdersFoundExeception;
    /**
     * Creates a new order object instance with its fields recalculated if necessary
     *
     * @param Order Order object instance representing order to update
     * @param boolean True or false depending on if the order needs to have its fields
     * recaulculated
     * 
     * @return Order with updated calculations if necessary
     */
    public Order updateOrderCalculations(Order order, boolean recalculate) 
    throws FlooringServicesDaoPersistenceException;
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
    /**
     * Tells user if an order exists for a date
     *
     * @param int orderNumber for order
     * @param LocalDateTime date the order was said to be placed on
     * @return boolean true if order exists for date, false 
     * otherwise
     */
    public boolean orderExistsForDate(int orderNumber, LocalDateTime orderDate) 
        throws FlooringServicesNoOrdersFoundExeception,
            FlooringServicesDaoPersistenceException;
    /**
     * Tells user if an order exists within out application at all
     *
     * @param int orderNumber for order
     * @return boolean true if order exists within the application,
     * false otherwise
     */
    public boolean orderExists(int orderNumber);
}
