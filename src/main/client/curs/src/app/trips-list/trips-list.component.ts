import {Component, Input, OnInit} from '@angular/core';
import {AppService} from "../app.service";
import {takeUntil} from "rxjs/operators";
import {NgUnsubscribeComponent} from "../ng-unsubscribe/ng-unsubscribe.component";
import {Trip} from "../trip.model";
import {TripsFiltersModel} from "../trips-filters/trips-filters.model";

@Component({
    selector: 'trips-list',
    templateUrl: './trips-list.component.html',
    styleUrls: ['./trips-list.component.scss']
})
export class TripsListComponent extends NgUnsubscribeComponent implements OnInit {
    @Input() filters: TripsFiltersModel;

    tripsList: Trip[];

    constructor(private appService: AppService) {
        super();
    }

    ngOnInit() {
        this.appService.tripsList$.pipe(
            takeUntil(this.ngUnsubscribe))
            .subscribe(tripsList => this.tripsList = tripsList);
    }

}
