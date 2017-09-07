package NEOClient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.Collections;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NeoClientGet {
    public static final int RESPONSE_OK = 200;

    public static void main(String[] args) {
        try {
            Client client = Client.create();

            WebResource webResource = client.resource("https://api.nasa.gov/neo/rest/v1/neo/browse?&api_key=DEMO_KEY");

            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

            if (response.getStatus() != RESPONSE_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            JSONParser parser = new JSONParser();
            try {
                //System.out.println(response.getEntity(String.class));

                JSONObject o = (JSONObject) parser.parse(response.getEntity(String.class));
                System.out.println("Total number of NEOs: " + ((JSONObject) o.get("page")).get("total_elements"));

                JSONArray array = (JSONArray) o.get("near_earth_objects");
                ArrayList<JSONObject> list = new ArrayList<JSONObject>();

                for (int i = 0; i < array.size(); i++) {
                    list.add((JSONObject) array.get(i));
                }
                Collections.sort(list, new ApproachDateComparator());
                System.out.println("NEO closest to earth: " + (list.get(0).get("name")));

                Collections.sort(list, new DiameterComparator());
                System.out.println("NEO largest diameter: " + (list.get(list.size()-1).get("name")));

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}