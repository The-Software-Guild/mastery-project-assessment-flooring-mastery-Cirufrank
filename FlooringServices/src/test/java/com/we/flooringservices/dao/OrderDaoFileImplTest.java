/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.Order;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class tests the OrdertDaoFileImpl class which 
 * provides the methods needed to read and 
 * write information to and from the Orders files
 */

@ExtendWith(OrderDaoFileImplParameterResolver.class)
@ExtendWith(OrderParameterResolver.class)
public class OrderDaoFileImplTest {
    
    public OrderDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass(Order testOrder, OrderDaoFileStubImpl testOrderDao) throws IOException {
       testOrderDao.removeOrder(testOrder);
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllOrders method, of class OrderDaoFileImpl.
     */
    @Test
    public void testGetAllOrders(Order testOrder, OrderDaoFileStubImpl testOrderDao) {
        final int ALL_CURRENT_ORDERS = 3, ONE_ORDER = 1;
        final List<Order> currentOrders = testOrderDao.getAllOrders();
        for (Order currentOrder: currentOrders) {
            System.out.println(currentOrder.toString());
        }
        if (testOrderDao.getOrder(testOrder.getOrderNumber()) == null)
                assertEquals(ALL_CURRENT_ORDERS,currentOrders.size());
        else assertEquals(ALL_CURRENT_ORDERS + ONE_ORDER, currentOrders.size());
    }

    /**
     * Test of getAllOrdersForDate method, of class OrderDaoFileImpl.
     */
    @Test
    public void testGetAllOrdersForDate(OrderDaoFileStubImpl testOrderDao) {
        final LocalDateTime orderDate = DaoHelper.parseFileDateString("06022013");
        final int ALL_ORDERS_FOR_DATE = 2;
        final List<Order> ordersForDate = testOrderDao.getAllOrdersForDate(orderDate);
        assertEquals(ALL_ORDERS_FOR_DATE, ordersForDate.size());
    }

    /**
     * Test of getOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testAddGetOrder(Order testOrder, OrderDaoFileStubImpl testOrderDao) throws IOException {
        testOrderDao.addOrder(testOrder);
        final Order addedOrder = testOrderDao.getOrder(testOrder.getOrderNumber());
        assertNotNull(addedOrder);
        assertEquals(testOrder, addedOrder);
    }

    /**
     * Test of updateOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testUpdateOrder(Order testOrder, OrderDaoFileStubImpl testOrderDao) {
        final String previousOrderCustomerName = testOrder.getCustomerName();
        testOrder.setCustomerName("A different customer");
        testOrderDao.updateOrder(testOrder);
        final Order updatedOrder = testOrderDao.getOrder(testOrder.getOrderNumber());
        assertNotEquals(previousOrderCustomerName, updatedOrder.getCustomerName());
    }
    
    /**
     * Test of exportAllActiveOrders method, of class OrderDaoFileImpl.
     */
    @Test
    public void testExportAllActiveOrders(OrderDaoFileStubImpl testOrderDao) {
        final List<Order> allOrders = testOrderDao.getAllOrders();
        final int totalOrders = allOrders.size();
        
        testOrderDao.exportAllActiveOrders();
        final List<Order> allExportedOrders = testOrderDao.getAllExportedOrders();
        assertEquals(totalOrders, allExportedOrders.size());
    }
    
    /**
     * Test of removeOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testRemoveOrder(Order testOrder, OrderDaoFileStubImpl testOrderDao) throws IOException {
        final Order removedOrder = testOrderDao.removeOrder(testOrder);
        assertNotNull(removedOrder);
        assertEquals(testOrder, removedOrder);
        final Order previosulyRemovedOrder = testOrderDao.getOrder(testOrder.getOrderNumber());
        assertNull(previosulyRemovedOrder);
        
    }


    
    
}
