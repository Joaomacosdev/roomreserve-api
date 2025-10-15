package br.com.roomreserve.dto.request;

import br.com.roomreserve.model.enums.Status;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record ReservaRequestDTO(

        @NotNull(message = "O ID do usuário é obrigatório")
        @Positive(message = "O ID do usuário deve ser positivo")
        Long usuarioId,

        @NotNull(message = "O ID da sala é obrigatório")
        @Positive(message = "O ID da sala deve ser positivo")
        Long salaId,

        @NotNull(message = "A data de início é obrigatória")
        @FutureOrPresent(message = "A data de início deve ser hoje ou no futuro")
        LocalDate inicio,

        @NotNull(message = "A data de fim é obrigatória")
        @FutureOrPresent(message = "A data de fim deve ser hoje ou no futuro")
        LocalDate fim,

        @NotNull(message = "O status é obrigatório")
        Status status


) {
}

