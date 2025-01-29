package org.avasylchuk.cocktailbook;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {

    private String username;
    private String password;
}
