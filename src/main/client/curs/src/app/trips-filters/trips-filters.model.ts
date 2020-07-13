import {City} from "../city.model";

export class TripsFiltersModel {
    private _dateStart: string;
    private _dateEnd: string;
    private _cityFrom: City;
    private _cityTo: City;
    private _passengersCount: number = 1;
    private _class: string;
    private _withHotel: string;


    get dateStart(): string {
        return this._dateStart;
    }

    set dateStart(value: string) {
        this._dateStart = value;
    }

    get dateEnd(): string {
        return this._dateEnd;
    }

    set dateEnd(value: string) {
        this._dateEnd = value;
    }


    get cityFrom(): City {
        return this._cityFrom;
    }

    set cityFrom(value: City) {
        this._cityFrom = value;
    }

    get cityTo(): City {
        return this._cityTo;
    }

    set cityTo(value: City) {
        this._cityTo = value;
    }

    get withHotel(): string {
        return this._withHotel;
    }

    set withHotel(value: string) {
        this._withHotel = value;
    }


    get passengersCount(): number {
        return this._passengersCount;
    }

    set passengersCount(value: number) {
        this._passengersCount = value;
    }

    get class(): string {
        return this._class;
    }

    set class(value: string) {
        this._class = value;
    }

    toParams(): any {
        return {
            dateStart: this.dateStart,
            dateEnd: this.dateEnd,
            from: this.cityFrom.id,
            to: this.cityTo.id,
            count: this.passengersCount,
            withHotels: true
        };
    }
}