package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.example.utils.ConfigReader;
import com.google.gson.JsonObject;

public class TransactionApiClient {
    private String baseUrl;
    private RequestSpecification requestSpec;

    public TransactionApiClient() {
        this.baseUrl = ConfigReader.getAppUrl();
        initializeRequestSpec();
    }

    private void initializeRequestSpec() {
        requestSpec = RestAssured.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .accept("application/json");
    }

    public Response createTransaction(String description, Double amount, String date, String month) {
        JsonObject body = new JsonObject();
        body.addProperty("description", description);
        body.addProperty("amount", amount);
        body.addProperty("date", date);
        body.addProperty("month", month);

        Response response = requestSpec
                .body(body.toString())
                .post("/api/transactions");

        System.out.println("Create Transaction API Response: " + response.getStatusCode());
        return response;
    }

    public Response getAllTransactions() {
        Response response = requestSpec
                .get("/api/transactions");

        System.out.println("Get All Transactions API Response: " + response.getStatusCode());
        return response;
    }

    public Response getTransactionsByMonth(String month) {
        Response response = requestSpec
                .get("/api/transactions/month/" + month);

        System.out.println("Get Transactions by Month API Response: " + response.getStatusCode());
        return response;
    }

    public int getTransactionCount() {
        Response response = getAllTransactions();
        if (response.getStatusCode() == 200) {
            return response.jsonPath().getList("$").size();
        }
        return 0;
    }

    public Object getTotalAmountForMonth(String month) {
        Response response = getTransactionsByMonth(month);
        if (response.getStatusCode() == 200) {
            double total = 0;
            try {
                total = response.jsonPath().getList("amount", Double.class).stream()
                        .mapToDouble(Double::doubleValue)
                        .sum();
            } catch (Exception e) {
                System.err.println("Error calculating total: " + e.getMessage());
            }
            return total;
        }
        return 0;
    }

    public boolean verifyTransactionExists(String description, String month) {
        Response response = getTransactionsByMonth(month);
        if (response.getStatusCode() == 200) {
            try {
                return response.jsonPath().getList("description", String.class).contains(description);
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
