import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
// @Injectable()
export class ApiService {
    private static API_NAME = 'api';
    public static API = ApiService.API_NAME + '/';

    constructor(private http: HttpClient) {
    }

    get(url: string, options?: Object): Observable<any> {
        return this.http.get(ApiService.API + url, options);
    }

    post(url: string, body: any, options?: Object): Observable<any> {
        return this.http.post(ApiService.API + url, body, options);
    }

    put(url: string, body: any, options?: Object): Observable<any> {
        return this.http.put(ApiService.API + url, body, options);
    }
}
