package com.everthaine.naxlin.multithreading;

import com.google.gson.Gson;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        try {
            Main.call_me();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void call_me() throws Exception {
        String url = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=077ea2e253d383ac38376dc064945dce";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        String resp = String.valueOf(response);

        Gson gson = new Gson();
        Weather weather = gson.fromJson(resp, Weather.class);
        weather.display();
    }
}

//{
// "coord":
//  {
//      "lon":-0.13,
//      "lat":51.51
//  },
// "weather": [
//      {
//          "id":300,
//          "main":"Drizzle",
//          "description":"light intensity drizzle",
//          "icon":"09d"
//      }
//  ],
// "base":"stations",
// "main":
//  {
//      "temp":280.32,
//      "pressure":1012,
//      "humidity":81,
//      "temp_min":279.15,
//      "temp_max":281.15
//  },
// "visibility":10000,
// "wind":
//  {
//      "speed":4.1,
//      "deg":80
//  },
// "clouds":
//  {
//      "all":90
//  },
// "dt":1485789600,
// "sys":
//  {
//      "type":1,
//      "id":5091,
//      "message":0.0103,
//      "country":"GB",
//      "sunrise":1485762037,
//      "sunset":1485794875
//  },
// "id":2643743,
// "name":"London",
// "cod":200
// }