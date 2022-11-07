/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.flooringservices.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Flooring Mastery Project with Spring DI
 * 
 * @description This helper class acts as the service calculator
 * for this application's orders
 * 
 * Each order will have a ServiceCalculator class instance member so that 
 * that all responsibility for performing calculations goes to that order
 * 
 * This demonstrates the benefits of composition
 */
public class ServiceCalculator {
    final private static int TWO_PLACES_SCALE = 2;
    final private static RoundingMode HALF_UP_ROUNDING_MODE = RoundingMode.HALF_UP;
    final private static BigDecimal BIG_DECIMAL_100 = new BigDecimal("100");
    private BigDecimal materialCost, laborCost, tax, totalCost;
    public ServiceCalculator(BigDecimal area, BigDecimal costPerSquareFoot,
            BigDecimal laborCostPerSquareFoot, BigDecimal materialCost,
            BigDecimal laborCost, BigDecimal taxRate) {
        materialCost = calculateMaterialCost(area, costPerSquareFoot);
        laborCost = calculateLaborCost(area, laborCostPerSquareFoot);
        tax = calculateTax(materialCost, laborCost, taxRate);
        totalCost = calculateTotalCost(materialCost, laborCost, tax);
        
    }
    public void updateCalculations(BigDecimal area, BigDecimal costPerSquareFoot,
            BigDecimal laborCostPerSquareFoot, BigDecimal materialCost,
            BigDecimal laborCost, BigDecimal taxRate) {
        materialCost = calculateMaterialCost(area, costPerSquareFoot);
        laborCost = calculateLaborCost(area, laborCostPerSquareFoot);
        tax = calculateTax(materialCost, laborCost, taxRate);
        totalCost = calculateTotalCost(materialCost, laborCost, tax);
    }
    public static BigDecimal calculateMaterialCost(BigDecimal area, BigDecimal costPerSquareFoot) {
        final BigDecimal materialCost = area.multiply(costPerSquareFoot);
        return materialCost;
    }
    public static BigDecimal calculateLaborCost(BigDecimal area, BigDecimal laborCostPerSquareFoot) {
        final BigDecimal laborCost = area.multiply(laborCostPerSquareFoot);
        return laborCost;
    }
    public static BigDecimal calculateTax(BigDecimal materialCost, BigDecimal laborCost, BigDecimal taxRate) {
        final BigDecimal totalTax = materialCost.add(laborCost)
                .multiply(taxRate.divide(
                                BIG_DECIMAL_100, 
                                TWO_PLACES_SCALE, 
                                HALF_UP_ROUNDING_MODE));
        return totalTax;
    }
    public static BigDecimal calculateTotalCost(BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax) {
        final BigDecimal totalCost = materialCost.add(laborCost).add(tax);
        return totalCost;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }
    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }
    
    public BigDecimal getTotalCost() {
        return totalCost;
    }
    
}
