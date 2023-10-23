import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BebidasListComponent } from './components/bebida/bebidalist/bebidalist.component';
import { LoginComponent } from './components/sistema/login/login.component';
import { IndexComponent } from './components/layout/index/index.component';
import { SaborlistComponent } from './components/sabor/saborlist/saborlist.component';

const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: 'full' },
  { path: "login", component: LoginComponent },
  {
    path: "admin", component: IndexComponent, children: [
      { path: "bebida", component: BebidasListComponent },
      { path: "sabor", component: SaborlistComponent }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
