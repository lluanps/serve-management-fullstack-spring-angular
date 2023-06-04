import { CustomResponse } from './../interface/custom-response';
import { HttpClient, HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Server } from '../interface/server';
import { Status } from '../enum/status.enum';

@Injectable({
  providedIn: 'root'
})
export class ServerService {

  private readonly apiUrl = 'http://localhost/8080';

  constructor(private http: HttpClient) { }

  servers$ = <Observable<CustomResponse>>
   this.http.get<CustomResponse>(`${this.apiUrl}/servers/list`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  );

  handleErrorm(handleError: any): Observable <never> {
    return throwError('Method not implemented.');
  }

  /*getServers(): Observable<CustomResponse> {
    return this.http.get<CustomResponse>(`http://localhots:8080/servers/list`);
  }*/

  save$ = (server: Server) => <Observable<CustomResponse>>
   this.http.post<CustomResponse>(`${this.apiUrl}/servers/save`, server)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  );

  ping$ = (ipAddress: string) => <Observable<CustomResponse>>
   this.http.get<CustomResponse>(`${this.apiUrl}/servers/ping/${ipAddress}`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  );

  /*
  metodo pora filtrar
  filter$ = (serverId: Status, response: CustomResponse) => <Observable<CustomResponse>>
   new Observable<CustomResponse>(
    suscriber => {
      console.log(response);
      suscriber.next(
        status == Status.ALL ? {...response, message: `Servers filter by ${status} status`}
      )
    }
   )
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  );
  */

  delete$ = (serverId: number) => <Observable<CustomResponse>>
   this.http.delete<CustomResponse>(`${this.apiUrl}/servers/delete/${serverId}`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  );



  private handleError(error: HttpErrorResponse): Observable <never> {
    console.log(error);

    return throwError(`An error occurred - Error code: ${error.status}`);
  }

}
