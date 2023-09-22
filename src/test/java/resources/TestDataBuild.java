package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.Arrays;
import java.util.List;

public class TestDataBuild {


    public AddPlace add_Place_Payload(String name, String language, String address) {

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress(address);
        List<String> typesList = Arrays.asList("shoe park","shoe");
        addPlace.setTypes(typesList);
        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage(language);
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);

        return addPlace;
    }


    public String delete_Place_Payload(String place_id){

        return ("{\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}");
    }
}
