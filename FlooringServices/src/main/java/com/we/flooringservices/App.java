/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.we.flooringservices;

import com.we.flooringservices.controller.FlooringServicesController;
import java.io.File;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This application allows users to add, edit, read, delete, and 
 * export orders for flooring materials and services
 * 
 * It uses the MVC architectural paradigm to organize files
 * in order to keep code DRY while separating concerns
 * 
 * Through the use of file storage the order information of 
 * items persists
 * 
 * Additionally, Spring dependency injection is utilized in order to take advantage
 * of loose coupling through Spring's Inversion of Control container
 * 
 *
 * 
 * 
 */

public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.we.flooringservices");
        appContext.refresh();
          
        FlooringServicesController controller = appContext.getBean("flooringServicesController", FlooringServicesController.class);
        controller.run();
    }
}
