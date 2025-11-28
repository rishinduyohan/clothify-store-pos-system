package edu.icet.clothify.model.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private String imagePath;
    private BigDecimal price;
    private Integer stockQuantity;
    private Long categoryId;
    private Long supplierId;
}
