package com.sistema.Gestion.service;

import com.sistema.Gestion.model.Product;
import java.util.List;

/**
 *
 * @author Lucas
 */
public interface IProductService {
    
    public List<Product> getAllProducts();
    
    public Product getProductForId(Integer idProduct);
    
    public void addModifyProduct(Product product);
    
    public void deleteProduct(Product product);
    
}
