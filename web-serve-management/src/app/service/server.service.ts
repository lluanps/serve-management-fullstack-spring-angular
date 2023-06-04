import { CustomResponse } from './../interface/custom-response';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomResponse } from '../interface/custom-response';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ServerService {

  private readonly apiUrl = 'any';

  constructor(private http: HttpClient) { }

  servers$ = <Observable<CustomResponse>>
   this.http.get<CustomResponse>(`${this.apiUrl}/servers/list`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  );

  handleError(handleError: any): Observable <never> {
    return throwError('Method not implemented.');
  }

  /*getServers(): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`http://localhots:8080/servers/list`);
  }*/

}
