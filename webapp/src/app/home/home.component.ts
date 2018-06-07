/*
 * Copyright (c) 2016 VMware, Inc. All Rights Reserved.
 * This software is released under MIT license.
 * The full license information can be found in LICENSE in the root directory of this project.
 */
import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ClrDatagridStringFilterInterface } from "@clr/angular";

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
    vuelos;
    nameFilter = new NameFilter();
    wizardOpened = false;
    constructor(private http: HttpClient) {

    }
    ngOnInit() {
        this.http.get(`http://localhost:8080/vuelo`).subscribe(res => this.vuelos = res);
    }
    onAdd() {
        this.wizardOpened = true;
    }
}
