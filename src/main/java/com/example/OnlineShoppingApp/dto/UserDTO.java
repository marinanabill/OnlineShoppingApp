// UserDTO.java
package com.example.OnlineShoppingApp.dto;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password; // be careful: do not send this in responses in prod
    private CartDTO cart;
    private List<OrderDTO> orders;
}
