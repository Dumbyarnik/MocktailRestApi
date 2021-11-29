package de.hsos.swa.mocktail.suchen.boundary.rest;

import org.junit.jupiter.api.Test;

import de.hsos.swa.mocktail.suchen.entity.Mocktail;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class KatalogResourceTest {

    @Test
    void testGetWaren() {
        given()
            .pathParam("id", "1").body(new Mocktail("1", "Tets"))
            .when().get("ware/{id}")
            .then()
                .statusCode(200);
                //.body(is("{\"name\":\"Tequila Sunrise\",\"produktinformation\":[{\"bezeichnung\":\"Wasser\",\"id\":0}],\"warennummer\":\"1\"}"));
    }

    @Test
    void testDeleteWare1() {
        given()
            .pathParam("id", "2")
            .when().delete("ware/{id}")
            .then()
                .statusCode(200);
    }

    @Test
    void testDeleteWare2() {
        given()
            .pathParam("id", "3")
            .when().delete("ware/{id}")
            .then()
                .statusCode(404)
                .body(is("Ware not found"));;
    }

    /*@Test
    void testNewWare() {
        String mocktail_name = "Pinacolada";

        given()
            .pathParam("mocktail", mocktail_name)
            .when().post("ware/{mocktail}")
            .then()
            .statusCode(201);
    }*/

    

    @Test
    void testSetDescriptionToWare() {
        String mocktail_name = "1";

        given()
            .pathParam("Neuer_Mocktailname", mocktail_name)
            .when().put("http://localhost:8080/ware/{Neuer_Mocktailname}?Zutat=Juice")
            .then()
                .statusCode(200);
                //.body(is("{\"name\":\"Tequila Sunrise\",\"produktinformation\":[{\"bezeichnung\":\"Wasser\",\"id\":0}],\"warennummer\":\"1\"}"));
    }
}
