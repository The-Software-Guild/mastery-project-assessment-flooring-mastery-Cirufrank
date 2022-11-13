/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.model;

import java.math.BigDecimal;
import java.util.Objects;

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
    private int productId;
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
    
    public void setProductStatus(Availability newStatus) {
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return "Product{ productId=" + productId + ", productType=" + productType + ", costPerSquareFoot=" + costPerSquareFoot + ", laborCostPerSquareFoot=" + laborCostPerSquareFoot + ", status=" + status +'}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + this.productId;
        hash = 97 * hash + Objects.hashCode(this.productType);
        hash = 97 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 97 * hash + Objects.hashCode(this.laborCostPerSquareFoot);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.productId != other.productId) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        return Objects.equals(this.laborCostPerSquareFoot, other.laborCostPerSquareFoot);
    }
    
    
    
}
