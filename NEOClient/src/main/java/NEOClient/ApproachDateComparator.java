package NEOClient;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

public class ApproachDateComparator implements Comparator<JSONObject> {

    public int compare(JSONObject o1, JSONObject o2) {
        JSONArray approachData1 = (JSONArray) o1.get("close_approach_data");
        JSONArray approachData2 = (JSONArray) o2.get("close_approach_data");

        ArrayList<JSONObject> approachDataList1 = new ArrayList<JSONObject>();
        ArrayList<JSONObject> approachDataList2 = new ArrayList<JSONObject>();

        for (int i = 0; i < approachData1.size(); i++) {
            approachDataList1.add((JSONObject) approachData1.get(i));
        }
        for (int i = 0; i < approachData2.size(); i++) {
            approachDataList2.add((JSONObject) approachData2.get(i));
        }
        // Since NASA also retruns past NEOs, we need to find the closest in the FUTURE..
        Long v1 = Long.MAX_VALUE, v2 = Long.MAX_VALUE;
        for (int i = 0; i < approachDataList1.size(); i++) {
            Long epochDate = (Long) ((JSONObject) approachData1.get(i)).get("epoch_date_close_approach");
            if (epochDate > 0) {
                v1 = epochDate;
                break;
            }
        }

        for (int i = 0; i < approachDataList2.size(); i++) {
            Long epochDate = (Long) ((JSONObject) approachData2.get(i)).get("epoch_date_close_approach");
            if (epochDate > 0) {
                v2 = epochDate;
                break;
            }
        }

        return v1.compareTo(v2);
    }

}
