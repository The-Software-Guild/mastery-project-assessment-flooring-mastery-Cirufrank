/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description TEST STUB: This class represents the file implementation of the
 * OrderDao interface and defines the properties and methods needed
 * to write and read Order information from the "Data/Orders_MMDDYYY.txt"
 * file as well as the "Backup/DataExport.txt" file
 */

@Component
public class OrderDaoFileStubImpl implements OrderDao {
    final private Map<Integer, Order> orders = new HashMap<>();
    final private List<LocalDateTime> allDates = new ArrayList<>();
    final private static String ORDER_NUMBER_HEADER = "orderNumber",
            CUSTOMER_NAME_HEADER = "customerName",
            STATE_HEADER = "state",
            TAX_RATE_HEADER = "taxRate",
            PRODUCT_TYPE_HEADER = "productType",
            AREA_HEADER = "area",
            SQUARE_FOOT_COST_HEADER = "costPerSquareFoot",
            SQUARE_FOOT_LABOR_COST_HEADER = "laborCostPerSquareFoot",
            MATERIAL_COST_HEADER = "materialCost",
            LABOR_COST_HEADER = "laborCost",
            TAX_HEADER = "tax",
            TOTAL_HEADER = "total",
            ORDER_DATE_HEADER = "orderDate",
            ORDER_FILE_BEGINNING_STRING = "Orders_",
            TXT_STRING = ".txt",
            ORDER_DATE_FILE_HEADERS = DaoHelper.createDelimiterSeparatedString(
                    DaoHelper.DELIMITER,
                    ORDER_NUMBER_HEADER, CUSTOMER_NAME_HEADER, STATE_HEADER,
                    TAX_RATE_HEADER, PRODUCT_TYPE_HEADER, AREA_HEADER,
                    SQUARE_FOOT_COST_HEADER, SQUARE_FOOT_LABOR_COST_HEADER,
                    MATERIAL_COST_HEADER, LABOR_COST_HEADER, TAX_HEADER,
                    TOTAL_HEADER),
            ALL_ORDERS_EXPORT_HEADER = DaoHelper.createDelimiterSeparatedString(
                    DaoHelper.DELIMITER,
                    ORDER_NUMBER_HEADER, CUSTOMER_NAME_HEADER, STATE_HEADER,
                    TAX_RATE_HEADER, PRODUCT_TYPE_HEADER, AREA_HEADER,
                    SQUARE_FOOT_COST_HEADER, SQUARE_FOOT_LABOR_COST_HEADER,
                    MATERIAL_COST_HEADER, LABOR_COST_HEADER, TAX_HEADER,
                    TOTAL_HEADER, ORDER_DATE_HEADER);
    
    private String dataDirectoryName, exportAllDataFileName;
    
    public OrderDaoFileStubImpl() {
        dataDirectoryName = "Data";
        exportAllDataFileName = "Backup/DataExport.txt";
    }
    
    @Autowired
    public OrderDaoFileStubImpl(@Value("TestData")String dataDirectoryName, 
            @Value("TestBackup/DataExport-Test.txt")String exportAllDataFileName) {
        this.dataDirectoryName = dataDirectoryName;
        this.exportAllDataFileName = exportAllDataFileName;
    }
    
    @Override
    public List<Order> getAllOrders() {
        loadAllOrders();
        final List<Order> allOrders = new ArrayList<>(orders.values());
        return allOrders;
    }
    
    @Override
    public List<Order> getAllOrdersForDate(LocalDateTime orderDate) {
        final String orderFileName = 
                DaoHelper.createOrderDateFileName(
                   dataDirectoryName + "/" + ORDER_FILE_BEGINNING_STRING, 
                          orderDate,
                     TXT_STRING);
        loadOrdersForDate(orderFileName);
        final List<Order> ordersForDate = 
                new ArrayList<>(orders.values());
        return ordersForDate;
    }
    
    @Override
    public Order getOrder(int orderId) {
        loadAllOrders();
        final Order order = orders.get(orderId);
        return order;
    }
    
    @Override
    public Order removeOrder(Order order) throws IOException {
        final int NO_ORDERS = 0;
        final String orderFileName = 
                DaoHelper.createOrderDateFileName(
                   dataDirectoryName + "/" + ORDER_FILE_BEGINNING_STRING, 
                          order.getOrderDate(),
                     TXT_STRING);
        loadOrdersForDate(orderFileName);
        final Order removedOrder = orders.remove(order.getOrderNumber());
        final List<Order> remainingOrders = new ArrayList<>(orders.values());
        writeOrdersForDate(orderFileName, remainingOrders);
        cleanFiles(orderFileName, order.getOrderDate());
        return removedOrder;
    }
    
    @Override
    public void updateOrder(Order order) {
        loadAllOrders();
        orders.put(order.getOrderNumber(), order);
        writeAllOrders();
    }
    
    @Override
    public void addOrder(Order order) throws IOException {
        final String orderFileName = 
                DaoHelper.createOrderDateFileName(
                   dataDirectoryName + "/" + ORDER_FILE_BEGINNING_STRING, 
                          order.getOrderDate(),
                     TXT_STRING);
        loadOrdersForDate(orderFileName);
        orders.put(order.getOrderNumber(), order);
        final List<Order> allOrdersForDate = new ArrayList<>(orders.values());
        writeOrdersForDate(orderFileName, allOrdersForDate);
    }
    
    @Override
    public void exportAllActiveOrders() {
        loadAllOrders();
        exportAllOrders();
    }
    public List getAllExportedOrders() {
        loadExportedOrders();
        final List<Order> exportedOrders = new ArrayList<>(orders.values());
        return exportedOrders;
    }
    
    private void cleanFiles(String orderFileName, LocalDateTime orderDate) {
        final int NO_ORDERS = 0;
        final List<Order> ordersForDate = getAllOrdersForDate(orderDate);
        if (ordersForDate.size() == NO_ORDERS) {
            final File orderFile = new File(orderFileName);
            orderFile.delete();
        }

    }
    private String marshallOrderNoDate(Order order) {
        final String orderAsText = DaoHelper.createDelimiterSeparatedString(
            DaoHelper.DELIMITER,
                Integer.toString(order.getOrderNumber()), order.getCustomerName(),
                order.getState(), order.getTaxRate().toString(), order.getProductType(),
                order.getArea().toString(), order.getCostPerSquareFoot().toString(),
                order.getLaborCostPerSquareFoot().toString(), 
                order.getMaterialCost().toString(), order.getLaborCost().toString(), 
                order.getTax().toString(), order.getTotal().toString());
        
        return orderAsText;
    }
    
    
    private String marshallOrderWithDate(Order order) {
        final String orderAsText = DaoHelper.createDelimiterSeparatedString(
            DaoHelper.DELIMITER,
                Integer.toString(order.getOrderNumber()), order.getCustomerName(),
                order.getState(), order.getTaxRate().toString(), order.getProductType(),
                order.getArea().toString(), order.getCostPerSquareFoot().toString(),
                order.getLaborCostPerSquareFoot().toString(), 
                order.getMaterialCost().toString(), order.getLaborCost().toString(), 
                order.getTax().toString(), order.getTotal().toString(),
                DaoHelper.formatExportDate(order.getOrderDate()));
        
        return orderAsText;
    }
    
    private Order unMarshallOrderWithDate(String orderAsText) {
        final String[] orderTokens = orderAsText.split(DaoHelper.DELIMITER);
        final int ORDER_NUMBER_INDEX = 0, CUSTOMER_NAME_INDEX = 1,
                STATE_INDEX = 2, TAX_RATE_INDEX = 3, PRODUCT_TYPE_INDEX = 4,
                AREA_INDEX = 5, SQUARE_FOOT_COST_INDEX = 6,
                SQUARE_FOOT_LABOR_COST_INDEX = 7, MATERIAL_COST_INDEX = 8,
                LABOR_COST_INDEX = 9, TAX_INDEX = 10, TOTAL_INDEX = 11,
                ORDER_DATE_INDEX = 12;
        final Order order = new Order(
                Integer.parseInt(orderTokens[ORDER_NUMBER_INDEX]),
        orderTokens[CUSTOMER_NAME_INDEX], orderTokens[STATE_INDEX],
        new BigDecimal(orderTokens[TAX_RATE_INDEX]), orderTokens[PRODUCT_TYPE_INDEX],
        new BigDecimal(orderTokens[AREA_INDEX]),
        new BigDecimal(orderTokens[SQUARE_FOOT_COST_INDEX]),
        new BigDecimal(orderTokens[SQUARE_FOOT_LABOR_COST_INDEX]), 
        new BigDecimal(orderTokens[MATERIAL_COST_INDEX]), 
        new BigDecimal(orderTokens[LABOR_COST_INDEX]),
        new BigDecimal(orderTokens[TAX_INDEX]), 
        new BigDecimal(orderTokens[TOTAL_INDEX]), 
        DaoHelper.parseExportDateString(orderTokens[ORDER_DATE_INDEX]));
        return order;
    }
    
    private Order unMarshallOrderWithoutDate(String orderAsText, LocalDateTime orderDate) {
        final String[] orderTokens = orderAsText.split(DaoHelper.DELIMITER);
        final int ORDER_NUMBER_INDEX = 0, CUSTOMER_NAME_INDEX = 1,
                STATE_INDEX = 2, TAX_RATE_INDEX = 3, PRODUCT_TYPE_INDEX = 4,
                AREA_INDEX = 5, SQUARE_FOOT_COST_INDEX = 6,
                SQUARE_FOOT_LABOR_COST_INDEX = 7, MATERIAL_COST_INDEX = 8,
                LABOR_COST_INDEX = 9, TAX_INDEX = 10, TOTAL_INDEX = 11,
                ORDER_DATE_INDEX = 12;
        final Order order = new Order(
                Integer.parseInt(orderTokens[ORDER_NUMBER_INDEX]),
        orderTokens[CUSTOMER_NAME_INDEX], orderTokens[STATE_INDEX],
        new BigDecimal(orderTokens[TAX_RATE_INDEX]), orderTokens[PRODUCT_TYPE_INDEX],
        new BigDecimal(orderTokens[AREA_INDEX]),
        new BigDecimal(orderTokens[SQUARE_FOOT_COST_INDEX]),
        new BigDecimal(orderTokens[SQUARE_FOOT_LABOR_COST_INDEX]), 
        new BigDecimal(orderTokens[MATERIAL_COST_INDEX]), 
        new BigDecimal(orderTokens[LABOR_COST_INDEX]),
        new BigDecimal(orderTokens[TAX_INDEX]), 
        new BigDecimal(orderTokens[TOTAL_INDEX]), 
        orderDate);
        return order;
    }
    
    private void loadOrdersForDate(String ordersFileName) {
        try {
                Scanner scanner = new Scanner(
                                        new BufferedReader(
                                            new FileReader(ordersFileName)));
                //Here to get rid of headers line
                scanner.nextLine();
                while(scanner.hasNextLine()) {
                    final String currentOrderText = scanner.nextLine();
                    final String orderDateString = 
                            DaoHelper.extractDateFromFileName(ordersFileName);
                    final LocalDateTime orderDate = 
                            DaoHelper.parseFileDateString(orderDateString);
                    if (!allDates.contains(orderDate)) allDates.add(orderDate);
                    Order currentOrder = 
                            unMarshallOrderWithoutDate(currentOrderText,
                                    orderDate);
                    orders.put(currentOrder.getOrderNumber(),currentOrder);
                }
                scanner.close();
            } catch(FileNotFoundException error) {
                System.out.println("-_- Could not find "
                        + "file " + ordersFileName);
            }
    }
    
    private void loadAllOrders() {
        final int NO_ORDER_FILES = 0;
        final File dataDirectory = new File(dataDirectoryName);
        if (!dataDirectory.exists()) return;
        final String[] filesNamesInDataDirectory = dataDirectory.list();
        final String[] orderFileNamesInDataDirectory = 
                Arrays.stream(filesNamesInDataDirectory).filter(fileName ->
                    fileName.contains(ORDER_FILE_BEGINNING_STRING)
                ).toArray(fileName -> new String[fileName]);
        if (orderFileNamesInDataDirectory.length == NO_ORDER_FILES) return;
        Arrays.stream(orderFileNamesInDataDirectory).forEach(orderFileName ->
        {
            orderFileName = dataDirectoryName + "/" + orderFileName;
            loadOrdersForDate(orderFileName);
        }
       );
        
    }
    
    private void loadExportedOrders() {
        try {
            Scanner scanner = 
                    new Scanner(
                        new BufferedReader(
                            new FileReader(exportAllDataFileName)));
            //Here so we don't try to parse headers
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                final String orderAsText = scanner.nextLine();
                final Order currentOrder = unMarshallOrderWithDate(orderAsText);
                orders.put(currentOrder.getOrderNumber(), currentOrder);
            }
            scanner.close();
        } catch(FileNotFoundException error) {
            System.out.println("-_- Unable to find Backups file");
        }
    }
    
    private void writeOrdersForDate(String ordersFileName, List<Order> orders) throws IOException {
        try {
            if (DaoHelper.fileExists(ordersFileName))
                DaoHelper.createNewFile(ordersFileName);
            PrintWriter output = new PrintWriter(
                                        new FileWriter(ordersFileName));
            //Here to print the headers line for the file
            output.println(ORDER_DATE_FILE_HEADERS);
            output.flush();
            for (Order currentOrder: orders) {
                final String orderAsText = marshallOrderNoDate(currentOrder);
                output.println(orderAsText);
                output.flush();
            }
            output.close();
        } catch(FileNotFoundException error) {
            System.out.println("-_- Unable to save orders to date file");
        }
    }
    //Make method that gets all dates when they're loaded
    //And create file from there
    private void writeAllOrders() {
        final List<Order> currentOrders = new ArrayList<>(orders.values());
        final int SAME_DATE = 0;
        for (LocalDateTime orderDate: allDates) {
            final String currentOrderFileName = 
                    DaoHelper.createOrderDateFileName(ORDER_FILE_BEGINNING_STRING, orderDate,
                    TXT_STRING);
            final List<Order> ordersForDate = currentOrders.stream().filter(
                order -> order.getOrderDate().compareTo(orderDate) == SAME_DATE)
                    .collect(Collectors.toList());
            try {
                writeOrdersForDate(currentOrderFileName, ordersForDate);
                
            } catch(IOException error) {
                System.out.println("-_- Orders could not be saved");
            }
        }
    }
    private void exportAllOrders() {   
        final List<Order> allActiveOrders = new ArrayList<>(orders.values());
        try {
            if (!DaoHelper.fileExists(exportAllDataFileName)) 
            DaoHelper.createNewFile(exportAllDataFileName);
            PrintWriter output = new PrintWriter(
                                    new FileWriter(exportAllDataFileName));
            output.println(ALL_ORDERS_EXPORT_HEADER);
            output.flush();
            for (Order currentOrder: allActiveOrders) {
                final String orderAsText = marshallOrderWithDate(currentOrder);
                output.println(orderAsText);
                output.flush();
            }
            output.close();
        } catch(IOException error) {
            System.out.println("-_- Unable to export all orders to file");
        }
    }
}
