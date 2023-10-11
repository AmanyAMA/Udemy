package resources;

public enum APIResources {

    AddPlaceApi("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");
    String resource;

    APIResources(String resource) {
    this.resource=resource;
    }

    public String getResource(){
        return resource;
    }


}
