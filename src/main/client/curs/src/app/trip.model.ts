import {Hotel} from "./hotel.model";

export class Trip {

    private _idIn: number;
    private _idBack: number;
    private _dateStartFrom: string;
    private _dateEndFrom: string;
    private _dateStartTo: string;
    private _dateEndTo: string;
    private _economPlaneIn: number;
    private _businessPlaneIn: number;
    private _firstClassPlaneIn: number;
    private _economPlaneBack: number;
    private _businessPlaneBack: number;
    private _firstClassPlaneBack: number;
    private _withHotel: boolean;
    private _hotels: Hotel[];

    constructor(params: any) {
        if (!params) return;
        this._idIn = params.idIn;
        this._idBack = params.idBack;
        this._dateStartFrom = params.dateStartFrom;
        this._dateStartTo = params.dateStartTo;
        this._dateEndFrom = params.dateEndFrom;
        this._dateEndTo = params.dateEndTo;
        this._economPlaneIn = params.economPlaneIn;
        this._businessPlaneIn = params.businessPlaneIn;
        this._firstClassPlaneIn = params.firstClassPlaneIn;
        this._economPlaneBack = params.economPlaneBack;
        this._businessPlaneBack = params.businessPlaneBack;
        this._firstClassPlaneBack = params.firstClassPlaneBack;
        this._withHotel = params.withHotel;
        if (this._withHotel) {
            this._hotels = params.hotels ? params.hotels.map(hotel => new Hotel(hotel)) : null;
        }
    }


    get idIn(): number {
        return this._idIn;
    }

    set idIn(value: number) {
        this._idIn = value;
    }

    get idBack(): number {
        return this._idBack;
    }

    set idBack(value: number) {
        this._idBack = value;
    }

    get dateStartFrom(): string {
        return this._dateStartFrom;
    }

    set dateStartFrom(value: string) {
        this._dateStartFrom = value;
    }

    get dateEndFrom(): string {
        return this._dateEndFrom;
    }

    set dateEndFrom(value: string) {
        this._dateEndFrom = value;
    }

    get dateStartTo(): string {
        return this._dateStartTo;
    }

    set dateStartTo(value: string) {
        this._dateStartTo = value;
    }

    get dateEndTo(): string {
        return this._dateEndTo;
    }

    set dateEndTo(value: string) {
        this._dateEndTo = value;
    }


    get economPlaneIn(): number {
        return this._economPlaneIn;
    }

    set economPlaneIn(value: number) {
        this._economPlaneIn = value;
    }

    get businessPlaneIn(): number {
        return this._businessPlaneIn;
    }

    set businessPlaneIn(value: number) {
        this._businessPlaneIn = value;
    }


    get firstClassPlaneIn(): number {
        return this._firstClassPlaneIn;
    }

    set firstClassPlaneIn(value: number) {
        this._firstClassPlaneIn = value;
    }

    get economPlaneBack(): number {
        return this._economPlaneBack;
    }

    set economPlaneBack(value: number) {
        this._economPlaneBack = value;
    }

    get businessPlaneBack(): number {
        return this._businessPlaneBack;
    }

    set businessPlaneBack(value: number) {
        this._businessPlaneBack = value;
    }


    get firstClassPlaneBack(): number {
        return this._firstClassPlaneBack;
    }

    set firstClassPlaneBack(value: number) {
        this._firstClassPlaneBack = value;
    }

    get withHotel(): boolean {
        return this._withHotel;
    }

    set withHotel(value: boolean) {
        this._withHotel = value;
    }

    get hotels(): Hotel[] {
        return this._hotels;
    }

    set hotels(value: Hotel[]) {
        this._hotels = value;
    }
}