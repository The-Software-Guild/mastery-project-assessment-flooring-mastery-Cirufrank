/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.ui;

import com.we.flooringservices.model.Order;
import com.we.flooringservices.model.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
            DISPLAY_ORDERS_OPTION = "* 1. Display Orders",
            ADD_ORDER_OPTION = "* 2. Add an Order",
            EDIT_ORDER_OPTION = "* 3. Edit an Order",
            REMOVE_ORDER_OPTION = "* 4. Remove an Order",
            EXPORT_ALL_DATA_OPTION = "* 5. Export and View All Data",
            QUIT_OPTION = "* 6. Quit",
            PLEASE_ENTER_CHOICE = "Please enter your choice",
            ENTER_DATE = "Please enter the date you'd like to select in format"
            + " MM/dd/yyyy such as 04/31/2022 or 12/23/2022",
            PLEASE_ENTER_FUTURE_DATE = "Please enter the order's date in format "
            + "MM/dd/yyyy such as 04/31/2022 or 12/23/2022",
            PLEASE_ENTER_NAME = "Please enter your name",
            PLEASE_ENTER_STATE = "Please enter state abbreviation",
            WOULD_YOU_LIKE_TO_CONTINUE = "Would you like to continue?",
            STATE_UNAVILABLE = "Sorry, the state entered is "
            + "unavailable for services. We have taken note of this "
            + "and logged the state specified to our requests.",
            EXIT_MESSAGE = "Sorry to see you go!",
            CHOOSE_PRODUCT_TYPE = "Please select the product that you'd "
            + "like to purchase",
            ENTER_AREA = "Please enter an area (Note: area must be "
            + "over 100 square feet)",
            SUCCESSFUL_ORDER_ADD = "=== Order added Successfully ===";
            
    
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
    
    public void displayOrders(List<Order> orders) {
        for (Order currentOrder: orders) {
           final String formattedOrder = formatOrder(currentOrder);
           print(formattedOrder);    
        }
    }
    private String formatOrder(Order order) {
        final String orderFormat = "Order Number: %s | "
                 + "Customer Name: %s | State: %s | Tax Rate: %s | "
                 + "Product Type: %s | Area: %s | Cost per Square Foot: %s | "
                 + "Labor Cost Per Square Foot: %s | Material Cost: %s | "
                 + "Labor Cost: %s | Tax: %s | "
                 + "Total: %s";
        final int orderNumber = order.getOrderNumber();
           final String customerName = order.getCustomerName();
           final String stateAbbrv = order.getState();
           final String orderTaxRate = order.getTaxRate().toString();
           final String productType = order.getProductType();
           final String orderArea = order.getArea().toString();
           final String costPerSquareFoot = order.getCostPerSquareFoot().toString();
           final String laborCostPerSquareFoot = order.getLaborCostPerSquareFoot().toString();
           final String materialCost = order.getMaterialCost().toString();
           final String laborCost = order.getLaborCost().toString();
           final String tax = order.getTax().toString();
           final String total = order.getTotal().toString();
        final String orderFormatted = String.format(orderFormat, orderNumber, customerName, stateAbbrv,
                orderTaxRate, productType, orderArea, costPerSquareFoot,
                laborCostPerSquareFoot, materialCost, laborCost, tax,
                total);
        return orderFormatted;
    }
    public int getUserItemChoice() {
        final int userChoice = io.readItemChoice(PLEASE_ENTER_CHOICE);
        return userChoice;
    }
    
    public LocalDateTime getUserDateChoice() {
        final LocalDateTime userDate = io.readLocalDateTime(ENTER_DATE);
        return userDate;
    }

    public LocalDateTime getFutureDate() {
        final LocalDateTime orderDate = io.readFutureLocalDateTime(PLEASE_ENTER_FUTURE_DATE);
        return orderDate;
    }
    public String getCustomerName() {
        final String customerName = io.readCustomerName(PLEASE_ENTER_NAME);
        return customerName;
    }
    public String getOrderState() {
        final String stateAbbrv = 
                io.readStateAbbrv(PLEASE_ENTER_STATE);
        return stateAbbrv;
    }
    public String getNextState() {
        final String stateAbbrv = 
                io.readStateAbbrv(PLEASE_ENTER_STATE);
        return stateAbbrv;
    }
    public boolean getUserContinueChoice() {
        final String YES = "yes".intern();
        final String userContinueChoice = io.readYesOrNo(WOULD_YOU_LIKE_TO_CONTINUE);
        return userContinueChoice == YES;
    }
    public void printStateUnavailableMessage() {
        print(STATE_UNAVILABLE);
    }
    public void printExitedMessage() {
        print(EXIT_MESSAGE);
    }
    public String getProductType(List<String> productTypes) {
        displayProductType(productTypes);
        final String productType = io.readProductType(CHOOSE_PRODUCT_TYPE, productTypes);
        return productType;
    }
    private void displayProductType(List<String> productTypes) {
        for (String productType:productTypes) {
            print(productType);
        }
    }
    public void displayOrderAddedSuccessfullyMessage() {
        print(SUCCESSFUL_ORDER_ADD);
    }
    public BigDecimal getArea() {
        final BigDecimal userArea = io.readArea(ENTER_AREA);
        return userArea;
    }
}
