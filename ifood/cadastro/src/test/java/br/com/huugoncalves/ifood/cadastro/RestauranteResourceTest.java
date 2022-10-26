package br.com.huugoncalves.ifood.cadastro;

import javax.ws.rs.core.Response.Status;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.huugoncalves.ifood.cadastro.util.TokenUtils;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
public class RestauranteResourceTest {

	private String token;
	
	@BeforeEach
	public void gereToken() throws Exception {
		token = TokenUtils.generateTokenString("/JWTProprietarioClaims.json", null);
	}

	@Test
	@DataSet("restaurantes-cenario-1.yml")
	public void testBuscarRestaurantes() {
		String resultado = given()
				.when().get("/restaurantes")
				.then()
				.statusCode(200)
				.extract().asString();
		Approvals.verify(resultado);
	}
	
	private RequestSpecification given() {
		return RestAssured.given().contentType(ContentType.JSON).header(new Header("Authorization", "Bearer " + token));
	}
	
	@Test
	@DataSet("restaurantes-cenario-1.yml")
	public void testAlterarRestaurante() {
		Restaurante dto = new Restaurante();
		dto.nome = "novoNome";
		Long parameterValue = 123L;
		given()
				.with().pathParam("id", parameterValue)
				.body(dto)
				.when().put("/restaurante/{id}")
				.then()
				.statusCode(Status.NO_CONTENT.getStatusCode())
				.extract().asString();
		
		Restaurante findById = Restaurante.findById(parameterValue);
		Assert.assertEquals(dto.nome, findById.nome);
	}

}