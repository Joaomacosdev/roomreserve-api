package br.com.roomreserve.dto.response;

import br.com.roomreserve.model.Sala;

public record SalaResponseDTO(
        Long id,
        String nome,
        Integer capacidade,
        Boolean ativo
) {
    public SalaResponseDTO(Sala sala) {
        this(sala.getId(), sala.getNome(), sala.getCapacidade(), sala.isAtivo());
    }
}
