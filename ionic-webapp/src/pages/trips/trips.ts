import {Component} from "@angular/core";
import {NavController, NavParams} from "ionic-angular";
import {TripService} from "../../services/trip-service";
import {TripDetailPage} from "../trip-detail/trip-detail";

@Component({
  selector: 'page-trips',
  templateUrl: 'trips.html'
})
export class TripsPage {
  // list of trips
  trips: any = [];
  closeTrips: any = [];
  tripModel: any;
  threedays: boolean;

  constructor(public nav: NavController, private tripService: TripService, public navParams: NavParams) {
    this.tripModel = this.navParams.data.model;
    this.threedays =  this.navParams.data.bool;
    // set sample data

    if( this.threedays ) {
      tripService.getVuelosProximos(this.tripModel.fecha).subscribe( (vuelos) => {
        this.closeTrips = vuelos;
      });
    }

    tripService.getVuelos(this.tripModel).subscribe( (vuelos) => {
      this.trips = vuelos;
    });
  
    
    // this.trips = tripService.getAll();
  }

  // view trip detail
  viewDetail(id) {
    const allTrips = [...this.trips, ...this.closeTrips]
    const tripSelected = allTrips.filter( trip => trip.id === id); 
    this.nav.push(TripDetailPage, {trip: tripSelected[0]} );
  }
  getFecha(fechams: any): Date {
    return new Date(fechams);
  }
}
