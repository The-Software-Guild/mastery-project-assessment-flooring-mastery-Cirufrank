/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.we.flooringservices.dao;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    final public static String DELIMITER = ",",
            FILE_DATE_FORMAT = "ddMMyyyy",
            EXPORT_DATE_FORMAT = "dd-MM-yyyy";
    final static int DATE_SUBSTRING_BEGINNING_INDEX = 7;
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
    
    public static LocalDateTime parseFileDateString(String dateString) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FILE_DATE_FORMAT);
        final LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return localDateTime;
    }
    
    public static LocalDateTime parseExportDateString(String dateString) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EXPORT_DATE_FORMAT);
        final LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return localDateTime;
    }
    
    public static String formatFileDate(LocalDateTime localDateTime) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FILE_DATE_FORMAT);
        final String formattedDate = localDateTime.format(formatter);
        return formattedDate;
    }
    public static String formatExportDate(LocalDateTime localDateTime) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EXPORT_DATE_FORMAT);
        final String formattedDate = localDateTime.format(formatter);
        return formattedDate;
    }
    
    public static String extractDateFromFileName(String fileName) {
        final String dateFromFileName = fileName.substring(DATE_SUBSTRING_BEGINNING_INDEX).trim();
        return dateFromFileName;
    }
    
    public static boolean fileExists(String fileName) {
        final File currentFile = new File(fileName);
        return currentFile.exists();
    }
    public static void createNewFile(String fileName) {
        final File currentFile = new File(fileName);
        try {
            currentFile.createNewFile();
        } catch(IOException error) {
            System.out.println("-_- File " + fileName + " "
                    + "could not be created");
        }
    }
}
