package curs.service.impl;

import curs.dao.TripsDao;
import curs.models.*;
import curs.service.TripsService;
import net.sourceforge.jtds.jdbc.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Service
@Transactional(readOnly = false)
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TripsServiceImpl implements TripsService {
    @Autowired
    private transient TripsDao dao;

    private void test(int i) {

    }

    private void test() {

    }

    @Override
    public List<City> getCities() {
        return dao.getCities();
    }

    @Override
    public List<Trip> getTrips(String start, String end, int from, int to, int count, boolean withHotel) throws ParseException, SQLException {
        List<Trip> trips = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = Date.valueOf(start);
        Date endDate = Date.valueOf(end);
        BaseTrip baseTrip = dao.getTrip(startDate, endDate, from, to, withHotel);
        for (Hotel hotel : baseTrip.getHotels()) {
            hotel.setEconom(hotel.getEconom() * count);
            hotel.setComfort(hotel.getComfort() * count);
            hotel.setLux(hotel.getLux() * count);
        }
        test(1);
        baseTrip.getPlanesIn().forEach(planeIn -> {
            baseTrip.getPlanesBack().forEach(planeBack -> {
                Trip trip = new Trip();
                trip.setIdIn(planeIn.getId());
                trip.setIdBack(planeBack.getId());
                trip.setHotelList(baseTrip.getHotels());
                trip.setWithHotel(baseTrip.isWithHotel());
                trip.setDateStartFrom(planeIn.getDateFrom());
                trip.setDateStartTo(planeIn.getDateTo());
                trip.setDateEndFrom(planeBack.getDateFrom());
                trip.setDateEndTo(planeBack.getDateTo());
                trip.setEconomPlaneIn(planeIn.getEconom() * count);
                trip.setBusinessPlaneIn(planeIn.getBusiness() * count);
                trip.setFirstClassPlaneIn(planeIn.getFirstClass() * count);
                trip.setEconomPlaneBack(planeBack.getEconom() * count);
                trip.setBusinessPlaneBack(planeBack.getBusiness() * count);
                trip.setFirstClassPlaneBack(planeBack.getFirstClass() * count);
                trips.add(trip);
            });
        });
        return trips;
    }

    @Override
    public void byTicket(Integer flightIdIn, Integer flightIdBack, Integer flightInSeat, Integer flightBackSeat, Integer hotelId, Integer hotelType, Integer count) {
        dao.byTicket(flightIdIn, flightIdBack, flightInSeat, flightBackSeat, hotelId, hotelType, count);
    }
}
