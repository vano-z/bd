import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';
import * as cloneDeep from 'lodash/cloneDeep';
import {TripsFiltersModel} from "./trips-filters/trips-filters.model";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    filters: TripsFiltersModel;

    constructor(private appService: AppService) {}

    ngOnInit() {

    }

    searchTrips(filters: TripsFiltersModel): void {
        this.filters = cloneDeep(filters);
        this.appService.getTrips(filters).subscribe();
    }

    onGetCitiesClick() {
        this.appService.getCities().subscribe();
    }

}
