package br.com.roomreserve.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SalaRequestDTO(
        @NotBlank(message = "O nome da sala é obrigatório")
        String nome,

        @NotNull(message = "A capacidade é obrigatória")
        @Positive(message = "A capacidade deve ser um número positivo")
        Integer capacidade,

        @NotNull(message = "O status de ativo é obrigatório")
        Boolean ativo
) {
}
