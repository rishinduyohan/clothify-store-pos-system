package edu.icet.clothify.model.dto;

import lombok.*;

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
    private Double price;
    private Integer stockQuantity;
    private String categoryId;
    private String supplierId;
}
