import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./auth/login/login.component";
import {RegisterComponent} from "./auth/register/register.component";
import {AboutComponent} from "./pages/about/about.component"
import {MainComponent} from "./pages/main/main.component";
import {ShopCardComponent} from "./pages/shop-card/shop-card.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'about', component: AboutComponent},
  {path: 'main', component: MainComponent},
  {path: 'shopCard', component: ShopCardComponent},
  {path: '', redirectTo: 'main', pathMatch: 'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
