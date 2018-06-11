import {Component} from "@angular/core";
import {NavController, AlertController, ToastController, MenuController} from "ionic-angular";
import {HomePage} from "../home/home";
import {RegisterPage} from "../register/register";
import { Storage } from '@ionic/storage';
import { LoginService } from "../../services/login-service";
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {

  loginForm: FormGroup;

  constructor(public nav: NavController, public forgotCtrl: AlertController, public menu: MenuController, public toastCtrl: ToastController,
  private loginService: LoginService, private storage: Storage, private formBuilder: FormBuilder) {
    this.menu.swipeEnable(false);
    this.loginForm = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }


  // login and go to home page
  login(username,password) {
   const loginModel = this.loginForm.value;
    this.loginService.login(loginModel).subscribe( (agencia) => {
      this.storage.set('agencia', agencia.name).then( () => {
        this.loginService.setLogged(true);
      });
      this.storage.set('agencia_id', agencia.id);
      this.nav.setRoot(HomePage);
    }, err => {
      const toast = this.toastCtrl.create({
        message: 'Login incorrecto, vuelve a intentarlo',
        duration: 3000
      });
      toast.present();
    });
    // this.nav.setRoot(HomePage);
    
  }

   // go to register page
   register() {
    this.nav.setRoot(RegisterPage);
  }
  forgotPass() {
    let forgot = this.forgotCtrl.create({
      title: 'Forgot Password?',
      message: "Enter you email address to send a reset link password.",
      inputs: [
        {
          name: 'email',
          placeholder: 'Email',
          type: 'email'
        },
      ],
      buttons: [
        {
          text: 'Cancel',
          handler: data => {
            console.log('Cancel clicked');
          }
        },
        {
          text: 'Send',
          handler: data => {
            console.log('Send clicked');
            let toast = this.toastCtrl.create({
              message: 'Email was sended successfully',
              duration: 3000,
              position: 'top',
              cssClass: 'dark-trans',
              closeButtonText: 'OK',
              showCloseButton: true
            });
            toast.present();
          }
        }
      ]
    });
    forgot.present();
  }
}