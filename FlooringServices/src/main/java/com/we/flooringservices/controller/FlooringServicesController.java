/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.controller;

import com.we.flooringservices.dao.FlooringServicesDaoPersistenceException;
import com.we.flooringservices.model.Order;
import com.we.flooringservices.service.FlooringServicesNoOrdersFoundExeception;
import com.we.flooringservices.service.FlooringServicesServiceLayer;
import com.we.flooringservices.ui.FlooringServicesView;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
    FlooringServicesServiceLayer service;
    
    @Autowired
    public FlooringServicesController(FlooringServicesView view, FlooringServicesServiceLayer service) {
        this.view = view;
        this.service = service;
    }
    
    public void run() { 
        while(usingApplication) {
                try {
                    int userChoice = 0;
                    view.displayWelcomeMessage();
                    view.displayMenu();
                    userChoice = view.getUserItemChoice();
                    switch(userChoice) {
                        case VIEW_ORDERS:
                            viewOrders();
                            break;
                        case ADD_NEW_ODER:
                            addOrder();
                            break;
                        case EDIT_ORDER:
                            editOrder();
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
                } catch(FlooringServicesNoOrdersFoundExeception | 
                        FlooringServicesDaoPersistenceException error) {
                    view.print(error.getMessage());
                }
            }
        }
        private void viewOrders() throws 
                FlooringServicesNoOrdersFoundExeception, 
                        FlooringServicesDaoPersistenceException{
            final LocalDateTime userDateChoice = view.getUserDateChoice();
            final List<Order> ordersForDate = service.getOrders(userDateChoice);
            view.displayOrders(ordersForDate);
        }
        private void addOrder()
            throws FlooringServicesDaoPersistenceException,
                FlooringServicesNoOrdersFoundExeception{
            final LocalDateTime orderDate = view.getFutureDate();
            final String customerName = view.getCustomerName();
            boolean continueOrder = true;
            final List<String> allProductTypes = service.getAvailableProductTypes();
            String orderState = view.getOrderState();
            boolean continueToOrder = true;
            while(continueToOrder &&
                service.isStateAvailable(orderState) != true) {
                service.logStateRequest(orderState);
                view.printStateUnavailableMessage();
                continueToOrder = view.getUserContinueChoice();
                if (continueToOrder == false) {
                    view.printExitedMessage();
                    return;
                }
                orderState = view.getNextState();
            }
            final String productType = view.getProductType(allProductTypes);
            final BigDecimal area = view.getArea();
            final Order newOrder = service.createOrder(orderDate, customerName, orderState, productType, area);
            final boolean addOrder = view.displayOrderAndGetAddChoice(newOrder);
            if (addOrder) {
                service.addOrder(newOrder);
                view.displayOrderAddedSuccessfullyMessage();
            }
            else return;
            
        }
        private void editOrder() throws FlooringServicesNoOrdersFoundExeception,
            FlooringServicesDaoPersistenceException {
            final List<String> allProductTypes = service.getAvailableProductTypes();
            final String EMPTY_STRING = "".intern();
            boolean continueToEdit = true, recalculate = false;
            int orderNumber = view.getOrderNumber();
            LocalDateTime orderDate = view.getUserDateChoice();
            while(continueToEdit && 
                    service.orderExistsForDate(orderNumber, orderDate) != true) {
                if (service.orderExists(orderNumber)) {
                    view.printOrderExistsButNoOrderForDateMessage();
                    continueToEdit = view.getUserContinueChoice();
                    if (continueToEdit == false) {
                        view.printExitedMessage();
                        return;
                    }
                    orderDate = view.getUserDateChoice();
                }
                else {
                    view.printOrderDoesNotExistMessage();
                    continueToEdit = view.getUserContinueChoice();
                    if (continueToEdit == false) {
                        view.printExitedMessage();
                        return;
                    }
                    orderNumber = view.getOrderNumber();
                    orderDate = view.getUserDateChoice();
                }
            }
            Order order = service.getOrder(orderNumber);
            final String customerName = view.editCustomerName(order);
            if (!customerName.equals(EMPTY_STRING)) order.setCustomerName(customerName);
            String orderState = view.editOrderState(order);
            continueToEdit = true;
            while(continueToEdit &&
                service.isStateAvailable(orderState) != true
                    && !orderState.equals(EMPTY_STRING)) {
                service.logStateRequest(orderState);
                view.printStateUnavailableMessage();
                continueToEdit = view.getUserContinueChoice();
                if (continueToEdit == false) {
                    view.printExitedMessage();
                    orderState = EMPTY_STRING;
                    break;
                }
                orderState = view.getNextState();
            }
            if (!orderState.equals(EMPTY_STRING)){
                recalculate = true;
                order.setState(orderState);
            }
            String productType = view.editProductType(order, allProductTypes);
            if (!productType.equals(EMPTY_STRING)) 
            {
                recalculate = true;
                order.setProductType(productType);
            }
            String stringArea = view.editArea(order);
            if (!stringArea.equals(EMPTY_STRING)) {
                BigDecimal area = new BigDecimal(stringArea);
                recalculate = true;
                order.setArea(area);
            }
            Order orderWithEdits = service.updateOrderCalculations(order, recalculate);
            view.displayOrderEdits(orderWithEdits);
            continueToEdit = view.getUserContinueChoice();
            if (continueToEdit == true) {
                service.updateOrder(orderWithEdits);
                view.displayEditSuccessBanner();
            } else view.displayNoEditsMade(order);
        }
    }
