package tk.xerohero.co2aware;

class BikeStation {
    private int parkingAvailable;
    private int number;
    private String address;
    private int bikesAvailable;

    //Safer than using LatLng type
    private String latitude;
    private String longitude;
    private Boolean dropOffPossible;
    private Boolean pickupPossible;

    public Boolean isDropOffPossible() {
        return dropOffPossible;
    }

    public void setDropOffPossible(Boolean dropOffPossible) {
        this.dropOffPossible = dropOffPossible;
    }

    public Boolean isPickupPossible() {
        return pickupPossible;
    }

    public void setPickupPossible(Boolean pickupPossible) {
        this.pickupPossible = pickupPossible;
    }

    String getLatitude() {
        return latitude;
    }

    void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    String getLongitude() {
        return longitude;
    }

    void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    BikeStation(Integer id, String addressDisplay, String latitude, String longitude, Integer bikesAvailable, Integer parkingPlacesAvailable, Boolean dropOffPossible, Boolean pickupPossible) {
        this.number = id;
        this.address = addressDisplay;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bikesAvailable = bikesAvailable;
        this.parkingAvailable = parkingPlacesAvailable;
        this.dropOffPossible = dropOffPossible;
        this.pickupPossible = pickupPossible;
    }

    int getNumber() {
        return number;
    }

    void setNumber(int number) {
        this.number = number;
    }

    void setParkingAvailable(int parkingAvailable) {
        this.parkingAvailable = parkingAvailable;
    }

    int getParkingAvailable() {
        return parkingAvailable;
    }

    void setAddress(String address) {
        this.address = address;
    }

    String getAddress() {
        return address;
    }


    void setBikesAvailable(int bikesAvailable) {
        this.bikesAvailable = bikesAvailable;
    }



    int getBikesAvailable() {
        return bikesAvailable;
    }


    int getBikeStationNumber() {
        return number;
    }

    void setBikeStationNumber(int number) {
        this.number = number;
    }



}
