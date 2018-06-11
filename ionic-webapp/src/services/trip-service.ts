import { Observable } from 'rxjs/Observable';
import {Injectable} from "@angular/core";
import {TRIPS} from "./mock-trips";
import { HttpClient, HttpParams } from "@angular/common/http";

@Injectable()
export class TripService {
  private trips: any;

  constructor(private httpClient: HttpClient) {
    this.trips = TRIPS;
  }

  getVuelos(vueloModel): Observable<any> {
    let params = new HttpParams();
    params = params.append('id_origen', vueloModel.id_origen);
    params = params.append('id_destino', vueloModel.id_destino);
    params = params.append('fecha', vueloModel.fecha);
    // params = params.append('tipo', vueloModel.tipo);
    params = params.append('plazas', vueloModel.plazas);

    return this.httpClient.get('http://localhost:8080/vuelo', {params: params});
  }

  getVuelosProximos(fecha) {
    let params = new HttpParams();
    params = params.append('fecha', fecha);
    return this.httpClient.get('http://localhost:8080/vuelo/proximo', {params: params});
    
  }
  getAll() {
    return this.trips;
  }

  getItem(id): Observable<any> {
    let params = new HttpParams();
    params = params.append('id', id);
    return this.httpClient.get('http://localhost:8080/vuelo/proximo', {params: params});
  }

  remove(item) {
    this.trips.splice(this.trips.indexOf(item), 1);
  }
}
