package com.sistema.Gestion.service;

import com.sistema.Gestion.model.Bill;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface IBillService {
    
    public List<Bill> getAllBills();
    
    public Bill getBillById(Integer idBill);
    
    public void addModifyBill(Bill bill);
    
    public void deleteBill(Bill bill);
    
    //public List<Bill> findBillByIdCustomerAndDateBetween(Integer idCustomer, Date startDate, Date endDate);
    
    //public List<Bill> findAllByDateBetween(Date startDate, Date endDate);
    
}
