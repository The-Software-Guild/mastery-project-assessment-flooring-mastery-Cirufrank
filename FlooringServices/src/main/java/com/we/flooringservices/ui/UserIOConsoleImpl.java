/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.ui;

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
    public void print(String message) {
        System.out.println(message);
    }
    public int readItemChoice(String message) {
        print(message);
        final int MINIMUM_CHOICE = 1, MAX_CHOICE = 6;
        boolean isValid = false;
        int userChoiceInt = 0;
        while (isValid != true) {
            Scanner scanner = new Scanner(System.in);
            String userChoice = scanner.nextLine().trim();
            try {
                userChoiceInt = Integer.parseInt(userChoice);
            } catch(NumberFormatException error) {
                print("Input invalid. Please enter and integer");
                break;
            }
            if (userChoiceInt < MINIMUM_CHOICE || userChoiceInt > MAX_CHOICE) {
                print("Out of range. Please enter a choice between 1 and 6");
            } else {
                isValid = true;
            }
        }
        return userChoiceInt;
    }
}
