package com.petstore.users.stepdefinitions;

import com.petstore.users.questions.LastResponseStatusCode;
import com.petstore.users.questions.ResponseBodyField;
import com.petstore.users.tasks.CreateUser;
import com.petstore.users.tasks.DeleteUserByUsername;
import com.petstore.users.tasks.GetUserByUsername;
import com.petstore.users.tasks.UpdateUser;
import com.petstore.users.utils.ApiConstants;
import com.petstore.users.utils.UserPayloadFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class UserCrudStepDefinitions {

    @Given("el consumidor consulta el servicio de usuarios de Petstore")
    public void elConsumidorConsultaElServicioDeUsuariosDePetstore() {
        OnStage.theActorCalled(ApiConstants.ACTOR_NAME).whoCan(CallAnApi.at(ApiConstants.BASE_URL));
    }

    @When("registra un usuario con username {string}, nombre {string}, apellido {string}, correo {string}, password {string}, telefono {string} y estado {int}")
    public void registraUnUsuarioConLosDatos(
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            Integer userStatus
    ) {
        theActorInTheSpotlight().attemptsTo(
                CreateUser.withData(
                        UserPayloadFactory.forCreate(username, firstName, lastName, email, password, phone, userStatus)
                )
        );
    }

    @When("consulta el usuario {string}")
    public void consultaElUsuario(String username) {
        theActorInTheSpotlight().attemptsTo(
                GetUserByUsername.called(username)
        );
    }

    @When("actualiza el usuario {string} con nombre {string}, apellido {string}, correo {string}, password {string}, telefono {string} y estado {int}")
    public void actualizaElUsuarioConLosDatos(
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String phone,
            Integer userStatus
    ) {
        theActorInTheSpotlight().attemptsTo(
                GetUserByUsername.called(username)
        );

        long userId = SerenityRest.lastResponse().jsonPath().getLong("id");

        theActorInTheSpotlight().attemptsTo(
                UpdateUser.called(
                        username,
                        UserPayloadFactory.forUpdate(userId, username, firstName, lastName, email, password, phone, userStatus)
                )
        );
    }

    @When("elimina el usuario {string}")
    public void eliminaElUsuario(String username) {
        theActorInTheSpotlight().attemptsTo(
                DeleteUserByUsername.called(username)
        );
    }

    @Then("el servicio responde con estado {int}")
    public void elServicioRespondeConEstado(Integer expectedStatus) {
        theActorInTheSpotlight().should(
                seeThat(LastResponseStatusCode.value(), equalTo(expectedStatus))
        );
    }

    @Then("la respuesta contiene el username {string}")
    public void laRespuestaContieneElUsername(String expectedUsername) {
        theActorInTheSpotlight().should(
                seeThat(ResponseBodyField.called("username"), equalTo(expectedUsername))
        );
    }

    @Then("la respuesta contiene el nombre {string} y el correo {string}")
    public void laRespuestaContieneElNombreYElCorreo(String expectedFirstName, String expectedEmail) {
        theActorInTheSpotlight().should(
                seeThat(ResponseBodyField.called("firstName"), equalTo(expectedFirstName)),
                seeThat(ResponseBodyField.called("email"), equalTo(expectedEmail))
        );
    }

        @Then("la respuesta contiene el mensaje {string}")
        public void laRespuestaContieneElMensaje(String expectedMessage) {
                theActorInTheSpotlight().should(
                                seeThat(ResponseBodyField.called("message"), equalTo(expectedMessage))
                );
        }
}
