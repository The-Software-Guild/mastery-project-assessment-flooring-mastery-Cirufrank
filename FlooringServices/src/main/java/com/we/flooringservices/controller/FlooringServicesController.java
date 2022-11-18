/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.controller;

import com.we.flooringservices.ui.FlooringServicesView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This class acts as the brains of our application and 
 * utilizes the service and view layers' methods to control program flow
 * 
 */
@Component
public class FlooringServicesController {
    final private static int VIEW_ORDERS = 1,
            ADD_NEW_ODER = 2,
            EDIT_ORDER = 3,
            REMOVE_ORDER = 4,
            EXPORT_AND_VIEW_ALL_ORDERS = 5,
            QUIT = 6;
    private boolean usingApplication = true;
    FlooringServicesView view;
    
    @Autowired
    public FlooringServicesController(FlooringServicesView view) {
        this.view = view;
    }
    
    public void run() { 
        while(usingApplication) {
                int userChoice = 0;
                view.displayWelcomeMessage();
                view.displayMenu();
                userChoice = view.getUserItemChoice();
                switch(userChoice) {
                    case VIEW_ORDERS:
                        view.print("Not implemented: view orders");
                        break;
                    case ADD_NEW_ODER:
                        view.print("Not implemented: add new order");
                        break;
                    case EDIT_ORDER:
                        view.print("Not implemented: edit order");
                        break;
                    case REMOVE_ORDER:
                        view.print("Not implemented: remove order");
                        break;
                    case EXPORT_AND_VIEW_ALL_ORDERS:
                        view.print("Not implemented:export and view all orders");
                        break;
                    case QUIT:
                        usingApplication = false;
                }
            }
        }
    }
