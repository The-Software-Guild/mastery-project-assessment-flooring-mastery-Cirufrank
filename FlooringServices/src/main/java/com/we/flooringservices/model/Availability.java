/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.we.flooringservices.model;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This enum represents the availability of the states within
 * our application
 * 
 * AVAILABLE represents a state being available for product/service purchase 
 * as well as a product being available for purchase 
 * 
 * UNAVIALABLE represents a state our services/products are not 
 * available to as well as products that are no longer sold
 * 
 * This allows us to retain the request data of states that were made 
 * available after enough requests were made
 * 
 * This also allows us to retain the information of products that were 
 * once available for purchase and are no longer available
 * 
 * The total orders purchased from available states can be calculated by 
 * counting the unique number of order IDs grouped by state
 */
public enum Availability {
    AVAILABLE, UNAVAILABLE;
}
