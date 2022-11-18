/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 *
 * @author ciruf
 */
@Component
@Primary
public class UserIOConsoleImpl implements UserIO {
    final static String[] VALID_ABBREVIATIONS = {"AL", "AK", "AS", "AZ", "AR", 
                                        "CA", "CO", "CT", "DE", "DC", "FM", "FL", 
                                        "GA", "GU", "HI", "ID", "IL", "IN", "IA", 
                                        "KS", "KY", "LA", "ME", "MH", "MD", "MA", 
                                        "MI", "MN", "MS", "MO", "MT", "NE", "NV", 
                                        "NH", "NJ", "NM", "NY", "NC", "ND", "MP", 
                                        "OH", "OK", "OR", "PW", "PA", "PR", "RI", 
                                        "SC", "SD", "TN", "TX", "UT", "VT", "VI", 
                                        "VA", "WA", "WV", "WI", "WY"};
    @Override
    public void print(String message) {
        System.out.println(message);
    }
    @Override
    public int readItemChoice(String message) {
        print(message);
        final int MINIMUM_CHOICE = 1, MAX_CHOICE = 6;
        boolean isValid = false;
        int userChoiceInt = 0;
        while (isValid != true) {
            final String userChoice = readString();
            try {
                userChoiceInt = Integer.parseInt(userChoice);
            } catch(NumberFormatException error) {
                print("Input invalid. Please enter an integer.");
                continue;
            }
            if (userChoiceInt < MINIMUM_CHOICE || userChoiceInt > MAX_CHOICE) {
                print("Out of range. Please enter a choice between 1 and 6.");
            } else {
                isValid = true;
            }
        }
        return userChoiceInt;
    }
    @Override
    public BigDecimal readBigDecimal(String message) {
        final int TWO_DECIMAL_PLACES = 2;
        print(message);
        boolean isValid = false;
        BigDecimal userBigDecimal = new BigDecimal("0");
        while(isValid != true) {
            final String userChoice = readString();
            try {
                userBigDecimal = new BigDecimal(userChoice)
                        .setScale(TWO_DECIMAL_PLACES, 
                                RoundingMode.HALF_UP);
            } catch(NumberFormatException error) {
                print("Input could not be parsed to a moentary value."
                        + "Please input a monetary value in this format "
                        + "0.00");
                continue;
            }
            isValid = true;
        }
        return userBigDecimal;
    }
    private BigDecimal readBigDecimal() {
        final int TWO_DECIMAL_PLACES = 2;
        boolean isValid = false;
        BigDecimal userBigDecimal = new BigDecimal("0");
        while(isValid != true) {
            final String userChoice = readString();
            try {
                userBigDecimal = new BigDecimal(userChoice)
                        .setScale(TWO_DECIMAL_PLACES, 
                                RoundingMode.HALF_UP);
            } catch(NumberFormatException error) {
                print("Input could not be parsed to a moentary value."
                        + "Please input a monetary value in this format "
                        + "0.00");
                continue;
            }
            isValid = true;
        }
        return userBigDecimal;
    }

    @Override
    public LocalDateTime readLocalDateTime(String message) {
        final DateTimeFormatter datePattern = 
                DateTimeFormatter.ofPattern("MM/dd/yyyy");
        print(message);
        boolean isValid = false;
        LocalDateTime userDate = null;
        while(isValid != true) {
            String userDateString = readString();
           try {
                userDate = LocalDate.parse(userDateString, 
                        datePattern).atStartOfDay();
           } catch(DateTimeParseException error) {
                print("Invalid date. Please input a date of pattern "
                        + "MM/dd/yyyy, for example: 01/23/2022 or 12/05/2022.");
                continue;
           }
           isValid = true;
        }
        return userDate;
    }
    @Override
    public LocalDateTime readFutureLocalDateTime(String message) {
        final long NO_DAYS = 0;
        final DateTimeFormatter datePattern = 
                DateTimeFormatter.ofPattern("MM/dd/yyyy");
        final LocalDateTime currentDateTime = LocalDateTime.now().toLocalDate().atStartOfDay();
        print(message);
        boolean isValid = false;
        LocalDateTime userDate = null;
        while(isValid != true) {
            String userDateString = readString();
           try {
                userDate = LocalDate.parse(userDateString, 
                        datePattern).atStartOfDay();
           } catch(DateTimeParseException error) {
                print("Invalid date. Please input a date of pattern "
                        + "MM/dd/yyyy, for example: 01/23/2022 or 12/05/2022.");
                continue;
           }
           if (userDate.isEqual(currentDateTime) || userDate.isAfter(currentDateTime))
           isValid = true;
           else print("Must be a future date");
        }
        return userDate;
    }
    @Override
    public String readStateAbbrv(String message) {
        print(message);
        final List<String> validAbbreviations = new ArrayList<>(
                Arrays.asList(VALID_ABBREVIATIONS));
        String userStateAbbrv = "";
        boolean isValid = false;
        while(isValid != true) {
            userStateAbbrv = readString().toUpperCase().intern();
            if (!validAbbreviations.contains(userStateAbbrv)) {
                print("Invalid state abbreviation. Please input a valid state.");
                continue;
            }
            isValid = true;
        }
        return userStateAbbrv;   
    }
    @Override
    public String readCustomerName(String message) {
        final String[] validCharacters = {"a","b","c","d","e",
            "f","g","h","i","j","k","l","m","n","o",
            "p","q","r","s","t","u","v","w","x","y","z"," ",
            "0", "1", "2", "3","4","5","6","7","8","9",
            ",",".","A","B","C","D","E","F","G","H","I",
        "J","K","L","M","N","O","P","Q","R","S","T","U",
        "V","W","X","YZ"};
        final String EMPTY_STRING = "";
        final List<String> validCharactersList =
                new ArrayList<>(Arrays.asList(validCharacters));
        print(message);
        String userName = "";
        boolean isValid = false;
        while(isValid != true) {
            userName = readString();
            if (userName.equals(EMPTY_STRING)) {
                print("Must enter name");
                continue;
            }
            boolean invalidChar = false;
            for (int index = 0; index < userName.length(); index += 1) {
                final String currentStringChar = String.valueOf(userName.charAt(index)).intern();
                if (!validCharactersList.contains(currentStringChar)) {
                    invalidChar = true;
                    break;
                }
            }
            if (invalidChar == true) {
                print("Invalid characters "
                    + "found. Name can only contain charcters "
                    + "within the English alphabet as well as "
                    + "numbers from 0 - 9, commas, spaces, and periods. "
                        + "Please enter valid name.");
                continue;
            }
            isValid = true;
        }
        return userName;
    }
    public String readString(String message) {
        print(message);
        Scanner scanner = new Scanner(System.in);
        final String userInput = scanner.nextLine().trim().intern();
        return userInput;
    }
    private String readString() {
        Scanner scanner = new Scanner(System.in);
        final String userInput = scanner.nextLine().trim().intern();
        return userInput;
    }
    public String readYesOrNo(String message) {
        final String INVALID_YES_OR_NO = "Invalid input. Please enter yes or no",
                YES = "yes", NO = "no";
        String yesOrNo;
        do {
          yesOrNo = readString(message).toLowerCase().intern();
          if (yesOrNo != YES && yesOrNo != NO) print(INVALID_YES_OR_NO);
        } while(yesOrNo != YES && yesOrNo != NO);
        return yesOrNo;
    }
    public String readProductType(String message, List<String> productTypes) {
        print(message);
        String userProductChoice = "";
        while(true) {
            userProductChoice = readString();
            if (!productTypes.contains(userProductChoice)) {
                print("Invalid choice. Please enter product "
                        + "type selection exactly as it appears "
                        + "on the list");
                continue;
            } else break;
        }
        return userProductChoice;
    }
    public BigDecimal readArea(String message) {
        final int EQUAL_TO_MIN = 0;
        final BigDecimal MIN_AREA = new BigDecimal("100");
        print(message);
        BigDecimal userArea = new BigDecimal("0");
        while (true) {
            userArea = readBigDecimal();
            if (userArea.compareTo(MIN_AREA) == EQUAL_TO_MIN 
                    || userArea.compareTo(MIN_AREA) > EQUAL_TO_MIN) {
                break;
            } else {
                print("Invalid area. Please enter an area "
                        + "greated than or equal to 100 square feet");
            }
        }
        return userArea;
    }
}
