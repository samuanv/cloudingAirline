import {Component} from "@angular/core";
import {NavController, NavParams} from "ionic-angular";
import {CheckoutTripPage} from "../checkout-trip/checkout-trip";

@Component({
  selector: 'page-trip-detail',
  templateUrl: 'trip-detail.html'
})
export class TripDetailPage {
  // trip info
  public tripdetail: any;
  // number of adult
  public adults = 0;
  public vip = 0;
  public total = 0;
  // number of children
  public children = 0;

  constructor(public nav: NavController, public navParams: NavParams) {
    this.tripdetail = this.navParams.data.trip;
    
   // private tripService: TripService, this.trip = tripService.getItem(this.navParams.data.id); 
   
  }

  // minus adult when click minus button
  minusAdult() {
    this.adults--;
  }

  // plus adult when click plus button
  plusAdult() {
    this.adults++;
  }


  minusVip() {
    this.vip--;
  }
  plusVip() {
    this.vip++;
  }

  // minus children when click minus button
  minusChildren() {
    this.children--;
  }

  // plus children when click plus button
  plusChildren() {
    this.children++;
  }

  makeSum(): number {
    this.total = (this.adults * this.tripdetail.tarifa) + (this.children * (this.tripdetail.tarifa /2 )) + (this.vip * (this.tripdetail.tarifa *1.25 ));
    return this.total;
  }
  // go to checkout page
  checkout() {
    this.nav.push(CheckoutTripPage, {trip: this.tripdetail, adults: this.adults, children: this.children, vip: this.vip, total: this.total});
  }
}
