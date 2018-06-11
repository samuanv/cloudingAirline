import { ReservationService } from './../../services/reservation-service';
import {Component} from "@angular/core";
import {NavController, LoadingController, ToastController, NavParams} from "ionic-angular";
import {HomePage} from "../home/home";
import { FormBuilder, FormGroup, FormArray } from "@angular/forms";
import { Storage } from '@ionic/storage';


@Component({
  selector: 'page-checkout-trip',
  templateUrl: 'checkout-trip.html'
})
export class CheckoutTripPage {
  // trip data
  public trip: any;
  public allReservations: any[] = [];
  public agencia: string;
  public agencia_id: string;
  

  // number of adults
  public adults = 0;
  public vip = 0;
  public total = 0;
  // number of children
  public children = 0;
  // date
  public date = new Date();

  public paymethods = 'creditcard';

  numbersA: Array<number>;
  numbersC: Array<number>;
  numbersV: Array<number>;

  form: FormGroup;

  constructor(public nav: NavController, private reservationService: ReservationService, public loadingCtrl: LoadingController,
     public toastCtrl: ToastController, public navParams: NavParams, private fb: FormBuilder, public storage: Storage) {
      
    this.storage.get('agencia').then( val => this.agencia = val);
    this.storage.get('agencia_id').then( val => this.agencia_id = val); 
    this.trip = this.navParams.data.trip;
    this.adults = this.navParams.data.adults;
    this.children = this.navParams.data.children;
    this.vip = this.navParams.data.vip;
    this.total = this.navParams.data.total;

    this.numbersA =  Array(this.adults).fill(0);
    this.numbersC =  Array(this.children).fill(0);
    this.numbersV =  Array(this.vip).fill(0);

    this.form = this.createGroup();
    this.fillForm();
  }

  fillForm(){
    let control = <FormArray>this.form.controls['pasajerosAdultos'];
    this.numbersA.forEach((index) => {
      control.push(this.initPasajero());
    });
    control = <FormArray>this.form.controls['pasajerosChildren'];    
    this.numbersC.forEach((index) => {
      control.push(this.initPasajero());
    });
    control = <FormArray>this.form.controls['pasajerosVip'];        
    this.numbersV.forEach((index) => {
      control.push(this.initPasajero());
    });
  }

  createGroup() {
    const group = this.fb.group({
      pasajerosAdultos: this.fb.array([
      ]),
      pasajerosChildren: this.fb.array([
      ]),
      pasajerosVip: this.fb.array([
      ]),
    });
    return group;
  }
  initPasajero() {
    return  this.fb.group({
      nombre: [''],
      apellidos: [''],
      dni: ['']
  });
  }
  createPassengers() {
     const allPasajeros = [...this.form.controls['pasajerosVip'].value, ...this.form.controls['pasajerosAdultos'].value, ... this.form.controls['pasajerosChildren'].value]
    this.reservationService.createPassengers(allPasajeros).subscribe( (passengers) => {
      console.log(passengers);
    });
  }
  createReservations(){
    this.form.controls['pasajerosVip'].value.forEach(element => {
      this.allReservations.push({
        numBultos: 0,
        embarquePrioritario: true,
        fechaPago: new Date(),
        asiento: 0,
        activa: true,
        pasajero_id: element.dni,
        agencia_id: this.agencia_id,
        vuelo_id: this.trip.id
      })
    });
    const otherPassagers = [ ...this.form.controls['pasajerosAdultos'].value, ... this.form.controls['pasajerosChildren'].value]
    otherPassagers.forEach(element => {
      this.allReservations.push({
        numBultos: 0,
        embarquePrioritario: false,
        fechaPago: new Date(),
        asiento: 0,
        activa: true,
        pasajero_id: element.dni,
        agencia_id: this.agencia_id,
        vuelo_id: this.trip.id
      })
    });
    this.reservationService.createReservations(this.allReservations).subscribe();
  }
  // process send button
  send() {
    this.createPassengers();
    this.createReservations();
    // send booking info
    let loader = this.loadingCtrl.create({
      content: "Espera mientras se realiza el pago..."
    });
    // show message
    let toast = this.toastCtrl.create({
      showCloseButton: true,
      cssClass: 'profile-bg',
      message: 'Reserva de vuelo realizada!',
      duration: 3000,
      position: 'bottom'
    });

    loader.present();

    setTimeout(() => {
      loader.dismiss();
      toast.present();
      // back to home page
      this.nav.setRoot(HomePage);
    }, 3000)
  }
}
