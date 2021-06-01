package helpers;

import static io.restassured.RestAssured.given;

public class BrowserstackApi {
    public static String getVideoUrl(String sessionId) {
        return given()
                .auth().basic("maksimbessudnov_kNJCU4", "pypsptaszQipGBi7M3eR")
                .when()
                .get("https://api-cloud.browserstack.com" + "/app-automate/sessions/" + sessionId + ".json")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response()
                .path("automation_session.video_url");
    }
}
