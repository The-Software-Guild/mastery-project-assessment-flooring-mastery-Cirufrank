/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.flooringservices.service;

import com.we.flooringservices.dao.AuditDaoFileStubImpl;
import com.we.flooringservices.dao.DaoHelper;
import com.we.flooringservices.dao.OrderDaoFileImplParameterResolver;
import com.we.flooringservices.dao.OrderParameterResolver;
import com.we.flooringservices.dao.ProductDaoFileImplParameterResolver;
import com.we.flooringservices.dao.ProductParameterResolver;
import com.we.flooringservices.dao.StateDaoFileImplParameterResolver;
import com.we.flooringservices.dao.StateParameterResolver;
import com.we.flooringservices.dao.StateRequestDaoFileImplParameterResolver;
import com.we.flooringservices.model.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class automates the Unit tests for the 
 * FlooringServicesServiceLayer using JUnit
 */
@ExtendWith(FlooringServicesServiceLayerParameterResolver.class)
@ExtendWith(StateRequestDaoFileImplParameterResolver.class)
@ExtendWith(OrderDaoFileImplParameterResolver.class)
@ExtendWith(ProductDaoFileImplParameterResolver.class)
@ExtendWith(StateDaoFileImplParameterResolver.class)
@ExtendWith(StateRequestDaoFileImplParameterResolver.class)
@ExtendWith(OrderParameterResolver.class)
@ExtendWith(ProductParameterResolver.class)
@ExtendWith(StateParameterResolver.class)

public class FlooringServicesServiceLayerImplTest {
    
    public FlooringServicesServiceLayerImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getOrders method, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testGetOrders(FlooringServicesServiceLayerStubImpl testServiceLayer) throws Exception {
        final LocalDateTime orderDate = DaoHelper.parseFileDateString("06022013");
        final int ALL_ORDERS_FOR_DATE = 2;
        final List<Order> ordersForDate = testServiceLayer.getOrders(orderDate);
        assertEquals(ALL_ORDERS_FOR_DATE, ordersForDate.size());
    }

    /**
     * Test of getAllOrders method, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testGetAllOrders() throws Exception {
    }

    /**
     * Test of exportAllOrders method, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testExportAllOrders() throws Exception {
    }

    /**
     * Test of orderExistsForDate method, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testOrderExistsForDate() throws Exception {
    }

    /**
     * Test of addOrder, getOrder, and removeOrder methods, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testAddGetRemoveOrder(Order testOrder, 
            FlooringServicesServiceLayerStubImpl testServiceLayer) throws Exception {
        testServiceLayer.addOrder(testOrder);
        final Order orderRetrieved = testServiceLayer.getOrder(testOrder.getOrderNumber());
        assertEquals(testOrder, orderRetrieved);
        
    }
    

    /**
     * Test of orderExists method, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testOrderExists() {
    }

    

    /**
     * Test of createOrder method, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testCreateOrder() throws Exception {
    }

    /**
     * Test of updateOrder and updateOrderCalculations methods, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testUpdateOrderAndUpdateOrderCalculations(Order testOrder, 
            FlooringServicesServiceLayerStubImpl testServiceLayer) throws Exception {
        final String previousCustomerName = testOrder.getCustomerName();
        final String previousProductType = testOrder.getProductType();
        final BigDecimal previousArea = testOrder.getArea();
        final String previousState = testOrder.getState();
        final BigDecimal previousTaxRate = testOrder.getTaxRate();
        final BigDecimal previousCostPerSquareFoot = testOrder.getCostPerSquareFoot();
        final BigDecimal previousLaborCostPerSquareFoot = testOrder.getLaborCostPerSquareFoot();
        final BigDecimal previousMaterialCost = testOrder.getMaterialCost();
        final BigDecimal previousLaborCost = testOrder.getLaborCost();
        final BigDecimal previousTax = testOrder.getTax();
        final BigDecimal previousTotal = testOrder.getTotal();
        testOrder.setProductType("Vinyl");
        testOrder.setState("MO");
        testOrder.setCustomerName("Test order edited");
        testOrder.setArea(new BigDecimal("157"));
        testServiceLayer.updateOrder(testOrder);
        final Order updatedOrder = testServiceLayer.getOrder(testOrder.getOrderNumber());
        assertNotEquals(previousCustomerName, updatedOrder.getCustomerName());
        assertNotEquals(previousProductType, updatedOrder.getProductType());
        assertNotEquals(previousArea, updatedOrder.getArea());
        assertNotEquals(previousState, updatedOrder.getState());
        assertEquals(previousTaxRate, updatedOrder.getTaxRate());
        assertEquals(previousCostPerSquareFoot, updatedOrder.getCostPerSquareFoot());
        assertEquals(previousLaborCostPerSquareFoot, updatedOrder.getLaborCostPerSquareFoot());
        assertEquals(previousMaterialCost, updatedOrder.getMaterialCost());
        assertEquals(previousLaborCost, updatedOrder.getLaborCost());
        assertEquals(previousTax, updatedOrder.getTax());
        assertEquals(previousTotal, updatedOrder.getTotal());
        Order recalculatedOrder = testServiceLayer.updateOrderCalculations(testOrder, true);
        testServiceLayer.updateOrder(recalculatedOrder);
        recalculatedOrder = testServiceLayer.getOrder(testOrder.getOrderNumber());
        assertNotEquals(previousCustomerName, recalculatedOrder.getCustomerName());
        assertNotEquals(previousProductType, recalculatedOrder.getProductType());
        assertNotEquals(previousArea, recalculatedOrder.getArea());
        assertNotEquals(previousState, recalculatedOrder.getState());
        assertNotEquals(previousTaxRate, recalculatedOrder.getTaxRate());
        assertNotEquals(previousCostPerSquareFoot, recalculatedOrder.getCostPerSquareFoot());
        assertNotEquals(previousLaborCostPerSquareFoot, recalculatedOrder.getLaborCostPerSquareFoot());
        assertNotEquals(previousMaterialCost, recalculatedOrder.getMaterialCost());
        assertNotEquals(previousLaborCost, recalculatedOrder.getLaborCost());
        assertNotEquals(previousTax, recalculatedOrder.getTax());
        assertNotEquals(previousTotal, recalculatedOrder.getTotal());
    }

    /**
     * Test of removeOrder method, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testRemoveOrder(Order testOrder, 
            FlooringServicesServiceLayerStubImpl testServiceLayer) throws Exception {
        testServiceLayer.removeOrder(testOrder);
        final Order removedOrder = testServiceLayer.getOrder(testOrder.getOrderNumber());
        assertNull(removedOrder);
    }

    /**
     * Test of isStateAvailable method, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testIsStateAvailable() throws Exception {
    }

    /**
     * Test of getAvailableProductTypes method, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testGetAvailableProductTypes() throws Exception {
    }

    /**
     * Test of logStateRequest method, of class FlooringServicesServiceLayerImpl.
     */
    @Test
    public void testLogStateRequest() throws Exception {
    }
    
}