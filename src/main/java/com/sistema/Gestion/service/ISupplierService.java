package com.sistema.Gestion.service;

import com.sistema.Gestion.model.Supplier;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface ISupplierService {
    
    public List<Supplier> getAllSuppliers();
    
    public Supplier getSuplierById(Integer idSupplier);
    
    public void addModifySupplier(Supplier suplier);
    
    public void deleteSupplier(Supplier supplier);
    
}
