/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.flooringservices.dao;

/**
 *
 * @author ciruf
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
