package example.devtips.senddatatoactivity.models;

import java.io.Serializable;

public class BikeStation implements Serializable {

    private String number;

    private Position position;

    public int getBikes() {
        return bikes;
    }

    public void setBikes(int bikes) {
        this.bikes = bikes;
    }

    public int getParking() {
        return parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    public BikeStation(String number, Position position) {
        this.number = number;
        this.position = position;
    }

    private int bikes;
    private int parking;
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
