package curs.service;

import curs.models.City;
import curs.models.Trip;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface TripsService {
    List<City> getCities();
    List<Trip> getTrips(String start, String end, int from, int to, int count, boolean withHotel) throws ParseException, SQLException;
    void byTicket(Integer flightIdIn, Integer flightIdBack, Integer flightInSeat, Integer flightBackSeat, Integer hotelId, Integer hotelType, Integer count);
}
