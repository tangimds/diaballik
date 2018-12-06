import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { BoardComponent } from './board/board.component';
import { MenuComponent } from './menu/menu.component';
import {MyData} from './mydata';

const appRoutes: Routes = [
  { path: '',
    redirectTo: '/config',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    MenuComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: false }
    ),
    BrowserModule,
    HttpClientModule
  ],
  providers: [MyData],
  bootstrap: [AppComponent]
})
export class AppModule { }
