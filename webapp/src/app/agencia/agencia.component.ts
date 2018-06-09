import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-agencia',
  templateUrl: './agencia.component.html',
  styleUrls: ['./agencia.component.scss']
})
export class AgenciaComponent implements OnInit {

  agencias;
  images = [
    'images/travel1.jpg',
    'images/travel2.png',
    'images/travel3.png',
    'images/travel4.png'
  ];
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.getAgencias();
  }
  getAgencias() {
    this.http.get(`http://localhost:8080/agencia`).subscribe(res => this.agencias = res);
  }

}
