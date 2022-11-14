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
 */
public interface OrderDao {
    /**
     * Returns all orders ever created regardless of status (UNAVAILABLE
     * orders have been removed from its date file, but its information
     * is retained in the backup file for business purposes such as if 
     * an order was deleted in error
     *
     * @param None
     * @return A list of all orders ever created successfully
     */
    public List<Order> getAllOrders();
    /**
     * Returns all currently active orders that have been successfully
     * saved
     *
     * @param None
     * @return A list of all currently active orders ever created successfully
     */
    public List<Order> getAllActiveOrders();
    /**
     * Returns all orders placed on a specified date
     *
     * @param None
     * @return A list of all orders ever created successfully for the 
     * date specified
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
     * Data/Orders_MMDDYYY.txt file
     *
     * @param Order An Order object that represents the order to remove perannetly
     * from the appropriate Data/Orders_MMDDYYY.txt file
     * @return void
     */
    public void removeOrderFromDateFile(Order order);
    /**
     * Updates an order's information from its file within the appropriate
     * Data/Orders_MMDDYYY.txt file
     *
     * @param Order An Order object that represents the order to update
     * within the appropriate Data/Orders_MMDDYYY.txt file
     * @return void
     */
    public void updateOrderInDateFile(Order order);
    /**
     * Updates an order's information from its file within the 
     * Backup/AllOrders.txt file
     *
     * @param Order An Order object that represents the order to update
     * within the Backup/AllOrders.txt file
     * @return void
     */
    public void updateOrderInBackupFile(Order order);
    /**
     * Updates an order's information from its file within the 
     * Backup/DataExport.txt file
     *
     * @param Order An Order object that represents the order to update
     * within the Backup/DataExport.txt file
     * @return void
     */
    public void updateOrderInActiveOrdersFile(Order order);
    /**
     * Adds an order's information the appropriate its file within the 
     * appropriate Data/Orders_MMDDYY.txt file
     *
     * @param Order An Order object that represents the order to add
     * to the Data/Orders_MMDDYY.txt file
     * @return void
     */
    public void addOrderToDateFile(Order order);
    /**
     * Adds an order's information the Backup/AllOrders.txt file
     *
     * @param Order An Order object that represents the order to add
     * to the Backup/AllOrders.txt file
     * @return void
     */
    public void addOrderToBackupFile(Order order);
    /**
     * Adds an order's information the Backup/DataExport.txt file
     *
     * @param Order An Order object that represents the order to add
     * to the Backup/DataExport.txt file
     * @return void
     */
    public void addOrderToActiveOrdersFile(Order order);
    /**
     * Save's all active orders' information to the Backup/ExportData.txt 
     * file
     *
     * @param None
     * @return void
     */
    public void exportAllActiveOrders();
}
