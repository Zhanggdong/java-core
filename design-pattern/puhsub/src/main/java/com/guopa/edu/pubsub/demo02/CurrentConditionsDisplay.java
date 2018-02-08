package com.guopa.edu.pubsub.demo02;

/**
 * Created by lsl on 2017/6/30.
 */
//此布告板实现了Observer接口，所以可以从WeatherData对象中获得改
//它也实现了DisplayElement接口，因为我们的API规定所有的布告板都必须实现此接口。
public class CurrentConditionsDisplay implements Observer, DisplayElement{
    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    //当update()被调用时，我们把温度和湿度保存起来，然后调用display()。
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    //display()方法就只是把最近的温度和湿度显示出来。
    public void display() {
        System.out.println("Current conditions: " + temperature
                + "F degrees and " + humidity + "% humidity");
    }
}
