package org.example.backendip.property;

import io.restassured.RestAssured;
import org.example.backendip.Models.Property;
import org.example.backendip.Repositories.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PropertyEndToEndTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PropertyRepository propertyRepository;

    private ArrayList<Property> properties = new ArrayList<>();

    @BeforeEach
    public void setup() {
        RestAssured.port = this.port;

        this.propertyRepository.deleteAll();
        this.properties.clear();

        Property property1 = new Property();
        property1.setAddress("123 King Street");
        property1.setSuburb("Burwood");
        property1.setState(("NSW"));
        property1.setPurchasePrice(BigDecimal.valueOf(12300000));
        property1.setWeeklyRent(BigDecimal.valueOf(5000));

        this.propertyRepository.save(property1);
        this.properties.add(property1);
    }

    @Test
    public void getAllProperties_PropertiesInDB_ReturnsSuccess() {
        given().when().get("/api/property").then().statusCode(HttpStatus.OK.value()).body("$", hasSize(1));
    }

    @Test
    public void getPropertyById_ValidId_ReturnSuccessAndCorrectInformation() {
        Property existingProperty = this.properties.get(0);
        given()
                .when()
                .get("/api/property/" + existingProperty.getId())
                .then().statusCode(HttpStatus.OK.value())
                .body("address", equalTo("123 King Street"))
                .body("suburb", equalTo("Burwood"))
                .body("state", equalTo("NSW"))
                .body("purchasePrice", equalTo(12300000f))
                .body("weeklyRent", equalTo(5000f));
    }

    @Test
    public void getPropertyById_InvalidId_Returns404Error() {
        long invalidId = 123456789L;

        given()
                .when()
                .get("/property/" + invalidId)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
