package curs.dao;

import curs.models.BaseTrip;
import curs.models.City;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

public interface TripsDao {
    List<City> getCities();

    BaseTrip getTrip(Date start, Date end, int from, int to, boolean withHotel);

    void byTicket(Integer flightIdIn, Integer flightIdBack, Integer flightInSeat, Integer flightBackSeat, Integer hotelId, Integer hotelType, Integer count);
}
