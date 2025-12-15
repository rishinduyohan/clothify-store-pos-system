package edu.icet.clothify.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String contactNumber;
    private String email;
}
