package br.com.db1.db1start.bancoapi.adapter;

import br.com.db1.db1start.bancoapi.dto.EstadoDTO;
import br.com.db1.db1start.bancoapi.entity.Estado;

public class EstadoAdapter {

	
	public static EstadoDTO transformaEntidadeDTO(Estado estado) {
	EstadoDTO dto = new EstadoDTO();
	dto.setId(estado.getId());
	dto.setNome(estado.getNome());
	return dto;
	}
}
