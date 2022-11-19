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
 * @description This class acts the the view layer of out application 
 * and is responsible for displaying and retrieving user input and 
 * output throughout the application
 */
@Component
@Primary
public class FlooringServicesView {
    final private String WELCOME_MESSAGE = "=== WELCOME TO OUR FLOORING SERVICES ===",
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
            WOULD_YOU_LIKE_TO_CONTINUE = "Would you like to continue? (Yes or No)",
            STATE_UNAVILABLE = "Sorry, the state entered is "
            + "unavailable for services. We have taken note of this "
            + "and logged the state specified to our requests.",
            EXIT_MESSAGE = "Sorry to see you go!",
            CHOOSE_PRODUCT_TYPE = "Please select the product that you'd "
            + "like to purchase",
            ENTER_AREA = "Please enter an area (Note: area must be "
            + "over 100 square feet)",
            SUCCESSFUL_ORDER_ADD = "=== Order added Successfully ===",
            ENTER_ORDER_NUMBER = "Please enter the order's number",
            ORDER_EXISTS_BUT_NOT_FOR_DATE = "No order was found for the date specified, "
            + "but an order does exists for that number."
            + "\nFor security purposes, we cannot disclose the date, however, "
            + "would you like to put in another date?",
            NO_ORDERs_EXISTS_FOR_NUMBER = "No orders exist for both the "
            + "date and number specified. Would you like to enter another order?",
            ENTER_FOR_NO_CHANGES = "Please press enter if you would not like to change this entry",
            EMPTY_STRING = "".intern(),
            ORDER_SUMMARY_BANNER = "=== ORDER SUMMARY ===",
            UPDATE_SUCCESS_MESSAGE = "=== ORDER UPDATE SUCCESS ===",
            REMOVE_SUCCESS_BANNER = "=== ORDER REMOVED ===",
            ORDER_NOT_REMOVED_MESSAGE = "Order not removed.",
            ORDERS_SUCCESSFULLY_EXPORTED = "=== ORDERS SUCCESSFULLY EXPORTED ===",
            ASTERISK = "*",
            AVAILABLE_PRODUCTS_BANNER = "=== AVAILABLE PRODUCTS ===",
            NO_ORDER_PLACED = "Order not placed.",
            GOODBYE_MESSAGE = "=== GOODBYE, COME AGAIN SOON! ===";
            
    
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
    public void displayOrder(Order order) {
        final String newOrderString = formatOrder(order);
        io.print(newOrderString);
    }
    public void displayRemoveSuccessMessage() {
        io.print(REMOVE_SUCCESS_BANNER);
    }
    public void displayOrderNotRemoved() {
        io.print(ORDER_NOT_REMOVED_MESSAGE);
    }
    public void displayAllOrdersExportedBanner() {
        io.print(ORDERS_SUCCESSFULLY_EXPORTED);
    }
    public boolean displayOrderAndGetContinueChoice(Order newOrder) {
        displayOrder(newOrder);
        return getUserContinueChoice();
    }
    public String editCustomerName(Order order) {
        String customerName = io.readEditCustomerName("Enter customer name (" + order.getCustomerName()
        + ")\n" + ENTER_FOR_NO_CHANGES);
        return customerName;
    }
    public String editOrderState(Order order) {
        String customerState = io.readEditStateAbbrv("Enter order state (" + order.getState()
        + ")\n" + ENTER_FOR_NO_CHANGES);
        return customerState;
    }
    public String editArea(Order order) {
        String customerAreaString = io.readEditArea("Enter order area (" + order.getArea()
        + ")\n" + ENTER_FOR_NO_CHANGES);
        return customerAreaString;
    }
    public void displayOrderEdits(Order order) {
        final String formattedOrder = formatOrder(order);
        io.print(ORDER_SUMMARY_BANNER);
        io.print(formattedOrder);
    }
    public void displayEditSuccessBanner() {
        io.print(UPDATE_SUCCESS_MESSAGE);
    }
    public void displayNoEditsMade(Order order) {
        io.print("No edits made to order number " + order.getOrderNumber());
    }
    public String editProductType(Order order, List<String> availableProducts) {
        displayProductType(availableProducts);
        final String productType = io.editProductType("Enter product type (" + order.getProductType()
        + ")\n" + ENTER_FOR_NO_CHANGES, availableProducts);
        return productType;
    }
    public int getUserItemChoice() {
        final int userChoice = io.readItemChoice(PLEASE_ENTER_CHOICE);
        return userChoice;
    }
    public int getOrderNumber() {
        final int orderNumber = io.readInt(ENTER_ORDER_NUMBER);
        return orderNumber;
    }
    public LocalDateTime getUserDateChoice() {
        final LocalDateTime userDate = io.readLocalDateTime(ENTER_DATE);
        return userDate;
    }
    public void printOrderDoesNotExistMessage() {
        io.print(NO_ORDERs_EXISTS_FOR_NUMBER);
    }
    public void printOrderExistsButNoOrderForDateMessage() {
        io.print(ORDER_EXISTS_BUT_NOT_FOR_DATE);
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
        print(AVAILABLE_PRODUCTS_BANNER);
        print(BORDER);
        displayProductType(productTypes);
        print(BORDER);
        String productType = io.readProductType(CHOOSE_PRODUCT_TYPE, productTypes);
        return productType;
    }
    private void displayProductType(List<String> productTypes) {
        for (String productType:productTypes) {
            print(ASTERISK + " " + productType);
        }
    }
    public void displayOrderAddedSuccessfullyMessage() {
        print(SUCCESSFUL_ORDER_ADD);
    }
    public void displayOrderNotPlacedMessage() {
        print(NO_ORDER_PLACED);
    }
    public void displayGoodbyeBanner() {
        print(GOODBYE_MESSAGE);
    }
    public BigDecimal getArea() {
        final BigDecimal userArea = io.readArea(ENTER_AREA);
        return userArea;
    }
}
