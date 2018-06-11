import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class LoginService {

  private _logged = new BehaviorSubject<boolean>(false);

  constructor(private httpClient: HttpClient) {
  }

  login(loginModel: any): Observable<any> {
    return this.httpClient.post('http://localhost:8080/agencia/login', loginModel);
  }
  logout(){
    this._logged.next(false); 
    
  }
  setLogged( log: boolean) {
    this._logged.next(log); 
  }
  getLogged() {
    return this._logged;
  }
}
