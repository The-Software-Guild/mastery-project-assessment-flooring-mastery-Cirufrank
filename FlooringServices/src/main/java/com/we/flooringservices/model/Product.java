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
 * @description This class acts as the model for our Products and 
 * contains the properties and methods that allow other parts of 
 * our application to access a product's information
 */

public class Product {
    final private static int ONE_PRODUCT = 1;
    private int productId;
    private String productType;
    private BigDecimal costPerSquareFoot, laborCostPerSquareFoot;
    public Product(String productType, BigDecimal costPerSquareFoot,
            BigDecimal laborCostPerSquareFoot, int totalProducts) {
        this.productId = totalProducts + ONE_PRODUCT;
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }
    
}
