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
 * 
 * TO DO: Update fields logic!
 */
public class Order {
    final private static int ONE_ORDER = 1;
    private ServiceCalculator serviceCalculator;
    private int orderNumber;
    private String customerName, state, productType;
    private BigDecimal taxRate, area, costPerSquareFoot, laborCostPerSquareFoot,
            materialCost, laborCost, tax, total;
    
    public Order(Product product, String customerName, String state,
            BigDecimal area, BigDecimal taxRate, int totalOrders) {
        orderNumber = totalOrders + ONE_ORDER;
        this.productType = product.getProductType();
        this.costPerSquareFoot = product.getCostPerSquareFoot();
        this.laborCostPerSquareFoot = product.getLaborCostPerSquareFoot();
        this.customerName = customerName;
        this.state = state;
        this.area = area;
        this.taxRate = taxRate;
        this.serviceCalculator = new ServiceCalculator(area, costPerSquareFoot,
            laborCostPerSquareFoot, taxRate);
        this.materialCost = serviceCalculator.getMaterialCost();
        this.laborCost = serviceCalculator.getLaborCost();
        this.total = serviceCalculator.getTotalCost();
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
