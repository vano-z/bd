import {Component, Input, OnInit} from '@angular/core';
import {Trip} from "../../trip.model";
import {AppService} from "../../app.service";
import {TripsFiltersModel} from "../../trips-filters/trips-filters.model";
import {Form, FormControl, FormGroup} from "@angular/forms";
import {SelectedTrip} from "../selected-trip.model";
import {compareNumbers} from "@angular/compiler-cli/src/diagnostics/typescript_version";

@Component({
    selector: 'trip-details-with-hotels',
    templateUrl: './trip-details-with-hotels.component.html',
    styleUrls: ['./trip-details-with-hotels.component.scss']
})
export class TripDetailsWithHotelsComponent implements OnInit {

    @Input() trip: Trip;
    @Input() filters: TripsFiltersModel;

    formIn: FormGroup;
    formBack: FormGroup;
    formHotel: FormGroup;
    selectedTrip: SelectedTrip = new SelectedTrip();

    bought: boolean = false;

    cost: number = 0;

    constructor(private service: AppService) { }

    ngOnInit() {
        this.formIn = new FormGroup({seatIn: new FormControl('0')});
        this.formBack = new FormGroup({seatBack: new FormControl('0')});
        this.formHotel = new FormGroup({type: new FormControl('0')});
        this.formIn.get("seatIn").valueChanges.subscribe( value => {
            this.selectedTrip.flightInSeat = +value;
            this.checkCost()
        });
        this.formBack.get("seatBack").valueChanges.subscribe( value => {
            this.selectedTrip.flightBackSeat = +value;
            this.checkCost()
        });
        this.formHotel.get("type").valueChanges.subscribe( value => {
            this.selectedTrip.hotelType = +value;
            this.checkCost()
        });
        this.checkCost();
    }


    checkCost(): void {
        this.cost = 0;
        let planeIn;
        let planeBack;
        let hotel;
        switch (this.formIn.controls.seatIn.value) {
            case "0": {
                planeIn = this.trip.economPlaneIn;
                this.selectedTrip.flightInSeat = 0;
                break;
            }
            case "1": {
                planeIn = this.trip.businessPlaneIn;
                this.selectedTrip.flightInSeat = 1;
                break;
            }
            case "2": {
                planeIn = this.trip.firstClassPlaneIn;
                this.selectedTrip.flightInSeat = 2;
                break;
            }
        }

        switch (this.formBack.controls.seatBack.value) {
            case "0": {
                planeBack = this.trip.economPlaneBack;
                this.selectedTrip.flightBackSeat = 0;
                break;
            }
            case "1": {
                planeBack = this.trip.businessPlaneBack;
                this.selectedTrip.flightBackSeat = 1;
                break;
            }
            case "2": {
                planeBack = this.trip.firstClassPlaneBack;
                this.selectedTrip.flightBackSeat = 2;
                break;
            }
        }

        if (this.selectedTrip.hotel) {
            switch (this.formHotel.controls.type.value) {
                case "0": {
                    hotel = this.selectedTrip.hotel.econom;
                    this.selectedTrip.hotelType = 0;
                    break;
                }
                case "1": {
                    hotel = this.selectedTrip.hotel.comfort;
                    this.selectedTrip.hotelType = 1;
                    break;
                }
                case "2": {
                    hotel = this.selectedTrip.hotel.lux;
                    this.selectedTrip.hotelType = 2;
                    break;
                }
            }
            this.cost += hotel;
        }

        this.cost += planeIn;
        this.cost += planeBack;
        this.bought = false;
    }

    onByTicket(): void {
        this.bought = true;
        this.selectedTrip.count = this.filters.passengersCount;
        this.selectedTrip.flightIdIn = this.trip.idIn;
        this.selectedTrip.flightIdBack = this.trip.idBack;
        this.service.byTrip(this.selectedTrip).subscribe();
    }

}
