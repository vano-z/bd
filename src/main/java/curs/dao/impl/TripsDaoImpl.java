package curs.dao.impl;

import curs.dao.TripsDao;
import curs.models.BaseTrip;
import curs.models.City;
import curs.models.Hotel;
import curs.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TripsDaoImpl implements TripsDao, Serializable {

    @Autowired
    JdbcTemplate jdbcTemplate;

    final static String RESULT_SET = "#result-set-1";

    @Override
    public List<City> getCities() {
        GetCitiesProcedure procedure = new GetCitiesProcedure(jdbcTemplate.getDataSource());
        List<City> cities = (List<City>) procedure.execute().get(RESULT_SET);
        return cities;
    }

    @Override
    public BaseTrip getTrip(Date start, Date end, int from, int to, boolean withHotel) {
        BaseTrip trip = new BaseTrip();
        GetPlanesProcedure planesProcedure = new GetPlanesProcedure(jdbcTemplate.getDataSource());
        GetHotelsProcedure hotelsProcedure = new GetHotelsProcedure(jdbcTemplate.getDataSource());
        trip.setPlanesIn((List<Plane>) planesProcedure.execute(start, from, to).get(RESULT_SET));
        trip.setPlanesBack((List<Plane>) planesProcedure.execute(end, to, from).get(RESULT_SET));
        if (withHotel) {
            trip.setHotels((List<Hotel>) hotelsProcedure.execute(to).get(RESULT_SET));
            trip.setWithHotel(true);
        }
        return trip;
    }

    @Override
    public void byTicket(Integer flightIdIn, Integer flightIdBack, Integer flightInSeat, Integer flightBackSeat, Integer hotelId, Integer hotelType, Integer count) {
        ByTripProcedure procedure = new ByTripProcedure(jdbcTemplate.getDataSource());
        procedure.execute(flightIdIn, flightIdBack, flightInSeat, flightBackSeat, count);
    }

    private class ByTripProcedure extends StoredProcedure {
        private static final String PROC_NAME = "by_trip";
        private static final String FLIGHT_ID_IN = "p_flightIdIn";
        private static final String FLIGHT_ID_BACK = "p_flightIdBack";
        private static final String FLIGHT_IN_SEAT = "p_flightInSeat";
        private static final String FLIGHT_BACK_SEAT = "p_flightBackSeat";
        private static final String COUNT = "p_count";

        public ByTripProcedure(DataSource dataSource) {
            super(dataSource, PROC_NAME);
            declareParameter(new SqlParameter(FLIGHT_ID_IN, Types.NUMERIC));
            declareParameter(new SqlParameter(FLIGHT_ID_BACK, Types.NUMERIC));
            declareParameter(new SqlParameter(FLIGHT_IN_SEAT, Types.NUMERIC));
            declareParameter(new SqlParameter(FLIGHT_BACK_SEAT, Types.NUMERIC));
            declareParameter(new SqlParameter(COUNT, Types.NUMERIC));
            compile();
        }

        public Map execute(int flightIdIn, int flightIdBack, int flightInSeat, int flightBackSeat, int count) {
            Map<String, Object> inputs = new HashMap<>();
            inputs.put(FLIGHT_ID_IN, flightIdIn);
            inputs.put(FLIGHT_ID_BACK, flightIdBack);
            inputs.put(FLIGHT_IN_SEAT, flightInSeat);
            inputs.put(FLIGHT_BACK_SEAT, flightBackSeat);
            inputs.put(COUNT, count);
            return super.execute(inputs);
        }
    }

    private class GetHotelsProcedure extends StoredProcedure {
        private static final String PROC_NAME = "get_hotels";
        private static final String CITY = "p_city";

        public GetHotelsProcedure(DataSource dataSource) {
            super(dataSource, PROC_NAME);
            declareParameter(new SqlParameter(CITY, Types.NUMERIC));
            declareParameter(new SqlReturnResultSet(RESULT_SET, new HotelsRowMapper()));
            compile();
        }

        public Map execute(int city) {
            Map<String, Object> inputs = new HashMap<>();
            inputs.put(CITY, city);
            return super.execute(inputs);
        }

    }

    private class HotelsRowMapper implements RowMapper<Hotel> {

        @Override
        public Hotel mapRow(ResultSet resultSet, int i) throws SQLException {
            Hotel hotel = new Hotel();
            hotel.setId(resultSet.getInt("id"));
            hotel.setName(resultSet.getString("name"));
            hotel.setAddress(resultSet.getString("address"));
            hotel.setEconom(resultSet.getDouble("econom"));
            hotel.setComfort(resultSet.getDouble("comfort"));
            hotel.setLux(resultSet.getDouble("lux"));
            return hotel;
        }
    }

    private class GetPlanesProcedure extends StoredProcedure {
        private static final String PROC_NAME = "get_planes";
        private static final String DATE = "p_date";
        private static final String FROM = "p_from";
        private static final String TO = "p_to";


        public GetPlanesProcedure(DataSource ds) {
            super(ds, PROC_NAME);
            declareParameter(new SqlParameter(DATE, Types.JAVA_OBJECT));
            declareParameter(new SqlParameter(FROM, Types.NUMERIC));
            declareParameter(new SqlParameter(TO, Types.NUMERIC));
            declareParameter(new SqlReturnResultSet(RESULT_SET, new PlanesRowMapper()));
            compile();
        }

        public Map execute(Date date, int from, int to) {
            Map<String, Object> inputs = new HashMap<>();
            inputs.put(DATE, date);
            inputs.put(FROM, from);
            inputs.put(TO, to);
            return super.execute(inputs);
        }
    }

    private class PlanesRowMapper implements RowMapper<Plane> {

        @Override
        public Plane mapRow(ResultSet resultSet, int i) throws SQLException {
            Plane plane = new Plane();
            plane.setId(resultSet.getInt("id"));
            plane.setDateFrom(resultSet.getTimestamp("dateFrom"));
            plane.setTimeFrom(resultSet.getTime("timeFrom").getTime());
            plane.setDateTo(resultSet.getTimestamp("dateTo"));
            plane.setTimeTo(resultSet.getTime("timeTo").getTime());
            plane.setEconom(resultSet.getDouble("econom"));
            plane.setBusiness(resultSet.getDouble("business"));
            plane.setFirstClass(resultSet.getDouble("firstClass"));
            return plane;
        }
    }

    private class GetCitiesProcedure extends StoredProcedure {
        private static final String PROC_NAME = "get_cities";

        public GetCitiesProcedure(DataSource dataSource) {
            super(dataSource, PROC_NAME);
            declareParameter(new SqlReturnResultSet(RESULT_SET, new CityRowMapper()));
            compile();
        }

        public Map execute() {
            return super.execute();
        }
    }

    private class CityRowMapper implements RowMapper<City> {

        @Override
        public City mapRow(ResultSet resultSet, int i) throws SQLException {
            City city = new City();
            city.setId(resultSet.getInt("id"));
            city.setName(resultSet.getString("name"));
            city.setCountry(resultSet.getString("country"));
            return city;
        }
    }
}
