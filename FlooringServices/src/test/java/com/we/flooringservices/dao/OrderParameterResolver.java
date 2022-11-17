/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.Order;
import com.we.flooringservices.model.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Vending Machine with Spring DI
 * 
 * @description This class is our parameter resolver for the 
 * order class, and it allows us to use dependency injection within our unit tests so that we
 * do not have to continuously instantiate an Order
 * object before each test is ran
 */

public class OrderParameterResolver implements ParameterResolver {
    final LocalDateTime orderDate = DaoHelper.parseExportDateString("11-17-2022");
    final Order testOrder = new Order("Test Customer", "CA",
                    new BigDecimal("4.60"), "Carpet", new BigDecimal("249.00"),
                    new BigDecimal("2.25"), new BigDecimal("2.10"),
                    new BigDecimal("100"), new BigDecimal("100"), new BigDecimal("50"),
                    new BigDecimal("250"), orderDate, 3);
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == Order.class;
    }
    
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return testOrder;
    }
}
