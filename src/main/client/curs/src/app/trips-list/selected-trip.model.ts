import {Hotel} from "../hotel.model";

export class SelectedTrip {
    flightIdIn: number;
    flightIdBack: number;
    flightInSeat: number;
    flightBackSeat: number;
    hotel: Hotel;
    hotelType: number;
    count: number;

    public toParams(): any {
        return {
            flightIdIn: this.flightIdIn,
            flightIdBack: this.flightIdBack,
            flightInSeat: this.flightInSeat,
            flightBackSeat: this.flightBackSeat,
            count: this.count,
            hotelId: this.hotel ? this.hotel.id : null,
            hotelType: this.hotel ? this.hotelType : null
        };
    }
}