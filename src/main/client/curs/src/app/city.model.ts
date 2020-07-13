export class City {
    id: number;
    name: string;
    country: string;
    constructor(params: any) {
        if (params == null) return;
        if (params) {
            this.id = params.id;
            this.name = params.name ? params.name : '';
            this.country = params.country ? params.country : '';
        }
    }
}