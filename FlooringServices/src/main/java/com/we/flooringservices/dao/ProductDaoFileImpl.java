/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.dao;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class represents the file implementation of the
 * ProductDao interface and defines the properties and methods needed
 * to write and read product information from the "Data/Products.txt"
 * file
 */

public class ProductDaoFileImpl implements ProductDao {
    final private static String DELIMITER = ",",
            PRODUCT_ID_HEADER = "productId",
            PRODUCT_TYPE_HEADER = "productType",
            SQUARE_FOOT_COST_HEADER = "costPerSquareFoot",
            SQUARE_FOOT_LABOR_COST_HEADER = "laborCostPerSquareFoot",
            productsHeadersLine = PRODUCT_ID_HEADER + DELIMITER
            + PRODUCT_TYPE_HEADER + DELIMITER + SQUARE_FOOT_COST_HEADER
            + DELIMITER + SQUARE_FOOT_LABOR_COST_HEADER;
    
    
}
