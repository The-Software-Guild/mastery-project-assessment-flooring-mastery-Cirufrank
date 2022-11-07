/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.model;

import java.math.BigDecimal;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class represents the orders within this application
 */
public class Order {
    private static int totalOrders = 0;
    final private static int ONE_ORDER = 1;
    private int orderNumber;
    private String customerName, state, productType;
    private BigDecimal taxRate, area, costPerSquareFoot, laborCostPerSquareFoot,
            materialCost, laborCost, tax, total;
    
    public Order() {
        totalOrders += ONE_ORDER;
        orderNumber = totalOrders;
    }
    
    public Order(String customerName, String state, String productType,
            BigDecimal area) {
        totalOrders += ONE_ORDER;
        orderNumber = totalOrders;
        this.customerName = customerName;
        this.state = state;
        this.productType = productType;
        this.area = area;
    }
            
    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getProductType() {
        return productType;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public BigDecimal getArea() {
        return area;
    }
    
    public void setArea(BigDecimal area) {
        this.area = area;
    }
    public 
        BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }
    
}
