package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class RestApiHelper {
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(System.getProperty("sut.url"))
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String fillPaymentFormWithDebitCardData(DataHelper.CardValidInformationModel cardValidInformationModel) {
        return given()
                .spec(requestSpec)
                .body(cardValidInformationModel)

                .when()
                .post("/api/v1/pay")

                .then()
                .statusCode(200)
                .extract().response().asString();
    }

    public static String fillPaymentFormWithCreditCardData(DataHelper.CardValidInformationModel cardValidInformationModel) {
        return given()
                .spec(requestSpec)
                .body(cardValidInformationModel)

                .when()
                .post("/api/v1/credit")

                .then()
                .statusCode(200)
                .extract().response().asString();
    }
}

