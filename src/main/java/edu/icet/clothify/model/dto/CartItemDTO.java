package edu.icet.clothify.model.dto;

import edu.icet.clothify.model.entity.Product;

import java.math.BigDecimal;

public class CartItemDTO {
    private Product product;
    private Integer quantity;
    private BigDecimal total;

    public CartItemDTO(Product product,Integer quantity){
        this.product = product;
        this.quantity = quantity;
        getTotalValue();
    }

    public void getTotalValue(){
        this.total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
