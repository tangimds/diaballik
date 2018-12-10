import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { MyData } from '../mydata';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  private p1name: any;
  private p2name: any;

  constructor(private http: HttpClient, private router: Router, private data: MyData) { }

  ngOnInit() {
  }

  onSubmit(form: NgForm) {
    /*if ( form.value.P2type === 'AI' ) {
      // CREATE PVC
      this.http.put(`game/newGamePVC/${form.value.p1name.toUpperCase()}/${form.value.scenario.toUpperCase()}/${form.value.difficulty.toUpperCase()}`, {}, {}).
      subscribe(returnedData => {
        console.log('init game : ');
        console.log(returnedData);
        this.data.setStorage(returnedData);
        this.router.navigate(['board']);
      });
    } else {
      // CREATE PVP
      this.http.put(`game/newGamePVP/${form.value.p1name.toUpperCase()}/${form.value.p2name.toUpperCase()}/${form.value.scenario.toUpperCase()}`, {}, {}).
      subscribe(returnedData => {
        console.log('init game : ');
        console.log(returnedData);
        this.data.setStorage(returnedData);
        this.router.navigate(['board']);
      });
    }*/console.log(form);
  }

  player2isAI(form: NgForm) {
    return form.value.P2type === 'AI';
  }
}
