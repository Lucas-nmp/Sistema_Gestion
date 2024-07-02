/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sistema.Gestion.repository;

import com.sistema.Gestion.model.Bill;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Lucas
 */
public interface BillRepository extends JpaRepository<Bill, Integer>{
    
    
    /*
    @Query("SELECT b FROM Bill b WHERE b.id_customer = :idCustomer AND b.date_bill BETWEEN :startDate AND :endDate")
    List<Bill> findBillByIdCustomerAndDateBetween(
        @Param("idCustomer") Integer idCliente,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );
    
    @Query("SELECT b FROM Bill b WHERE b.date_bill BETWEEN :startDate AND :endDate")
    List<Bill> findAllByDateBetween(
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate    
    );
*/
    
    
    
}
