package br.com.huugoncalves.ifood.cadastro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.huugoncalves.ifood.cadastro.Prato;
import br.com.huugoncalves.ifood.cadastro.dto.AdicionarPratoDTO;
import br.com.huugoncalves.ifood.cadastro.dto.AtualizarPratoDTO;
import br.com.huugoncalves.ifood.cadastro.dto.PratoDTO;

@Mapper(componentModel = "cdi")
public interface PratoMapper {
	
	@Mapping(target = "id", ignore = true)
	PratoDTO toDTO(Prato prato);
	
	Prato toPrato(AdicionarPratoDTO adicionarPratoDTO);
	
	void toPrato(AtualizarPratoDTO atualizarPratoDTO, @MappingTarget Prato prato);

}
