import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {Component} from "@angular/core";
import {NavController, PopoverController} from "ionic-angular";
import {Storage} from '@ionic/storage';

import {NotificationsPage} from "../notifications/notifications";
import {SettingsPage} from "../settings/settings";
import {TripsPage} from "../trips/trips";
import {SearchLocationPage} from "../search-location/search-location";


@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})

export class HomePage {

  fligthForm: FormGroup ;
  // search condition
  searchO = {
    name: '',
    date: new Date().toISOString()
  }
  searchD = {
    name: '',
    date: new Date().toISOString()
  }
  isToggled: boolean = false;
  
  constructor(private storage: Storage, public nav: NavController, public popoverCtrl: PopoverController, private formBuilder: FormBuilder) {
    this.fligthForm = this.formBuilder.group({
      id_origen:['', Validators.required],
      id_destino: ['', Validators.required],
      plazas: ['1', Validators.required],
      fecha: [ new Date().toISOString() , Validators.required],
      tipo: [ false , Validators.required]
    });
  }

  ionViewWillEnter() {
    this.storage.get('pickup').then((val) => {
      if (val === null) {
        this.searchO.name = "Sumter, United States"
        this.fligthForm.get('id_origen').setValue('00SC');
      } else {
        this.searchO.name = val.name;
        this.fligthForm.get('id_origen').setValue(val.id);
      }
    }).catch((err) => {
      console.log(err)
    });

    this.storage.get('dropOff').then((val) => {
      if (val === null) {
        this.searchD.name = "Fort Worth, United States";
        this.fligthForm.get('id_destino').setValue('00TA');
      } else {
        this.searchD.name = val.name;
        this.fligthForm.get('id_destino').setValue(val.id);
      }
    }).catch((err) => {
      console.log(err)
    });
  }

  // go to result page
  doSearch() {
    const consultaModel = this.fligthForm.value;

    if (consultaModel.tipo) {
      consultaModel.tipo = 'IDA_VUELTA';
    }else {
      consultaModel.tipo = 'IDA';
    }

    const year = consultaModel.fecha.split("-")[0];
    const month = consultaModel.fecha.split("-")[1];
    const day = ( consultaModel.fecha.split("-")[2] ).split("T")[0];

    consultaModel.fecha = month+'/'+day+'/'+year;

    this.nav.push(TripsPage,{ model: consultaModel, bool: this.isToggled} );
  }

  // choose place
  choosePlace(from) {
    this.nav.push(SearchLocationPage, from);
  }

  // to go account page
  goToAccount() {
    this.nav.push(SettingsPage);
  }

  presentNotifications(myEvent) {
    console.log(myEvent);
    let popover = this.popoverCtrl.create(NotificationsPage);
    popover.present({
      ev: myEvent
    });
  }

}

//
