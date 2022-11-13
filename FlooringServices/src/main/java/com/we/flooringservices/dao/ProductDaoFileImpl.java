/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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

@Component
@Primary
public class ProductDaoFileImpl implements ProductDao {
    final private Map<Integer, Product> allProducts= new HashMap<>();
    final private static String DELIMITER = ",",
            PRODUCT_ID_HEADER = "productId",
            PRODUCT_TYPE_HEADER = "productType",
            SQUARE_FOOT_COST_HEADER = "costPerSquareFoot",
            SQUARE_FOOT_LABOR_COST_HEADER = "laborCostPerSquareFoot",
            productHeadersLine = PRODUCT_ID_HEADER + DELIMITER
            + PRODUCT_TYPE_HEADER + DELIMITER + SQUARE_FOOT_COST_HEADER
            + DELIMITER + SQUARE_FOOT_LABOR_COST_HEADER;
    private String productsFileName;
    
    public ProductDaoFileImpl() {
        productsFileName = "Data/Products.txt";
    }
    
    public ProductDaoFileImpl(String productsFileName){
        this.productsFileName = productsFileName;
    }
    
    @Override
    public List<Product> getAllProducts() {
        loadProducts();
        final List<Product> currentProducts =
                new ArrayList<>(allProducts.values());
        return currentProducts;
    }
    
    @Override
    public Product getProduct(int productId) {
        loadProducts();
        final Product productRetrieved = 
                allProducts.get(productId);
        return productRetrieved;
    }
    
    @Override
    public void addProduct(Product product) {
        loadProducts();
        allProducts.put(product.getProductId(), product);
        writeProducts();
    }
    
    private String marshallProduct(Product product) {
        final String productAsText = DaoHelper
                .createDelimiterSeparatedString(DELIMITER,
                        Integer.toString(product.getProductId()),
                        product.getProductType(), 
                        product.getCostPerSquareFoot().toString(),
                        product.getLaborCostPerSquareFoot().toString());
        return productAsText;
    }
    
    private Product unMarshallProduct(String productAsText) {
        final String[] productTokens = productAsText.split(DELIMITER);
        final int ID_INDEX = 0, PRODUCT_TYPE_INDEX = 1,
                PRODUCT_SQUARE_FOOT_COST_INDEX = 2,
                PRODUCT_LABOR_SQUARE_FOOT_COST_INDEX = 3,
                productId = Integer.parseInt(productTokens[ID_INDEX]),
                TOTAL_PRODUCTS = allProducts.size();
        
        final String productType = productTokens[PRODUCT_TYPE_INDEX];
        final BigDecimal costPerSquareFoot = new BigDecimal(productTokens[
                PRODUCT_SQUARE_FOOT_COST_INDEX]),
                laborCostPerSquareFoot = new BigDecimal(productTokens[
                        PRODUCT_LABOR_SQUARE_FOOT_COST_INDEX]);
        
        Product product = new Product(productId, productType,
        costPerSquareFoot, laborCostPerSquareFoot, TOTAL_PRODUCTS);
        return product;
        
    }
    
    private void loadProducts() {
        try {
            Scanner scanner =
                    new Scanner( 
                        new BufferedReader(
                            new FileReader(productsFileName)));
            //This skips the header line from being converted to
            //a Product thus causing an error
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                final String productAsText = scanner.nextLine();
                final Product currentProduct =
                        unMarshallProduct(productAsText);
                allProducts.put(currentProduct.getProductId(), currentProduct);
            }
            scanner.close();
        } catch(FileNotFoundException error) {
            System.out.println("-_- Unable to read"
                    + "products from file");
        }
    }
    
    private void writeProducts() {
        final ArrayList<Product> currentProducts =
                new ArrayList<>(allProducts.values());
        try {
            PrintWriter output = 
                    new PrintWriter(
                        new FileWriter(productsFileName));
            //Write the headers line to the file before writing the 
            //products
            output.println(productHeadersLine);
            for (Product currentProduct: currentProducts) {
                final String productAsText = marshallProduct(currentProduct);
                output.println(productAsText);
                output.flush();
            }
            output.close();
        } catch(IOException error) {
            System.out.println("-_- Could not write "
                    + "products to file");
        }
    }
}
