import { ReservationService } from './../../services/reservation-service';
import { Component } from '@angular/core';
import { NavController, AlertController } from 'ionic-angular';
import { Storage } from '@ionic/storage';
// import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'page-reservation',
  templateUrl: 'reservation.html'
})
export class ReservationPage {

  reservas: any;
  reservasPasadas: any;
  agencia_id: string;

  constructor(
    public navCtrl: NavController,
    private storage: Storage,
    private reservationService: ReservationService,
    public alertCtrl: AlertController) {
      this.storage.get('agencia_id').then( val => this.agencia_id = val); 
  }

  getFecha(fechams: any) {
    return new Date(fechams).toISOString().split('T')[0];
  }

  ionViewWillEnter() {
    this.reservationService.getReservas(this.agencia_id, false).subscribe( (reservas) => this.reservas = reservas);
    this.reservationService.getReservas(this.agencia_id, true).subscribe( (reservas) => this.reservasPasadas = reservas);
  }
  presentPrompt(reserva) {
    let alert = this.alertCtrl.create({
      title: 'Cambiar Nombre',
      inputs: [
        {
          name: 'Nombre',
          placeholder: 'Nombre'
        }
      ],
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel',
          handler: data => {
            console.log('Cancel clicked');
          }
        },
        {
          text: 'Cambiar',
          handler: data => {
            this.reservationService.changeName(reserva.pasajero_id, data.Nombre).subscribe( (pasajero) => console.log(pasajero));
          }
        }
      ]
    });
    alert.present();
  }

  cancel( reserva ){
    console.log(reserva);
  }
  edit( reserva ) {
    this.presentPrompt(reserva);
  }
  generate( reserva ){
    this.reservationService.generateTicket({vuelo_id: reserva.vuelo_id, agencia_id: reserva.agencia_id}).subscribe( () => {
      this.reservationService.getReservas(this.agencia_id, false).subscribe( reservas => this.reservas = reservas);
    } );
  }

}
