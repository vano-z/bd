import {Component, EventEmitter, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {TripsFiltersModel} from "./trips-filters.model";
import {City} from "../city.model";
import {AppService} from "../app.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'trips-filters',
  templateUrl: './trips-filters.component.html',
  styleUrls: ['./trips-filters.component.scss']
})
export class TripsFiltersComponent implements OnInit, OnChanges {
    @Output() applyFilters: EventEmitter<TripsFiltersModel> = new EventEmitter();
    selectedFilters: TripsFiltersModel = new TripsFiltersModel();
    cities: City[];
    constructor(public appService: AppService,
                public route: ActivatedRoute,
                public router: Router) { }

    ngOnInit() {
        this.appService.getCities().subscribe(cities => {
            this.cities = cities;
            this.route.queryParams.subscribe(map => {
                if (map['from']) {
                    this.selectedFilters.cityFrom = cities.filter(city => city.id == map['from'])[0];
                    this.selectedFilters.cityTo = cities.filter(city => city.id == map['to'])[0];
                    this.selectedFilters.dateStart = map['dateStart'];
                    this.selectedFilters.dateEnd = map['dateEnd'];
                    this.selectedFilters.passengersCount = map['count'];
                    this.applyFilters.emit(this.selectedFilters);
                }
            });
        });
    }

    ngOnChanges(changes: SimpleChanges): void {
        console.log(changes);
    }

    onSearchClick(): void {
        // this.setDefaultFilters();
        this.applyFilters.emit(this.selectedFilters);
    }

    setDefaultFilters(): void {
        this.selectedFilters.cityFrom = new City({id: 1, name: 'Москва', country: 'Россия'});
        this.selectedFilters.cityTo = new City({id: 2, name: 'Париж', country: 'Франция'});
        this.selectedFilters.dateStart = "2019-09-29";
        this.selectedFilters.dateEnd = "2019-09-30";
        this.selectedFilters.passengersCount = 1;
    }

    copyToClipboard(): void {
        let textToBeCopied = window.location.host + '/?';
        let params = this.selectedFilters.toParams();
        let map = new Map();
        Object.keys(params).forEach(function (key) {
            map.set(key, params[key]);
        });
        map.forEach((v, k) => {
            textToBeCopied = textToBeCopied + k + "=" + v + "&";
        });
        textToBeCopied = textToBeCopied.substr(0, textToBeCopied.length - 1);
        let textarea = null;
        textarea = document.createElement("textarea");
        textarea.style.height = "0px";
        textarea.style.left = "-100px";
        textarea.style.opacity = "0";
        textarea.style.position = "fixed";
        textarea.style.top = "-100px";
        textarea.style.width = "0px";
        document.body.appendChild(textarea);
        textarea.value = textToBeCopied;
        textarea.select();document.execCommand("copy");
        if (textarea && textarea.parentNode) {
            textarea.parentNode.removeChild(textarea);
        }
    }

    isCopyAvailable(): boolean {
        return this.selectedFilters.passengersCount != null &&
            this.selectedFilters.cityFrom != null &&
            this.selectedFilters.cityTo != null &&
            this.selectedFilters.dateStart != null &&
            this.selectedFilters.dateEnd != null;
    }
}
