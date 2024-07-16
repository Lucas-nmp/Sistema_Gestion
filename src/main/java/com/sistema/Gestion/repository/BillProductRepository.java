package com.sistema.Gestion.repository;

import com.sistema.Gestion.model.BillProducts;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BillProductRepository extends JpaRepository<BillProducts, Integer>{
    
}
