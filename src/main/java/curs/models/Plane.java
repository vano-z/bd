package curs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plane implements Serializable {
    private int id;
    private Date dateFrom;
    private Date dateTo;
    private String cityFrom;
    private String cityTo;
    private double econom;
    private double business;
    private double firstClass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public double getEconom() {
        return econom;
    }

    public void setEconom(double econom) {
        this.econom = econom;
    }

    public double getBusiness() {
        return business;
    }

    public void setBusiness(double business) {
        this.business = business;
    }

    public double getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(double firstClass) {
        this.firstClass = firstClass;
    }

    public void setTimeFrom(long time) {
        this.dateFrom.setTime(this.dateFrom.getTime() + time - (60000 * 60 * 3));
    }

    public void setTimeTo(long time) {
        this.dateTo.setTime(this.dateTo.getTime() + time - (60000 * 60 * 3));
    }
}
