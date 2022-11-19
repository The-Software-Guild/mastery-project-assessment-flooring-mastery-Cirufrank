/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.Product;
import java.util.List;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This interface defines the methods publicly available
 * to retrieve and add products to the "Data/Products.txt" file
 */
public interface ProductDao {
    /**
     * Returns a product from the database that matches the 
     * id specified
     *
     * @param String the type of the product to retrieve
     * @return the Product object that has the id specified if
     * found within the taxes file, null otherwise
     */
    public Product getProduct(String productType)
            throws FlooringServicesDaoPersistenceException;
    /**
     * Returns a list of all of the products currently
     * within inventory (listed within the Data/Products.txt
     * file
     *
     * @param None
     * @return A list of the products currently listed in 
     * inventory
     */
    public List<Product> getAllProducts()
            throws FlooringServicesDaoPersistenceException;
    /**
     * Adds a product to our inventory list by saving it
     * to the Data/Products.txt file
     *
     * @param Produce Product object representing the 
     * product we'd like to add, and thus providing us 
     * an interface to get all of the product's associated
     * information
     * 
     * @return void
     */
    public void addProduct (Product product)
            throws FlooringServicesDaoPersistenceException;
    /**
     * Edits a product to our inventory list by saving its
     * new changes to the Data/Products.txt file
     *
     * @param Produce Product object representing the 
     * product we'd like to edit, and thus providing us 
     * an interface to get, and update all of the product's needed
     * information
     * 
     * @return void
     */
    public void editProduct(Product product)
            throws FlooringServicesDaoPersistenceException;
}
