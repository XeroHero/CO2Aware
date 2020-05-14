package example.devtips.senddatatoactivity.models;

import java.io.Serializable;

public class BikeStation implements Serializable {

    private String number;

    private Position position;

    public BikeStation(String number, Position position) {
        this.number = number;
        this.position = position;
    }

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
