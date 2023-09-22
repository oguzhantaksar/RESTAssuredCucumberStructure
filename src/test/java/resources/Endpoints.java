package resources;

public enum Endpoints {

    addPlaceEndpoint("/maps/api/place/add/json"),
    getPlaceEndpoint("/maps/api/place/get/json"),
    deletePlaceEndpoint("/maps/api/place/delete/json");

    private String endpoint;
    Endpoints(String endpoint) {

        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
