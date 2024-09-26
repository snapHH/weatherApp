import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import org.json.JSONObject;

public class WeatherAPI {
    private static final String API_KEY = "Your Api Key"; // replace with your actual API key
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public static String getWeatherData(String city) {
        StringBuilder result = new StringBuilder();
        try {
            URI uri = new URI(BASE_URL + city + "&appid=" + API_KEY + "&units=metric");
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String parseWeatherData(String json) {
        JSONObject jsonObject = new JSONObject(json);
        String cityName = jsonObject.getString("name");
        JSONObject main = jsonObject.getJSONObject("main");
        double temperature = main.getDouble("temp");
        String weatherDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
        return "City: " + cityName + "\nTemperature: " + temperature + "°C\nDescription: " + weatherDescription;
    }
}
