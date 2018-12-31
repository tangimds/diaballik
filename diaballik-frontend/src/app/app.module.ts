import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { BoardComponent } from './board/board.component';
import { MenuComponent } from './menu/menu.component';
import {MyData} from './mydata';
import {DragDropModule} from '@angular/cdk/drag-drop';
import { MatInputModule, MatPaginatorModule, MatProgressSpinnerModule,
  MatSortModule, MatTableModule } from '@angular/material';
import { NewGameMenuComponent } from './new-game-menu/new-game-menu.component';
import { LoadGameMenuComponent } from './load-game-menu/load-game-menu.component';


const appRoutes: Routes = [
  { path: '',
    redirectTo: '/menu',
    pathMatch: 'full'
  },
  {
    path: 'menu',
    component: MenuComponent
  },
  {
    path: 'board',
    component: BoardComponent
  },
  {
    path: 'newGameMenu',
    component: NewGameMenuComponent
  },
  {
    path: 'loadGameMenu',
    component: LoadGameMenuComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    MenuComponent,
    NewGameMenuComponent,
    LoadGameMenuComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: false }
    ),
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    DragDropModule,
    MatInputModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSortModule,
    MatTableModule
  ],
  providers: [MyData],
  bootstrap: [AppComponent]
})
export class AppModule { }
