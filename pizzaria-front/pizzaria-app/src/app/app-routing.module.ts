import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BebidasListComponent } from './components/bebida/bebidalist/bebidalist.component';
import { LoginComponent } from './components/sistema/login/login.component';
import { IndexComponent } from './components/layout/index/index.component';
import { SaborlistComponent } from './components/sabor/saborlist/saborlist.component';
import { EnderecolistComponent } from './components/endereco/enderecolist/enderecolist.component';
import { FuncionariolistComponent } from './components/funcionario/funcionariolist/funcionariolist.component';

const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: 'full' },
  { path: "login", component: LoginComponent },
  {
    path: "admin", component: IndexComponent, children: [
      { path: "bebida", component: BebidasListComponent },
      { path: "sabor", component: SaborlistComponent },
      { path: "endereco", component: EnderecolistComponent },
      { path: "funcionario", component: FuncionariolistComponent }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
