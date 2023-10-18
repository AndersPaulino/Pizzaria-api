import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BebidalistComponent } from './components/bebida/bebidalist/bebidalist.component';
import { BebidadetailsComponent } from './components/bebida/bebidadetails/bebidadetails.component';

@NgModule({
  declarations: [
    AppComponent,
    BebidalistComponent,
    BebidadetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
