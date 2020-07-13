export class Hotel {
    private _id: string;
    private _name: string;
    private _address: string;
    private _econom: number;
    private _comfort:number;
    private _lux: number;

    constructor(params: any) {
        if (!params) return;
        this._id = params.id;
        this._name = params.name;
        this._address = params.address;
        this._econom = params.econom;
        this._comfort = params.comfort;
        this._lux = params.lux;
    }


    get id(): string {
        return this._id;
    }

    set id(value: string) {
        this._id = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get address(): string {
        return this._address;
    }

    set address(value: string) {
        this._address = value;
    }

    get econom(): number {
        return this._econom;
    }

    set econom(value: number) {
        this._econom = value;
    }

    get comfort(): number {
        return this._comfort;
    }

    set comfort(value: number) {
        this._comfort = value;
    }

    get lux(): number {
        return this._lux;
    }

    set lux(value: number) {
        this._lux = value;
    }
}