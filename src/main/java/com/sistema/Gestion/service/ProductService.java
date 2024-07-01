package com.sistema.Gestion.service;

import com.sistema.Gestion.model.Product;
import com.sistema.Gestion.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lucas
 */
@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductForId(Integer idProduct) {
        Product product = productRepository.findById(idProduct).orElse(null);
        return product;
    }

    @Override
    public void addModifyProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
    
    
    
    
}
