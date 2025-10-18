package br.com.roomreserve.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UsuarioUpdateRequestDTO(

        @NotNull
        Long id ,
        String nome,

        @Email(message = "O e-mail deve ser válido")
        String email,

        @Pattern(
                regexp = "\\d{10,11}",
                message = "O telefone deve conter apenas números e ter 10 ou 11 dígitos"
        )
        String telefone
) {
}
