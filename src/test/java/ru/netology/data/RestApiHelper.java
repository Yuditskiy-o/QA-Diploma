package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class RestApiHelper {
    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void fillPaymentFormWithDebitCardData(DataHelper.CardInformationForApi cardInformationForApi) {
        given()
                .spec(requestSpec)
                .body(cardInformationForApi)

                .when()
                    .post("/api/v1/pay")

                .then().log().all()
                    .statusCode(200)
                    .extract().response()
                    .asString();
    }

    public static void fillPaymentFormWithCreditCardData(DataHelper.CardInformationForApi cardInformationForApi) {
        given()
                    .spec(requestSpec)
                    .body(cardInformationForApi)

                .when()
                    .post("/api/v1/credit")

                .then().log().all()
                    .statusCode(200)
                    .extract().response()
                    .asString();
    }
}

