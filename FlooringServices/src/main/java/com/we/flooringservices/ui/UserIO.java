/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.flooringservices.ui;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description 
 */
public interface UserIO {
     /**
     * Displays the specified message to the user
     *
     * @param String message message to be displayed to user
     * @return void
     */
    public void print(String message);
    /**
     * Prompts user for item choice until a valid integer has been entered
     *
     * @param String message message to be displayed to user
     * @return int representing item or refund choice
     */
    public int readItemChoice(String message);
    
    /**
     * Prompts user to enter string input then
     * returns the string input
     *
     * @param String message input prompt to be displayed to user
     * @return String
     */
    public String readString(String message);
    /**
     * Prompts user to enter name until no valid characters 
     * are present within the name
     *
     * @param String message input prompt to be displayed to user
     * @return String of customer name
     */
    public String readCustomerName(String message);
    /**
     * Prompts user to enter name until no valid characters 
     * are present within the name (empty strings allowed to 
     * signify no edits to be made)
     *
     * @param String message input prompt to be displayed to user
     * @return String of customer name
     */
    public String readEditCustomerName(String message);
    /**
     * Continues to prompt user to enter string input 
     * until it can be parsed to a BigDecimal with a 
     * scale of 2, then returns the BigDecimal input
     *
     * @param String message input prompt to be displayed to user
     * @return BigDecimal
     */
    public BigDecimal readBigDecimal(String message);
    /**
     * Continues to prompt user to enter string input 
     * until it can be parsed to a LocalDateTime object 
     * instance
     *
     * @param String message input prompt to be displayed to user
     * @return LocalDateTime
     */
    public LocalDateTime readLocalDateTime(String message);
    /**
     * Continues to prompt user to enter string input 
     * until it can be parsed to a LocalDateTime object 
     * instance and the date is in the future
     *
     * @param String message input prompt to be displayed to user
     * @return LocalDateTime
     */
    public LocalDateTime readFutureLocalDateTime(String message);
    /**
     * Continues to prompt user to enter string input 
     * until a valid state abbreviation is entered
     *
     * @param String message input prompt to be displayed to user
     * @return String
     */
    public String readStateAbbrv(String message);
    /**
     * Continues to prompt user to enter string input 
     * until a valid state abbreviation or an empty string
     * is entered
     *
     * @param String message input prompt to be displayed to user
     * @return String of stae abbreviation or empty string
     */
    public String readEditStateAbbrv(String message);
    /**
     * Prompts user for a yes or no answer and continues to do so until 
     * their answer is yes or no (case insensitive)
     *
     * @param message message to be displayed to user when prompting
     * them for input
     * @return string character sequence of "yes" or "no"
     */
    public String readYesOrNo(String message);
    /**
     * Prompts user for the type of product to purchase and 
     * continues to do so until a user has entered in a product
     * type as it appears on the list
     *
     * @param message message to be displayed to user when prompting
     * them for input
     * @param List<String> list of the names of available products for 
     * purchase
     * @return string product type name
     */
    public String readProductType(String message, List<String> productTypes);
    /**
     * Prompts user for the type of product to purchase and 
     * continues to do so until a user has entered in a product
     * type as it appears on the list or an empty string
     *
     * @param message message to be displayed to user when prompting
     * them for input
     * @param List<String> list of the names of available products for 
     * purchase
     * @return string product type name or empty string is user would 
     * not like to edit product type
     */
    public String editProductType(String message, List<String> productTypes);
    /**
     * Prompts user for the type area of the order and continues
     * to do so until the area is greater than the minimum area 
     * allowed for purchase
     *
     * @param message message to be displayed to user when prompting
     * them for input
     * @return BigDecimal Area of the user's order
     */
    public BigDecimal readArea(String message);
    /**
     * Prompts user for the type area of the order and continues
     * to do so until the area is greater than the minimum area 
     * allowed for purchase or the area string is empty
     *
     * @param message message to be displayed to user when prompting
     * them for input
     * @return String representing the area of the user's edit
     */
    public String readEditArea(String message);
    /**
     * Prompts user for integer until choice entered can be
     * successfully parsed to an integer
     *
     * @param message message to be displayed to user when prompting
     * them for input
     * @return Int user entered successfully
     */
    public int readInt(String message);
    
}
