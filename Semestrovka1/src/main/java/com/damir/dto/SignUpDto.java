package com.damir.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    @Pattern(regexp=".+@.+\\..+", message="Неверный формат email")
    private String email;

    @NotBlank(message = "Обязательно для ввода")
    @Size(min = 2, message = "Должно быть больше 4 символов")
    @Size(max = 16, message = "Должно быть меньше 16 символов")
    private String password;

    @NotBlank(message = "Обязательно для ввода")
    @Size(min = 2, message = "Должно быть больше 4 символов")
    @Size(max = 16, message = "Должно быть меньше 16 символов")

    private String passwordRepeat;

    @NotBlank(message = "Обязательно для ввода")
    @Size(min = 2, message = "Должно быть больше 2 символов")
    @Size(max = 16, message = "Должно быть меньше 16 символов")
    private String firstName;

    @NotBlank(message = "Обязательно для ввода")
    @Size(min = 2, message = "Должно быть больше 2 символов")
    @Size(max = 16, message = "Должно быть меньше 16 символов")
    private String lastName;

    private String position;


}
