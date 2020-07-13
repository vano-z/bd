package curs.controller;

import curs.models.City;
import curs.models.Trip;
import curs.service.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;


@Controller
@RequestMapping(value = "/api")
public class TripsController {

    @Autowired
    TripsService tripsService;

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    @ResponseBody
    public List<City> getCities() {
        return tripsService.getCities();
    }

    @RequestMapping(value = "/trips-list", method = RequestMethod.GET)
    @ResponseBody
    public List<Trip> getTrips(@RequestParam(value = "dateStart") String dateStart,
                               @RequestParam(value = "dateEnd") String dateEnd,
                               @RequestParam(value = "from") Integer from,
                               @RequestParam(value = "to") Integer to,
                               @RequestParam(value = "count") Integer count,
                               @RequestParam(value = "withHotels", required = false, defaultValue = "false") boolean withHotels) throws ParseException, SQLException {
        List<Trip> trips = tripsService.getTrips(dateStart, dateEnd, from, to, count, withHotels);
        return trips;
    }

    @RequestMapping(value = "/by-trip", method = RequestMethod.POST)
    @ResponseBody
    public void byTrip(@RequestParam(value = "flightIdIn") Integer flightIdIn,
                       @RequestParam(value = "flightIdBack") Integer flightIdBack,
                       @RequestParam(value = "flightInSeat") Integer flightInSeat,
                       @RequestParam(value = "flightBackSeat") Integer flightBackSeat,
                       @RequestParam(value = "hotelId", required = false) Integer hotelId,
                       @RequestParam(value = "hotelType", required = false) Integer hotelType,
                       @RequestParam(value = "count") Integer count) {
        tripsService.byTicket(flightIdIn, flightIdBack, flightInSeat, flightBackSeat, hotelId, hotelType, count);
    }











}