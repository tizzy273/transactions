package com.assignment.transactions.cucumber.glue;

import com.assignment.transactions.dto.ErrorDto;
import com.assignment.transactions.dto.Transaction;
import com.assignment.transactions.entity.TransactionEntity;
import com.assignment.transactions.enums.TransactionType;
import com.assignment.transactions.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Dato;
import io.cucumber.java.it.Quando;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionScenario {

    public static final String CREATE_TRANSACTION = "http://localhost:8082/transactions/transaction/create-transaction";
    public static final String TRANSACTION_HISTORY = "http://localhost:8082/transactions/transaction/history";

    private Response response;

    @Autowired
    private TransactionRepository transactionRepository;


    private Transaction transaction;

    private String entityToJson(Transaction transaction) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        return ow.writeValueAsString(transaction);
    }

    @Before
    public void cleanDb(){
        transactionRepository.deleteAll();
    }

    @Given("a Transaction with amount {float} and type {string}")
    public void aTransactionWithAmountAndTypeDEPOSIT(Float amount, String type) {
        transaction = new Transaction(1,type,amount);
    }

    @When("I call the Create Transaction endpoint")
    public void newTransaction() throws JsonProcessingException {
        response = given()
                .header("Content-Type", "application/json")
               .body(entityToJson(transaction))
                .post(CREATE_TRANSACTION);
    }

    @Then("the transaction should be created successfully")
    public void theTransactionShouldBeCreatedSuccessfully() {

        response.then()
                .statusCode(200);

        List<Transaction> transactions = response.body()
                .jsonPath()
                .getList(".", Transaction.class);

        assertEquals(transaction, transactions.getFirst());


    }


    @Then("I'll get HTTP Status Code {int} with message {string}")
    public void iLlGetHTTPStatusCode(int statusCode, String messaggio) {

        response.then().statusCode(statusCode);
        ErrorDto exposureError = response.body()
                .jsonPath()
                .getObject(".", ErrorDto.class);

        assertEquals(messaggio, exposureError.getMessage());
    }

    @Given("a Transaction with amount {float} and type {string} and no Account Id specified")
    public void aTransactionWithAmountAndTypeDEPOSITAndNoAccountIdSpecified(float amount, String type) {
        transaction = new Transaction();

        transaction.setType(type);
        transaction.setAmount(amount);
    }

    @Given("a Transaction with amount {int} and type {string} and no Amount specified")
    public void aTransactionWithAmountAndTypeDEPOSITAndNoAmountSpecified(int amount, String type) {
        transaction = new Transaction();
        transaction.setType(type);
        transaction.setAccountId(1);
    }


    @When("I call the Transaction History endpoint with AccountId param = {int}")
    public void iCallTheTransactionHistoryEndpoint(int accountId) {
        response = given()
                .header("Content-Type", "application/json")
                .get(TRANSACTION_HISTORY + "?account-id="+ accountId);
    }

    @Given("an instance of the DB where there are multiple Transactions")
    public void anInstanceOfTheDBWhereThereAreMultipleTransactions() {

        transactionRepository.save(new TransactionEntity(TransactionType.DEPOSIT.name(), 1, 10000f));
        transactionRepository.save(new TransactionEntity(TransactionType.DEPOSIT.name(), 2, 10000f));
        transactionRepository.save(new TransactionEntity(TransactionType.DEPOSIT.name(), 3, 10000f));
        transactionRepository.save(new TransactionEntity(TransactionType.DEPOSIT.name(), 4, 10000f));
        transactionRepository.save(new TransactionEntity(TransactionType.DEPOSIT.name(), 5, 10000f));
        transactionRepository.save(new TransactionEntity(TransactionType.DEPOSIT.name(), 1, 10000f));
        transactionRepository.save(new TransactionEntity(TransactionType.DEPOSIT.name(), 1, 10000f));

    }

    @Then("I'll get all the transactions with AccountID = {int}")
    public void iLlGetAllTheTransactionsWithAccountID(int accountId) {
        response.then()
                .statusCode(200);

        List<Transaction> transactions = response.body()
                .jsonPath()
                .getList(".", Transaction.class);

       List<Integer> accountIdList =  transactions.stream().map(Transaction::getAccountId).toList();

       accountIdList.forEach(a -> assertEquals(accountId, a));
    }
}
