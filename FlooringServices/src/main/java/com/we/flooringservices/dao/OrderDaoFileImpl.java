/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.dao;

import com.we.flooringservices.model.Order;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ciruf
 */
public class OrderDaoFileImpl implements OrderDao {
    final private Map<Integer, Order> allOrders = new HashMap<>();
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
    
    public OrderDaoFileImpl() {
        dataDirectoryName = "Data";
        exportAllDataFileName = "Backup/DataExport.txt";
    }
    
    public OrderDaoFileImpl(String dataDirectoryName, String exportAllDataFileName) {
        this.dataDirectoryName = dataDirectoryName;
        this.exportAllDataFileName = exportAllDataFileName;
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
                order.getOrderDate().toString());
        
        return orderAsText;
    }
    
    public Order unMarshallOrderWithDate(String orderAsText) {
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
    
    public List<Order> getAllOrders() {
        final int NO_ORDER_FILES = 0;
        final File dataDirectory = new File(dataDirectoryName);
        if (!dataDirectory.exists()) return null;
        final String[] filesNamesInDataDirectory = dataDirectory.list();
        final String[] orderFileNamesInDataDirectory = 
                Arrays.stream(filesNamesInDataDirectory).filter(fileName ->
                    fileName.contains(ORDER_FILE_BEGINNING_STRING)
                ).toArray(fileName -> new String[fileName]);
        if (orderFileNamesInDataDirectory.length == NO_ORDER_FILES) return null;
        
        Arrays.stream(orderFileNamesInDataDirectory).forEach(orderFileName ->
        {
            
        }
        );
        
    }
}
