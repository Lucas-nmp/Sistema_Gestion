/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.Gestion.service;

import com.sistema.Gestion.model.Bill;
import com.sistema.Gestion.repository.BillRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas
 */
@Service
public class BillService implements IBillService{

    @Autowired
    private BillRepository billRepository;
    
    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Integer idBill) {
        Bill bill = billRepository.findById(idBill).orElse(null);
        return bill;
    }

    @Override
    public void addModifyBill(Bill bill) {
        billRepository.save(bill);
        
    }

    @Override
    public void deleteBill(Bill bill) {
        billRepository.delete(bill);
    }
    
    

    /*
    @Override
    public List<Bill> findBillByIdCustomerAndDateBetween(Integer idCustomer, Date startDate, Date endDate) {
        return billRepository.findBillByIdCustomerAndDateBetween(idCustomer, startDate, endDate);
    }

    @Override
    public List<Bill> findAllByDateBetween(Date startDate, Date endDate) {
        return billRepository.findAllByDateBetween(startDate, endDate);
                
    }*/

    

    
    
}
