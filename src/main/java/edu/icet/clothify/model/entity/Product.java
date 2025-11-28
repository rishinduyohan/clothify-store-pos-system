package edu.icet.clothify.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Database AUTO_INCREMENT
    @Column(name = "product_id")
    private Long productId; // Long type ID

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_path", length = 255)
    private String imagePath;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    // Foreign Key Mapping for Category (BIGINT FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    // Foreign Key Mapping for Supplier (BIGINT FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private Supplier supplier;
}