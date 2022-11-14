/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.Order;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This interface defines the methods publicly available
 * to retrieve and add products to the "Data/Orders.txt" file
 * 
 * NOTE: We can use the java.io.File class' file/directoryInstance.list()
 * method to return an array of strings for the directory specified
 * an continue to load all orders from that file until the orders have 
 * all been saved to the current export
 * 
 * The audit file will keep track of all changes so the orders date 
 * appropriate file will be responsbile for maintaining all order 
 * information and the Backup/DataExport.txt file will be 
 * created based on the order's information
 * 
 * An order's date will be found by getting the last 8 characters from
 * its .txt file's name (since these will represent the date the order
 * was added in MMDDYYYY format)
 */
public interface OrderDao {
    /**
     * Returns all currently active orders that have been successfully
     * saved by continuing to unMarshall order objects from all files 
     * within the Data/Orders directory that begin with "Orders_"
     *
     * @param None
     * @return A list of all orders ever created successfully
     */
    public List<Order> getAllOrders();
    /**
     * Returns all currently active orders that have been successfully
     * saved an were placed on the date specified
     *
     * @param None
     * @return A list of all currently active orders ever created successfully
     */
    public List<Order> getAllOrdersForDate(LocalDateTime orderDate);
    /**
     * Returns an order that matches the specified id
     *
     * @param int The id of the order that the user would like to retrieve
     * @return An order matching the id of the order specified if found, mull
     * otherwise
     */
    public Order getOrder(int orderId);
    /**
     * Removes an order from its file within the appropriate
     * Data/Orders_MMDDYYYY.txt file (the date is added to the unMarshalled
     * order by taking the last 8 characters from the file it was loaded from
     *
     * @param Order An Order object that represents the order to remove 
     * permanently from the appropriate Data/Orders_MMDDYYYY.txt file
     * @return void
     */
    public void removeOrder(Order order);
    /**
     * Updates an order's information from its file within the appropriate
     * Data/Orders_MMDDYYYY.txt file
     *
     * @param Order An Order object that represents the order to update
     * within the appropriate Data/Orders_MMDDYYYY.txt file
     * @return void
     */
    public void updateOrder(Order order);
    /**
     * Adds an order's information the appropriate file within the 
     * appropriate Data/Orders_MMDDYYYY.txt file
     *
     * @param Order An Order object that represents the order to add
     * to the Data/Orders_MMDDYYYY.txt file
     * @return void
     */
    public void addOrder(Order order);
    /**
     * Save's all active orders' information to the Backup/ExportData.txt 
     * file
     *
     * @param None
     * @return void
     */
    public void exportAllActiveOrders();
}
