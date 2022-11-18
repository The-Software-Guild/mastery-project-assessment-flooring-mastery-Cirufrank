/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.Availability;
import com.we.flooringservices.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class tests the ProductDaoFileImpl class which 
 * provides the methods needed to read and 
 * write information to and from the Products file
 */

@ExtendWith(ProductDaoFileImplParameterResolver.class)
@ExtendWith(ProductParameterResolver.class)
@TestMethodOrder(OrderAnnotation.class)
public class ProductDaoFileImplTest {
    
    public ProductDaoFileImplTest() {
    }

    @Test
    @Order(1)
    public void testGetAllProducts(Product productToAdd, ProductDaoFileStubImpl testProductDao) 
        throws FlooringServicesDaoPersistenceException{
        final int TOTAL_PRODUCTS = 8, ONE_PRODUCT = 1;
        final ArrayList<Product> allProducts = 
                new ArrayList<>(testProductDao.getAllProducts());
        for (Product currentProduct: allProducts) {
            System.out.println(currentProduct.toString());
        }
        if (testProductDao.getProduct(productToAdd.getProductId()) != null) {
            assertEquals(TOTAL_PRODUCTS + ONE_PRODUCT, allProducts.size());
        }else {
            assertEquals(TOTAL_PRODUCTS, allProducts.size());
        }
        
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1})
    @Order(2)
    public void testGetProduct(int productId, ProductDaoFileStubImpl testProductDao) 
        throws FlooringServicesDaoPersistenceException{
        final Product accurateProduct = new Product(
        productId,"Carpet",new BigDecimal(2.25),
                new BigDecimal("2.10"), Availability.AVAILABLE);
        
        assertEquals(accurateProduct, testProductDao.getProduct(productId));
    }
    
    @Test
    @Order(3)
    public void testAddProduct(Product productToAdd, ProductDaoFileStubImpl testProductDao) 
        throws FlooringServicesDaoPersistenceException{
        testProductDao.addProduct(productToAdd);
        assertEquals(productToAdd, testProductDao.getProduct(productToAdd.getProductId()));
    }
    
    @Test
    @Order(4)
    public void testEditProduct(Product productToEdit, ProductDaoFileStubImpl testProductDao) 
        throws FlooringServicesDaoPersistenceException{
        final Availability UNAVAILABLE = Availability.UNAVAILABLE;
        productToEdit.setProductStatus(UNAVAILABLE);
        testProductDao.editProduct(productToEdit);
        assertEquals(productToEdit, 
                testProductDao.getProduct(productToEdit.getProductId()));
    }
    
    @Test
    @Order(5)
    public void testRemoveProduct(Product productToRemove, ProductDaoFileStubImpl testProductDao) 
        throws FlooringServicesDaoPersistenceException{
        testProductDao.removeProduct(productToRemove.getProductId());
        assertTrue(
                testProductDao.getProduct(
                        productToRemove.getProductId()) == null);
    }
    
}
