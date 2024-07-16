
package com.sistema.Gestion.service;

import com.sistema.Gestion.model.BillProducts;
import com.sistema.Gestion.repository.BillProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillProductService {
    
    @Autowired
    private BillProductRepository billProductRepository;
    
    public void addBillProduct(BillProducts billProducts) {
        billProductRepository.save(billProducts);
    }
    
}
