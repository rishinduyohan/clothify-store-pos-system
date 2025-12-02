package edu.icet.clothify.model.entity;

import edu.icet.clothify.model.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItem {
    private ProductDTO product;
    private Integer quantity;
    private BigDecimal total;

    public CartItem(ProductDTO product, Integer quantity){
        this.product = product;
        this.quantity = quantity;
        calculateTotal();
    }

    public void calculateTotal(){
        this.total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
