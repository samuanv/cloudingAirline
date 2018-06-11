/*
 * Copyright (c) 2016 VMware, Inc. All Rights Reserved.
 * This software is released under MIT license.
 * The full license information can be found in LICENSE in the root directory of this project.
 */
import { Component, OnInit, ViewChild } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ClrDatagridStringFilterInterface } from "@clr/angular";
import { FormControl, FormGroup, Validators, MaxLengthValidator } from '@angular/forms';

class NameFilter implements ClrDatagridStringFilterInterface<any> {
    accepts(vuelo, search: string): boolean {
        return "" + vuelo.nombre === search
            || vuelo.nombre.toLowerCase().indexOf(search) >= 0;
    }
}
@Component({
    styleUrls: ['./home.component.scss'],
    templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {
    @ViewChild('wizard') wizard;

    vuelos;
    aeropuertos;
    aviones;
    nameFilter = new NameFilter();
    wizardOpened = false;
    avionSelected;
    createAvionForm: FormGroup;
    loading = false;
    constructor(private http: HttpClient ) {
        this.initForm();
    }
    initForm() {
        this.createAvionForm = new FormGroup ({
            nombre: new FormControl('', Validators.required),
            fechaSalida: new FormControl('', Validators.required),
            horaSalida: new FormControl('', Validators.required),
            embarque: new FormControl('', [Validators.required, Validators.max(0)]),
            tarifa: new FormControl('', Validators.required),
            aeropuertoOrigen: new FormControl('', Validators.required),
            aeropuertoDestino: new FormControl('', Validators.required),
        });
    }
    ngOnInit() {
        this.getVuelos();
        this.getAeropuertos();
        this.getAviones();
    }
    getAviones() {
        this.http.get(`http://localhost:8080/avion`).subscribe(res => this.aviones = res);
    }
    getVuelos() {
        this.http.get(`http://localhost:8080/vuelo`).subscribe(res => this.vuelos = res);
    }
    getAeropuertos() {
        this.http.get(`http://localhost:8080/aeropuerto`).subscribe(res => this.aeropuertos = res)
    }
    onAdd() {
        this.wizardOpened = true;
    }
    createAvion() {
        this.loading = true;
        const formFilled = this.createAvionForm.value;
        const form = {
            nombre: formFilled.nombre,
            fechaSalida: new Date(`${formFilled.fechaSalida} ${formFilled.horaSalida} `),
            fechaEmbarque: new Date(`${formFilled.fechaSalida} ${formFilled.horaSalida} `),
            tarifa: formFilled.tarifa,
            aeropuertoOrigen_id: formFilled.aeropuertoOrigen.id,
            aeropuertoDestino_id: formFilled.aeropuertoDestino.id,
            tipoVuelo: 'IDA_VUELTA',
            avion_id: this.avionSelected.id,
            fechaCreacionReserva: new Date()
        }
        form.fechaEmbarque.setMinutes(new Date(form.fechaSalida).getMinutes() + formFilled.embarque);
        this.http.post(`http://localhost:8080/vuelo`, form).subscribe(res => {
            console.log('enviado');
            this.loading = false;
            this.getVuelos();
            this.wizardClosed();
        })
    }
    wizardClosed() {
        this.wizard.reset();
        this.initForm();
    }
}
