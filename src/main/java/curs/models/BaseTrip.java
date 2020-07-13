package curs.models;

import java.util.List;

public class BaseTrip {
    private List<Plane> planesIn;
    private List<Plane> planesBack;
    private List<Hotel> hotels;
    private boolean withHotel = false;

    public List<Plane> getPlanesIn() {
        return planesIn;
    }

    public void setPlanesIn(List<Plane> planesIn) {
        this.planesIn = planesIn;
    }

    public List<Plane> getPlanesBack() {
        return planesBack;
    }

    public void setPlanesBack(List<Plane> planesBack) {
        this.planesBack = planesBack;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public boolean isWithHotel() {
        return withHotel;
    }

    public void setWithHotel(boolean withHotel) {
        this.withHotel = withHotel;
    }
}
