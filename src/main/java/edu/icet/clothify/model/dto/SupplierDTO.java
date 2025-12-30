package edu.icet.clothify.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierDTO {
    private Long supplierId;
    private String companyName;
    private String contactPerson;
    private String contactNumber;
    private String itemCategory;
    private String email;
}
