package example.devtips.senddatatoactivity.models;

import java.io.Serializable;

public class BikeStation implements Serializable {

    private String number;
    private String contract_name;
    private String name;
    private String address;

    private Position position;

    private String banking;
    private String bonus;
    private String bike_stands;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContract_name() {
        return contract_name;
    }

    public void setContract_name(String contract_name) {
        this.contract_name = contract_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getBanking() {
        return banking;
    }

    public void setBanking(String banking) {
        this.banking = banking;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getBike_stands() {
        return bike_stands;
    }

    public void setBike_stands(String bike_stands) {
        this.bike_stands = bike_stands;
    }

    public String getAvailable_bike_stands() {
        return available_bike_stands;
    }

    public void setAvailable_bike_stands(String available_bike_stands) {
        this.available_bike_stands = available_bike_stands;
    }

    public String getAvailable_bikes() {
        return available_bikes;
    }

    public void setAvailable_bikes(String available_bikes) {
        this.available_bikes = available_bikes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    private String available_bike_stands;
    private String available_bikes;
    private String status;
    private String last_update;
}
