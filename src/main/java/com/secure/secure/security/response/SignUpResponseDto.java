package com.secure.secure.security.response;

import com.secure.secure.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDto {
    private Long id;
    private String fullName;
}
