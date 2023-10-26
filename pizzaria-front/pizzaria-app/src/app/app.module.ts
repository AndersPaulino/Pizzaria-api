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
import { SaborlistComponent } from './components/sabor/saborlist/saborlist.component';
import { SabordetailsComponent } from './components/sabor/sabordetails/sabordetails.component';
import { EnderecolistComponent } from './components/endereco/enderecolist/enderecolist.component';
import { EnderecodetailsComponent } from './components/endereco/enderecodetails/enderecodetails.component';
import { FuncionariodetailsComponent } from './components/funcionario/funcionariodetails/funcionariodetails.component';
import { FuncionariolistComponent } from './components/funcionario/funcionariolist/funcionariolist.component';
import { ClientedetailsComponent } from './components/cliente/clientedetails/clientedetails.component';
import { ClientelistComponent } from './components/cliente/clientelist/clientelist.component';
import { PizzalistComponent } from './components/pizza/pizzalist/pizzalist.component';
import { PizzadetailsComponent } from './components/pizza/pizzadetails/pizzadetails.component';

@NgModule({
  declarations: [
    AppComponent,
    BebidasListComponent,
    BebidaDetailsComponent,
    LoginComponent,
    FooterComponent,
    HeaderComponent,
    IndexComponent,
    SaborlistComponent,
    SabordetailsComponent,
    EnderecolistComponent,
    EnderecodetailsComponent,
    FuncionariodetailsComponent,
    FuncionariolistComponent,
    ClientedetailsComponent,
    ClientelistComponent,
    PizzalistComponent,
    PizzadetailsComponent
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
