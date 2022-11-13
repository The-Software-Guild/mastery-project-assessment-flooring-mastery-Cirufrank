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
    private Availability status;
    private int productId, totalProducts;
    private String productType;
    private BigDecimal costPerSquareFoot, laborCostPerSquareFoot;
    
    //This constructor assigns an ID to nelwy added products by passsing in
    //an argument of total products for the id to be calculated through
    public Product(String productType, BigDecimal costPerSquareFoot,
            BigDecimal laborCostPerSquareFoot, int totalProducts,
            Availability status) {
        this.productId = totalProducts + ONE_PRODUCT;
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        this.status = status;
    }
    
    //This constructor insantiates products that already exist in storage 
    //and therefore does not need to a a product id to them
    public Product(int productId, String productType, BigDecimal costPerSquareFoot,
            BigDecimal laborCostPerSquareFoot, Availability status) {
        this.productId = productId;
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
        this.status = status;
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
    
    public Availability getProductStatus() {
        return status;
    }
    
}
