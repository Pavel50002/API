package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API {
    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when()
                        .get(endpoint)
                        .then()
                        .contentType(ContentType.JSON)
                        .log()
                        .all()
                        .extract()
                        .response();
    }

    @org.testng.annotations.Test
    public void main() {
        Response response = doGetRequest("https://jsonplaceholder.typicode.com/users");

        List<String> jsonResponse = response.jsonPath().getList("$"); // "$" это обозначение каоторое означает корневой элемент
        System.out.println(jsonResponse.size());// здесь мы получаем количество пользователей в массиве


        String usernames = response.jsonPath()
                .getString("username");
        System.out.println(usernames); // здесь мы получаем их имина в массиве

        System.out.println();

        String usernames0 = response.jsonPath()
                .getString("username[0]");
        System.out.println(usernames0);// выведет только первое имя из массива

    }
    @org.testng.annotations.Test
    public void test() {

        Response response = doGetRequest("https://jsonplaceholder.typicode.com/users/1");
        Map<String, String> company = response.jsonPath().getMap("company"); //Здесь мы в стринговое значение company прсваиваем все что в него входит
        System.out.println(company.get("name"));


    }
}