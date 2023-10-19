import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BebidasListComponent } from './components/bebida/bebidalist/bebidalist.component';
import { BebidaDetailsComponent } from './components/bebida/bebidadetails/bebidadetails.component';
import { LoginComponent } from './components/sistema/login/login.component';
import { FooterComponent } from './components/layout/footer/footer.component';
import { HeaderComponent } from './components/layout/header/header.component';
import { IndexComponent } from './components/layout/index/index.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    BebidasListComponent,
    BebidaDetailsComponent,
    LoginComponent,
    FooterComponent,
    HeaderComponent,
    IndexComponent
  ],
  imports: [
    FormsModule,
    RouterModule,
    BrowserModule,
    NgbModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
