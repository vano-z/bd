import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subject} from "rxjs";

@Component({
    selector: 'ng-unsubscribe',
    templateUrl: './ng-unsubscribe.component.html',
    styleUrls: ['./ng-unsubscribe.component.scss']
})
export class NgUnsubscribeComponent implements OnDestroy {
    protected ngUnsubscribe: Subject<void> = new Subject();
    constructor() { }

    ngOnDestroy(): void {
        this.ngUnsubscribe.next();
        this.ngUnsubscribe.complete();
    }
}
