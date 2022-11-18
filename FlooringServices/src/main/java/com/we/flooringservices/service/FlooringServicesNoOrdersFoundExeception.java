/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.service;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class allows us to create a custom exception that
 * will be thrown if there are no orders retrieved after they are requested
 */
public class FlooringServicesNoOrdersFoundExeception extends Exception {
    public FlooringServicesNoOrdersFoundExeception(String message) {
        super(message);
    }
    
    public FlooringServicesNoOrdersFoundExeception(String message, Throwable cause) {
        super(message, cause);
    }
}
