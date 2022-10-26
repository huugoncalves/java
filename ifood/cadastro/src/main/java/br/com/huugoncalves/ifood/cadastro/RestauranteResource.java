package br.com.huugoncalves.ifood.cadastro;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.huugoncalves.ifood.cadastro.dto.AdicionarPratoDTO;
import br.com.huugoncalves.ifood.cadastro.dto.AdicionarRestauranteDTO;
import br.com.huugoncalves.ifood.cadastro.dto.AtualizarPratoDTO;
import br.com.huugoncalves.ifood.cadastro.dto.AtualizarRestauranteDTO;
import br.com.huugoncalves.ifood.cadastro.dto.PratoDTO;
import br.com.huugoncalves.ifood.cadastro.dto.RestauranteDTO;
import br.com.huugoncalves.ifood.cadastro.infra.ConstraintViolationResponse;
import br.com.huugoncalves.ifood.cadastro.mapper.PratoMapper;
import br.com.huugoncalves.ifood.cadastro.mapper.RestauranteMapper;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Restaurante")
@RolesAllowed("proprietario")
@SecurityScheme(securitySchemeName = "ifood-oauth", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows
(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token")))
@SecurityRequirement(name = "ifood-oauth", scopes = {})
public class RestauranteResource {

	@Inject
	RestauranteMapper restauranteMapper;

	@Inject
	PratoMapper pratoMapper;

	@GET
	@Counted(name = "buscarRestaurantes")
	public List<RestauranteDTO> buscarRestaurantes() {
		Stream<Restaurante> restaurantes = Restaurante.streamAll();
		return restaurantes.map(r -> restauranteMapper.toRestauranteDTO(r)).collect(Collectors.toList());
	}

	@POST
	@Transactional
	@APIResponse(responseCode = "201", description = "Caso restaurante seja cadastrado com sucesso.")
	@APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = ConstraintViolationResponse.class)))
	public Response adicionarRestaurante(@Valid AdicionarRestauranteDTO dto) {
		Restaurante restaurante = restauranteMapper.toRestaurante(dto);
		restaurante.persist();
		return Response.status(Status.CREATED).build();
	}

	@PUT
	@Path("{id}")
	@Transactional
	public void atualizarRestaurante(@PathParam("id") Long id, AtualizarRestauranteDTO dto) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException();
		}
		Restaurante restaurante = restauranteOp.get();

		restauranteMapper.toRestaurante(dto, restaurante);
		restaurante.persist();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public void removerRestaurante(@PathParam("id") Long id) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
		restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {
			throw new NotFoundException();
		});
	}

	// Pratos

	@GET
	@Path("{idRestaurante}/pratos")
	@Tag(name = "Prato")
	public List<PratoDTO> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante inexistente!");
		}
		Stream<Prato> pratos = Prato.stream("restaurante", restauranteOp.get());
		return pratos.map(p -> pratoMapper.toDTO(p)).collect(Collectors.toList());
	}

	@POST
	@Path("{idRestaurante}/pratos")
	@Transactional
	@Tag(name = "Prato")
	public Response adicionarPrato(@PathParam("idRestaurnte") Long idRestaurante, AdicionarPratoDTO dto) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante inexistente!");
		}
		Prato prato = pratoMapper.toPrato(dto);
		prato.restaurante = restauranteOp.get();
		prato.persist();
		return Response.status(Status.CREATED).build();
	}

	@PUT
	@Path("{idRestaurante}/pratos/{id}")
	@Transactional
	@Tag(name = "Prato")
	public Response atualizarPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id,
			AtualizarPratoDTO dto) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante inexistente!");
		}

		Optional<Prato> pratoOp = Prato.findByIdOptional(id);
		if (pratoOp.isEmpty()) {
			throw new NotFoundException("Prato inexistente!");
		}
		Prato prato = pratoOp.get();
		pratoMapper.toPrato(dto, prato);
		prato.persist();
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Path("{idRestaurante}/pratos")
	@Transactional
	@Tag(name = "Prato")
	public Response removerPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
		Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
		if (restauranteOp.isEmpty()) {
			throw new NotFoundException("Restaurante inexistente!");
		}
		Optional<Prato> pratoOp = Prato.findByIdOptional(id);
		pratoOp.ifPresentOrElse(Prato::delete, () -> {
			throw new NotFoundException("Prato inexistente!");
		});
		return Response.status(Status.OK).build();
	}
}