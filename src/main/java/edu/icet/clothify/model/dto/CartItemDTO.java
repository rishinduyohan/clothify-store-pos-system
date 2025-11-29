package edu.icet.clothify.model.dto;

import edu.icet.clothify.model.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemDTO {
    private ProductDTO product;
    private Integer quantity;
    private BigDecimal total;

    public CartItemDTO(ProductDTO product,Integer quantity){
        this.product = product;
        this.quantity = quantity;
        calculateTotal();
    }

    public void calculateTotal(){
        this.total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
