package example.devtips.senddatatoactivity;

public class BikeStation {

    private String name;
    private int bikesAvailable;
    private int parkingAvailable;
    private int number;
    private String contractName;
    private String address;
    private double lat;
    private double lng;
    private boolean banking;
    private boolean bonus;
    private String status;
    private long lastUpdate;

    public BikeStation(String name, int bikesAvailable, int parkingAvailable, int number, String
            contractName, String address, int lat, int lng, boolean banking, boolean bonus, String status, long lastUpdate) {
        this.name = name;
        this.bikesAvailable = bikesAvailable;
        this.parkingAvailable = parkingAvailable;
        this.number = number;
        this.contractName = contractName;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.banking = banking;
        this.bonus = bonus;
        this.status = status;
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getBikesAvailable() {
        return bikesAvailable;
    }

    void setBikesAvailable(int bikesAvailable) {
        this.bikesAvailable = bikesAvailable;
    }

    int getParkingAvailable() {
        return parkingAvailable;
    }

    void setParkingAvailable(int parkingAvailable) {
        this.parkingAvailable = parkingAvailable;
    }

    public int getNumber() {
        return number;
    }

    void setNumber(int number) {
        this.number = number;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isBanking() {
        return banking;
    }

    void setBanking(boolean banking) {
        this.banking = banking;
    }

    public boolean isBonus() {
        return bonus;
    }

    void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    public String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    BikeStation() {

    }


}