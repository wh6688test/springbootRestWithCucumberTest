
package org.tutorials.wproject2.test;

import io.cucumber.datatable.DataTable;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java8.En;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.spring.CucumberTestContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.tutorials.wproject2.test.model.Group;
import org.tutorials.wproject2.test.model.GroupIn;
import org.tutorials.wproject2.test.model.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.tutorials.wproject2.test.World;


public class StepDefinition implements En {

    @Autowired
    private World world;

    private static final String basePath="/wrest";
    private static final String APPLICATION_JSON="application/json";
    private final String applicationJson="application/json";

    private static final Logger LOG= LoggerFactory.getLogger(StepDefinition.class);

    @Before
    public void setup() throws Exception {
        RestAssured.baseURI="http://localhost";
        RestAssured.port = 8080;

    }

    @BeforeStep
    public void methodSetUp() throws Exception {
    }

    @AfterStep
    public void methodTeardown() throws Exception {
    }

    @After
    public void Teardown() throws Exception {
    }


    @Given("api request uri {string} and expected status code {int}")
    public void apiRequestUriResponseCode(String apiPath, Integer statusCode) throws Throwable {
       world.setStatusCode(statusCode);
        world.setApiPath(apiPath);

    }

    @When("I create the group using the following attributes successfully:")
    public void GivenAttributeThenCreateGroup(DataTable inputTable) throws Throwable {

        List<Map<String, String>> gAttrs=inputTable.asMaps();
        GroupIn groupIn=new GroupIn(gAttrs.get(0));

        world.responseGroup=given().basePath(basePath).contentType("application/json").body(groupIn)
                .when().post(world.getApiPath()).then().assertThat().statusCode(world.getStatusCode())
                .extract().as(Group.class);

        assertTrue(world.responseGroup.getGid()>=0);
        assertEquals(gAttrs.get(0), world.responseGroup.getAttributes());

    }

    @When("I retrieve all groups via api {string} with response status {int}")
    public void GivenThenRetrieveAllGroup(String uri, Integer statusCode) throws Exception {

        world.responseString=given().basePath(basePath).accept(ContentType.JSON)
                .when().get(uri).then().assertThat().statusCode(world.statusCode)
                .extract().asString();

    }


    @Then("I verify that the response contents {string} contain following values:")
    public void ThenVerifyResponseStringGroupList(String negative, io.cucumber.datatable.DataTable responseBody) throws Exception {

        List<Map<String, String>>expectedValues=responseBody.asMaps();

        Map<String, String>actualAttributes=world.responseGroup.getAttributes();

        if (negative.trim().contains("not")) {
            expectedValues.forEach(k->assertFalse(k.entrySet().contains(actualAttributes.entrySet())));
        } else {
            //expectedValues.forEach(k->assertFalse(k.keySet().contains(actualAttributes.keySet())));
            //expectedValues.forEach(k->assertFalse(k.values().contains(actualAttributes.values())));
            expectedValues.forEach(k->assertFalse(k.entrySet().contains(actualAttributes.entrySet())));
        }
    }

   @Then("I verify that the response group matches:")
    public void ThenVerifyResponseGroup(Group expectedGroup) throws Exception {

      assertTrue(world.responseGroup.equals(expectedGroup));
    }

    @When("I retrieve specific group via api {string} using {string} {long} with expected response status code {int}")
    public void whenGetSpecificGroupThenReturnTheGroup200(String uri, String param1, Long gid, Integer statusCode) throws Exception {

        world.responseGroup=given().basePath(basePath).accept(ContentType.JSON)
                .log().all().queryParam(param1, gid.toString())
                .when().get(uri).then().statusCode(statusCode)
                .extract().as(Group.class);
        world.responseString=world.responseGroup.toString();



    }

    @Given("the group with gid {long} exists or get created")
    public void GivenGroup(Long gid) throws Exception {
        try {
             given().basePath(basePath).accept(ContentType.JSON)
                    .log().all().queryParam("gid", gid.toString())
                    .when().get("/group").then().statusCode(HttpStatus.OK.value());
        } catch (Exception e) {
            Group newGroup=new Group(gid);
            world.responseGroup=given().basePath(basePath).contentType(applicationJson).body(newGroup)
                    .when().post("/group").then().assertThat().statusCode(HttpStatus.CREATED.value())
                    .extract().as(Group.class);
       }

    }

    @When("I update the group member:")
    public void whenUpdateGroupMemberThenReturnUpdatedGroup200(DataTable inputTable) throws Exception {

        List<Map<String, String>>inputMap=inputTable.asMaps();

        String gid1=inputMap.get(0).get("gid");
        String memberId=inputMap.get(0).get("memberId");
        String rating=inputMap.get(0).get("rating");

        Member member=new Member(memberId, Short.valueOf(rating));


        Map<String, Member> responseMembers=given().basePath(basePath).contentType(applicationJson)
                .log().all()
                .queryParam("gid", gid1)
                .body(member)
               // .when().put(world.apiPath,gid1).then().assertThat().statusCode(world.getStatusCode())
                 .when().put(world.apiPath).then().assertThat().statusCode(world.getStatusCode())
                .extract().path("members");
    }


    @When("I retrieve the group list with group member id {string} via api {string} with expected response status {int}")
    public void whenGetSpecificMemberThenReturnContainingGroups(String memberId, String uri, Integer statusCode) throws Exception {

       String responseGroup=given().basePath(basePath).contentType(applicationJson)
               .log().all()
                .when().get(uri+"/"+memberId).then().assertThat().statusCode(statusCode)
                .extract().asString();

    }


    @When("I delete the group with gid {long} via api {string} with response status {int}")
    public void whenDeleteGroupReturn204(Long gid, String uri, Integer statusCode) throws Exception {
       given().basePath(basePath).contentType(applicationJson)
                .log()
                .all()
                .when().delete(uri+"/"+gid).then().assertThat().statusCode(statusCode);

    }
}
