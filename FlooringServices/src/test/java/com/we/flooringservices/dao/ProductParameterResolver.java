/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.Availability;
import com.we.flooringservices.model.Product;
import java.math.BigDecimal;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class is our parameter resolver for the 
 * Product class, and it allows us to use dependency injection within our unit tests so that we
 * do not have to continuously instantiate a Product
 * object before each test is ran
 */

public class ProductParameterResolver implements ParameterResolver {
    final int TOTAL_PRODUCTS = 8;
    final Availability STATUS = Availability.AVAILABLE;
    final Product productToAdd = new Product("Test type", new BigDecimal("2.25"), 
            new BigDecimal("4.35"), TOTAL_PRODUCTS, STATUS);
    
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == Product.class;
    }
    
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return productToAdd;
    }
}
