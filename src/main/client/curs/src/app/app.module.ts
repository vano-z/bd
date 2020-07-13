import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TripsFiltersComponent } from './trips-filters/trips-filters.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { TripsListComponent } from './trips-list/trips-list.component';
import { NgUnsubscribeComponent } from './ng-unsubscribe/ng-unsubscribe.component';
import { TripDetailsComponent } from './trips-list/trip-details/trip-details.component';
import { TripDetailsWithHotelsComponent } from './trips-list/trip-details-with-hotels/trip-details-with-hotels.component';
import {MomentModule} from "ngx-moment";

@NgModule({
  declarations: [
    AppComponent,
    TripsFiltersComponent,
    TripsListComponent,
    NgUnsubscribeComponent,
    TripDetailsComponent,
    TripDetailsWithHotelsComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        MomentModule,
        ReactiveFormsModule
    ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
