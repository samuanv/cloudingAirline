import { LoginService } from './../services/login-service';
import { Component, ViewChild, OnInit, OnDestroy } from "@angular/core";
import { Platform, Nav } from "ionic-angular";

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { Keyboard } from '@ionic-native/keyboard';

import { HomePage } from "../pages/home/home";
import { LoginPage } from "../pages/login/login";
import { Storage } from '@ionic/storage';
import { ISubscription } from 'rxjs/Subscription';
import { ReservationPage } from '../pages/reservation/reservation';

export interface MenuItem {
    title: string;
    component: any;
    icon: string;
}

@Component({
  templateUrl: 'app.html'
})

export class MyApp implements OnInit, OnDestroy {
  @ViewChild(Nav) nav: Nav;

  rootPage: any = LoginPage;
  
  agencyName: string = '';
  appMenuItems: Array<MenuItem>;
  subcriptionLogin: ISubscription ;

  constructor(
    public platform: Platform,
    public statusBar: StatusBar,
    public splashScreen: SplashScreen,
    public keyboard: Keyboard,
    private storage: Storage,
    private loginService: LoginService
  ) {
    this.initializeApp();

    this.appMenuItems = [
      {title: 'Home', component: HomePage, icon: 'home'},
      {title: 'Reservations', component: ReservationPage, icon: 'paper'}
    ];
  }

  initializeApp() {
    this.platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.

      //*** Control Splash Screen
      // this.splashScreen.show();
      // this.splashScreen.hide();

      //*** Control Status Bar
      this.statusBar.styleDefault();
      this.statusBar.overlaysWebView(false);

      //*** Control Keyboard
      this.keyboard.disableScroll(true);
    });
  }

  ngOnInit() {
    this.subcriptionLogin = this.loginService.getLogged().subscribe( val => {
      if (val){
        this.getName();
      }
     } );
  }
  ngOnDestroy() {
    this.subcriptionLogin.unsubscribe();
  }
  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    this.nav.setRoot(page.component);
  }

  logout() {
    //this.storage.remove('agencia');
    //this.storage.remove('agencia_id');
    this.storage.clear();
    this.loginService.logout();
    this.nav.setRoot(LoginPage);
  }
  getName() {
    this.storage.get('agencia').then((val) => {
      this.agencyName = val;
    });
  }
  

}
