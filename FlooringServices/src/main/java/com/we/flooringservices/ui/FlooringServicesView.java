/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description 
 */
@Component
@Primary
public class FlooringServicesView {
    final private String WELCOME_MESSAGE = "=== WELCOME OUR FLOORING SERVICES ===",
            BORDER = "*******************************",
            DISPLAY_ORDERS_OPTION = "* 1. Display Orers",
            ADD_ORDER_OPTION = "* 2. Add an Order",
            EDIT_ORDER_OPTION = "8 3. Edit an Order",
            REMOVE_ORDER_OPTION = "* 4. Remove an Order",
            EXPORT_ALL_DATA_OPTION = "* 5. Export and View All Data",
            QUIT_OPTION = "* 6. Quit",
            PLEASE_ENTER_CHOICE = "Please enter your choice";
            
    
    private UserIO io;
    @Autowired
    public FlooringServicesView(UserIO io) {
        this.io = io;
    }
    public void print(String message) {
        io.print(message);
    }
    public void displayWelcomeMessage() {
        io.print(WELCOME_MESSAGE);
    }
    public void displayMenu() {
        io.print(BORDER);
        io.print(DISPLAY_ORDERS_OPTION);
        io.print(ADD_ORDER_OPTION);
        io.print(EDIT_ORDER_OPTION);
        io.print(REMOVE_ORDER_OPTION);
        io.print(EXPORT_ALL_DATA_OPTION);
        io.print(QUIT_OPTION);
        io.print(BORDER);
    }
    
    public int getUserItemChoice() {
        final int userChoice = io.readItemChoice(PLEASE_ENTER_CHOICE);
        return userChoice;
    }
}
