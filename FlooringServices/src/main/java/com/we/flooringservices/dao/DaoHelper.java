/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.flooringservices.dao;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This interface acts as a helper to the DAOs of out application and
 * products static methods many will use such as those to create a comma-separated
 * list of text representing an object, thus keeping out code DRY
 */

public interface DaoHelper {
    public static String createDelimiterSeparatedString(String delimiter, String... items) {
        final int ONE_ITEM = 1, ENDING_STRING_INDEX = items.length - ONE_ITEM;
        String delimiterSeparatedString = "";
        for (int index = 0; index < items.length; index += 1) {
            final String currentItem = items[index];
            if (index == ENDING_STRING_INDEX) {
                delimiterSeparatedString += currentItem;
                break;
            } 
            delimiterSeparatedString += currentItem + delimiter;
        }
        return delimiterSeparatedString;
    }
}
