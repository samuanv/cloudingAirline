import {Component} from "@angular/core";
import {NavController, NavParams} from "ionic-angular";
import {Storage} from '@ionic/storage';

// import {SearchCarsPage} from "../search-cars/search-cars";

@Component({
  selector: 'page-search-location',
  templateUrl: 'search-location.html'
})

export class SearchLocationPage {
  public fromto: any;
  // places
  public places = {
    nearby: [
      {
        id: '00SC',
        name: "Sumter, United States"
      },
      {
        id: '00TA',
        name: "Fort Worth, United States"
      },
      {
        id: '00GA',
        name: "Lithonia, United States"
      },
      {
        id: '00HI',
        name: "Kailua/Kona, United States"
      },
      {
        id: '00KY',
        name: "Stanford, United States"
      }
    ],
    recent: [
      {
        id: '00SC',
        name: "Sumter, United States"
      }
    ]
  };

  constructor(private storage: Storage, public nav: NavController, public navParams: NavParams) {
    this.fromto = this.navParams.data;
  }

  // search by item
  searchBy(item) {
    if (this.fromto === 'from') {
      this.storage.set('pickup', item);
    }

    if (this.fromto === 'to') {
      this.storage.set('dropOff', item);
    }
    // this.nav.push(SearchCarsPage);
    this.nav.pop();
  }
}
