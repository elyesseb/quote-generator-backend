package com.quote.generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ufhopla
 * on 10/08/2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
}
