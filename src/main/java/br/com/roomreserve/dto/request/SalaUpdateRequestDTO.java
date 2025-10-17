package br.com.roomreserve.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SalaUpdateRequestDTO(

        @NotNull
        Long id,

        @NotBlank(message = "O nome da sala é obrigatório")
        String nome,

        @Positive(message = "A capacidade deve ser um número positivo")
        Integer capacidade,

        Boolean ativo
) {
}
