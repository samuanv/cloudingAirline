import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { ClarityModule } from '@clr/angular';
import { AppComponent } from './app.component';
import { ROUTING } from "./app.routing";
import { HomeComponent } from "./home/home.component";
import { AboutComponent } from "./about/about.component";
import { HttpClientModule } from "@angular/common/http";
import es from '@angular/common/locales/es';
import { registerLocaleData } from '@angular/common';

registerLocaleData(es);
@NgModule({
    declarations: [
        AppComponent,
        AboutComponent,
        HomeComponent
    ],
    imports: [
        BrowserAnimationsModule,
        BrowserModule,
        FormsModule,
        HttpModule,
        ClarityModule,
        ROUTING,
        HttpClientModule
    ],
    providers: [
        { provide: LOCALE_ID, useValue: 'es' }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
