package javaFxTable;

import java.io.Serial;
import java.io.Serializable;

public class Passenger implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    private String firstName;
    private String lastName;
    private String vehicleNo;
    private int liters;

    public Passenger(String firstName, String lastName, String vehicleNo, int liters) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.vehicleNo = vehicleNo;
        this.liters = liters;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public int getLiters() {
        return liters;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setLiters(int liters) {
        this.liters = liters;
    }
}
