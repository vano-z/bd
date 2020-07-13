package curs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;
import curs.serializer.DateTimeSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Trip implements Serializable {
    private int idIn;
    private int idBack;
    @JsonSerialize(using = DateTimeSerializer.class)
    private Date dateStartFrom;
    @JsonSerialize(using = DateTimeSerializer.class)
    private Date dateEndFrom;
    @JsonSerialize(using = DateTimeSerializer.class)
    private Date dateStartTo;
    @JsonSerialize(using = DateTimeSerializer.class)
    private Date dateEndTo;
    private double economPlaneIn;
    private double businessPlaneIn;
    private double firstClassPlaneIn;
    private double economPlaneBack;
    private double businessPlaneBack;
    private double firstClassPlaneBack;
    private List<Hotel> hotels;
    private boolean withHotel;

    public int getIdIn() {
        return idIn;
    }

    public void setIdIn(int idIn) {
        this.idIn = idIn;
    }

    public int getIdBack() {
        return idBack;
    }

    public void setIdBack(int idBack) {
        this.idBack = idBack;
    }

    public Date getDateStartFrom() {
        return dateStartFrom;
    }

    public void setDateStartFrom(Date dateStartFrom) {
        this.dateStartFrom = dateStartFrom;
    }

    public Date getDateEndFrom() {
        return dateEndFrom;
    }

    public void setDateEndFrom(Date dateEndFrom) {
        this.dateEndFrom = dateEndFrom;
    }

    public Date getDateStartTo() {
        return dateStartTo;
    }

    public void setDateStartTo(Date dateStartTo) {
        this.dateStartTo = dateStartTo;
    }

    public Date getDateEndTo() {
        return dateEndTo;
    }

    public void setDateEndTo(Date dateEndTo) {
        this.dateEndTo = dateEndTo;
    }

    public double getEconomPlaneIn() {
        return economPlaneIn;
    }

    public void setEconomPlaneIn(double economPlaneIn) {
        this.economPlaneIn = economPlaneIn;
    }

    public double getBusinessPlaneIn() {
        return businessPlaneIn;
    }

    public void setBusinessPlaneIn(double businessPlaneIn) {
        this.businessPlaneIn = businessPlaneIn;
    }

    public double getFirstClassPlaneIn() {
        return firstClassPlaneIn;
    }

    public void setFirstClassPlaneIn(double firstClassPlaneIn) {
        this.firstClassPlaneIn = firstClassPlaneIn;
    }

    public double getEconomPlaneBack() {
        return economPlaneBack;
    }

    public void setEconomPlaneBack(double economPlaneBack) {
        this.economPlaneBack = economPlaneBack;
    }

    public double getBusinessPlaneBack() {
        return businessPlaneBack;
    }

    public void setBusinessPlaneBack(double businessPlaneBack) {
        this.businessPlaneBack = businessPlaneBack;
    }

    public double getFirstClassPlaneBack() {
        return firstClassPlaneBack;
    }

    public void setFirstClassPlaneBack(double firstClassPlaneBack) {
        this.firstClassPlaneBack = firstClassPlaneBack;
    }

    public List<Hotel> getHotelList() {
        return hotels;
    }

    public void setHotelList(List<Hotel> hotelList) {
        this.hotels = hotelList;
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
