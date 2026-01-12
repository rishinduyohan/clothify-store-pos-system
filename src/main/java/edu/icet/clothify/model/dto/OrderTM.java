package edu.icet.clothify.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderTM {
    private String orderId;
    private String productName;
    private String date;
    private Double amount;
    private String paymentMethod;
}
