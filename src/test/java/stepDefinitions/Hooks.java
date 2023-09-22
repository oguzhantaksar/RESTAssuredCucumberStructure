package stepDefinitions;

import io.cucumber.java.Before;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {



        StepDefinition stepDefinition = new StepDefinition();

        if (StepDefinition.place_id == null) {

            stepDefinition.add_place_payload_with("Oguzhan", "Turkish", "Edirne");
            stepDefinition.user_calls_with_http_request("addPlaceEndpoint", "POST");
            stepDefinition.verify_place_id_created_maps_to_using("Oguzhan", "getPlaceEndpoint");

        }
    }
}
