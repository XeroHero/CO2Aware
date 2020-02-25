package tk.xerohero.co2aware;

import com.google.android.gms.maps.model.LatLng;

class BikeStation {
    private int number;
    private String address;
    private LatLng location;
    private int bikesAvailable;

    public String getAddress() {
        return address;
    }

    public LatLng getLocation() {
        return location;
    }

    public int getBikesAvailable() {
        return bikesAvailable;
    }

    public int getParkingAvailable() {
        return parkingAvailable;
    }

    int getBikeStationNumber() {
        return number;
    }

    public void setBikeStationNumber(int number) {
        this.number = number;
    }

    private final int parkingAvailable;

    public BikeStation(Integer id, String addressDisplay, LatLng latLngLocation, int bikesAvailable, int parkingPlacesAvailable) {
        this.number = id;
        this.address = addressDisplay;
        this.location = latLngLocation;
        this.bikesAvailable = bikesAvailable;
        this.parkingAvailable = parkingPlacesAvailable;
    }


}