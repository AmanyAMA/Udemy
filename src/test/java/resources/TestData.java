package resources;

import pojo.GooglePlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    //create Separated class with function to pass the API body  through
    public GooglePlace  addplacedata(String lang ,String name , Double lat) {
        GooglePlace GP =new GooglePlace();
        Location location=new Location();
        GP.setAccuracy(50);
        location.setLat(lat);
        location.setLng(33.427362);
        List<String> typeList = new ArrayList<>();
        typeList.add( "shoe park");
        typeList.add("shop");
        GP.setTypes(typeList);
        GP.setAddress("29, side layout, cohen 09");
        GP.setLanguage(lang);
        GP.setName(name);
        GP.setPhone_number("(+91) 983 893 3937");
        GP.setWebsite("http://google.com");
        GP.setLocation(location);
        return GP;
    }

    public String delete_body(String place_id) {
        String body= "{\n" +
                "\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}\n";
        return body;
    }
}
