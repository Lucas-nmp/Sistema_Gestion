package com.sistema.Gestion.service;

import com.sistema.Gestion.model.Supplier;
import com.sistema.Gestion.repository.SupplierRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas
 */
@Service
public class SupplierService implements ISupplierService{

    @Autowired
    private SupplierRepository supplierRepository;
    
    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier getSuplierById(Integer idSupplier) {
        Supplier supplier = supplierRepository.findById(idSupplier).orElse(null);
        return supplier;
    }

    @Override
    public void addModifySupplier(Supplier suplier) {
        supplierRepository.save(suplier);
    }

    @Override
    public void deleteSupplier(Supplier supplier) {
        supplierRepository.delete(supplier);
    }
    
}
