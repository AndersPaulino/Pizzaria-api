import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BebidasListComponent } from './components/bebida/bebidalist/bebidalist.component';
import { BebidaDetailsComponent } from './components/bebida/bebidadetails/bebidadetails.component';


@NgModule({
  declarations: [
    AppComponent,
    BebidasListComponent,
    BebidaDetailsComponent
  ],
  imports: [
    RouterModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
