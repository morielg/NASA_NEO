package NEOClient;

import org.json.simple.JSONObject;

import java.util.Comparator;

public class DiameterComparator implements Comparator<JSONObject>{

    public int compare(JSONObject o1, JSONObject o2) {
        JSONObject diameter1 = (JSONObject) ((JSONObject) o1.get("estimated_diameter")).get("kilometers");
        JSONObject diameter2 = (JSONObject) ((JSONObject) o2.get("estimated_diameter")).get("kilometers");
        Double v1 = (Double) (diameter1.get("estimated_diameter_max"));
        Double v2 = (Double) (diameter2.get("estimated_diameter_max"));
        return v1.compareTo(v2);
    }

}
