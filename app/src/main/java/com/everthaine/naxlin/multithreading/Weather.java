package com.everthaine.naxlin.multithreading;

public class Weather {
    public Coord coord;
    public Weath weather[];

    public String base;
    public float visibility;

    public Mein main;
    public Wind wind;

    public String rain;

    public Clouds clouds;

    public float dt;

    public Sys sys;

    public float id;
    public String name;
    public float cod;


    public void display() {
        System.out.println("\nNow Displaying the current weather in London:");

        System.out.println("Coord: " + coord.longitude + " : " + coord.latitude);
        for (Weath w : weather) {
            System.out.println("Weath: " + w.id + " : " + w.main + " : " + w.desc + " : " + w.icon);
        }
        System.out.println("Base: " + base);
        System.out.println("Visibility: " + visibility);
        System.out.println("Mein: " + main.temp + " : " + main.humidity + " : " + main.pressure + " : " + main.temp_min + " : " + main.temp_max);
        System.out.println("Wind: " + wind.speed + " : " + wind.deg);

        System.out.println("Rain: " + rain);

        System.out.println("Clouds: " + clouds.all);

        System.out.println("DT: " + dt);

        System.out.println("Sys: " + sys.type + " : " + sys.id + " : " + sys.message + " : " + sys.country + " : " + sys.sunrise + " : " + sys.sunset);

        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("COD: " + cod);
    }
}

//// {"coord":{"lon":139,"lat":35},
////        "sys":{"country":"JP","sunrise":1369769524,"sunset":1369821049},
////        "weather":[{"id":804,"main":"clouds","description":"overcast clouds","icon":"04n"}],
////        "main":{"temp":289.5,"humidity":89,"pressure":1013,"temp_min":287.04,"temp_max":292.04},
////        "wind":{"speed":7.31,"deg":187.002},
////        "rain":{"3h":0},
////        "clouds":{"all":92},
////        "dt":1369824698,
////        "id":1851632,
////        "name":"Shuzenji",
////        "cod":200}