package helper;

import org.json.JSONArray;

public class FormatarJSONString {

    public static String arrayToJson(JSONArray jsonArray) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < jsonArray.length(); i++) {
            stringBuilder.append(jsonArray.getString(i));
            if (i < jsonArray.length() - 1) {
                stringBuilder.append(", ");
            }
        }

        return stringBuilder.toString();
    }

    public static JSONArray jsonToArray(String input) {
        JSONArray jsonArray = new JSONArray();

        String[] items = input.split(", ");

        for (String item : items) {
            jsonArray.put(item);
        }

        return jsonArray;
    }
}