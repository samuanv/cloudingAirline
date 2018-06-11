import { Observable } from 'rxjs/Observable';
import {Injectable} from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";

@Injectable()
export class ReservationService {

  constructor(private httpClient: HttpClient) {
  }

  addReservation(): Observable<any> {
    return;
  }

  createPassengers(passengers): Observable<any> {
    return this.httpClient.post('http://localhost:8080/pasajero',passengers);    
  }
  createReservations(reservations): Observable<any> {
    return this.httpClient.post('http://localhost:8080/reserva',reservations);
  }

  getReservas(agencia_id, realizado): Observable<any> {
    let params = new HttpParams();
    params = params.append('realizado', realizado);
    return this.httpClient.get('http://localhost:8080/reserva/'+agencia_id, {params: params});
  }
  changeName(id,name): Observable<any> {
    return this.httpClient.put('http://localhost:8080/pasajero/'+id, name);
  }
  generateTicket(embarque): Observable<any> {
    return this.httpClient.post('http://localhost:8080/reserva/embarcar', embarque);
  }

}
