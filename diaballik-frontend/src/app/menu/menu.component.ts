import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {MyData} from '../mydata';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  name1: string;
  name2: string;
  scenario: string;
  difficulty: string;
  human: boolean;

  constructor(private http: HttpClient, private router: Router, private data: MyData) {
  }

  ngOnInit() {
    this.name1 = 'Tangi';
    this.name2 = 'Taha';
    this.scenario = 'STANDARD';
    this.human = true;
  }

  onSubmit() {
    if (!this.human) {
      // CREATE PVC
      this.http.put(`game/newGamePVC/${this.name1.toUpperCase()}/${this.scenario.toUpperCase()}/${this.difficulty.toUpperCase()}`, {}, {}).
      subscribe(returnedData => {
        console.log('init game : ');
        console.log(returnedData);
        this.data.setStorage(returnedData);
        this.router.navigate(['board']);
      });
    } else {
      // CREATE PVP
      this.http.put(`game/newGamePVP/${this.name1.toUpperCase()}/${this.name2.toUpperCase()}/${this.scenario.toUpperCase()}`, {}, {}).
      subscribe(returnedData => {
        console.log('init game : ');
        console.log(returnedData);
        this.data.setStorage(returnedData);
        this.router.navigate(['board']);
      });
    }
    console.log('name1: ' + this.name1 + '\n' +
      '  name2: ' + this.name2 + '\n' +
      '  scenario: ' + this.scenario + '\n' +
      '  difficulty: ' + this.difficulty + '\n' +
      '  human: ' + this.human + '');
  }
}
