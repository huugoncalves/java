package br.com.huugoncalves.ifood.cadastro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.huugoncalves.ifood.cadastro.Restaurante;
import br.com.huugoncalves.ifood.cadastro.dto.AdicionarRestauranteDTO;
import br.com.huugoncalves.ifood.cadastro.dto.AtualizarRestauranteDTO;
import br.com.huugoncalves.ifood.cadastro.dto.RestauranteDTO;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {
	
	@Mapping(target = "nome", source = "nomeFantasia")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataAtualizacao", ignore = true)
	@Mapping(target = "localizacao.id", ignore = true)
	public Restaurante toRestaurante(AdicionarRestauranteDTO adicionarRestauranteDTO);
	
	@Mapping(target = "nome", source = "nomeFantasia")
	public void toRestaurante(AtualizarRestauranteDTO atualizarRestauranteDTO, @MappingTarget Restaurante restaurante);
	
	@Mapping(target = "nomeFantasia", source = "nome")
	@Mapping(target = "dataCriacao", dateFormat = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	public RestauranteDTO toRestauranteDTO(Restaurante restaurante);

}
