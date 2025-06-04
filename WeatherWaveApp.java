import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherWaveApp {
    private static final String[][] TARGETS = {
            { "函館", "017000" },
            { "青森", "020000" },
            { "東京", "130000" },
            { "長野", "200000" },
            { "大阪", "270000" },
            { "愛媛", "380000" },
            { "鹿児島", "460100" },
            { "沖縄", "471000" }
    };

    public static void main(String[] args) {
        String today = java.time.LocalDate.now().toString();
        for (String[] target : TARGETS) {
            String areaName = target[0];
            String areaCode = target[1];
            String urlStr = "https://www.jma.go.jp/bosai/forecast/data/forecast/" + areaCode + ".json";
            try {
                URL url = new URI(urlStr).toURL();
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    StringBuilder responseBody = new StringBuilder();
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            responseBody.append(line);
                        }
                    }
                    JSONArray rootArray = new JSONArray(responseBody.toString());
                    JSONObject timeSeriesObject = rootArray.getJSONObject(0).getJSONArray("timeSeries")
                            .getJSONObject(0);
                    JSONArray timeDefines = timeSeriesObject.getJSONArray("timeDefines");
                    JSONArray weathers = timeSeriesObject.getJSONArray("areas").getJSONObject(0)
                            .getJSONArray("weathers");
                    JSONArray winds = timeSeriesObject.getJSONArray("areas").getJSONObject(0).getJSONArray("winds");
                    JSONArray waves = timeSeriesObject.getJSONArray("areas").getJSONObject(0).getJSONArray("waves");
                    boolean found = false;
                    for (int i = 0; i < timeDefines.length(); i++) {
                        if (timeDefines.getString(i).startsWith(today)) {
                            String weather = toHalfWidth(weathers.getString(i));
                            String wind = toHalfWidth(winds.getString(i));
                            String wave = toHalfWidth(waves.getString(i));
                            System.out.println(
                                    "【" + areaName + "】" + weather + "。風向きは" + wind + "。波の高さは" + wave + "。");
                            found = true;
                            break;
                        }
                    }
                    if (!found)
                        System.out.println("本日の天気情報はありません");
                } else {
                    System.out.println("取得失敗!");
                }
            } catch (IOException | java.net.URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 全角英数字・記号を半角に変換する。
     * 
     * @param s 変換対象の文字列
     * @return 半角に変換された文字列
     */
    private static String toHalfWidth(String s) {
        if (s == null)
            return null;
        // 全角英数字・記号・スペースを半角に
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            // 全角スペース
            if (c == '\u3000') {
                sb.append(' ');
            }
            // 全角英数字・記号
            else if (c >= '\uFF01' && c <= '\uFF5E') {
                sb.append((char) (c - 0xFEE0));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}