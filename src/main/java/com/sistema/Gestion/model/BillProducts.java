package com.sistema.Gestion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillProducts {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBillProduct;
    
    private Integer idBill;
    private Integer idProduct;
    private Integer amount;
}
