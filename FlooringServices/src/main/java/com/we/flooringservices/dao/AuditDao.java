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
 * @description This interface declares the method responsible for
 * auditing the services purchased within our flooring services application
 */
public interface AuditDao {
    /**
     * Write an entry to the audit log for the application
     *
     * @param String entry to write to the audit log
     * @return void
     */
    public void writeAuditEntry(String entry) throws FlooringServicesDaoPersistenceException;
}
