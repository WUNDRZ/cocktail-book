package org.avasylchuk.cocktailbook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public class UserProfileDto {

    private Long id;
    private String username;
}
