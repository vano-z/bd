import {Injectable} from '@angular/core';
import { ApiService } from './api.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {TripsFiltersModel} from "./trips-filters/trips-filters.model";
import {HttpParams} from "@angular/common/http";
import {map, tap} from "rxjs/operators";
import {Trip} from "./trip.model";
import {City} from "./city.model";
import {SelectedTrip} from "./trips-list/selected-trip.model";

@Injectable({
  providedIn: 'root'
})

export class AppService {

    public tripsList$: Observable<Trip[]>;
    private tripsListSubject$: BehaviorSubject<Trip[]> = new BehaviorSubject(null);

    constructor(private apiService: ApiService) {
        this.tripsList$ = this.tripsListSubject$.asObservable();
    }

    public getTrips(filters: TripsFiltersModel): Observable<void> {
        let params = filters.toParams();
        let httpParams = new HttpParams();
        Object.keys(params).forEach(function (key) {
            httpParams = httpParams.append(key, (params[key] != null && params[key] != undefined) ? params[key] : ' ');
        });

        return this.apiService.get(`/trips-list`, {
            params: httpParams
        }).pipe(tap((response: any) => {
            this.tripsListSubject$.next(response.map(trip => new Trip(trip)))
        }))
    }

    public getCities(): Observable<City[]> {
        return this.apiService.get(`/cities`).pipe(
            map(response => response.map(city => {
            return new City(city);
        })));
    }

    public byTrip(selectedTrip: SelectedTrip): Observable<any> {
        let params = selectedTrip.toParams();
        let httpParams = new HttpParams();
        Object.keys(params).forEach(function (key) {
            httpParams = httpParams.append(key, (params[key] != null && params[key] != undefined) ? params[key] : ' ');
        });

        return this.apiService.post(`/by-trip`, null, {params: httpParams});
    }

    public getDateTime(dateTime: string): any {
        let list = dateTime.split("T");
        let date = list[0];
        let time = list[1].split("+")[0];
        time = time.substring(0, time.length - 6);
        let dateList = date.split("-");
        dateList = dateList.reverse();
        let dateRes = dateList.join(".");
        return {
            date: dateRes,
            time: time
        }
    }

}
