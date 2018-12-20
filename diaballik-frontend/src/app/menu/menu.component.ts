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
  modeMenu: any;

  dataSource: any[];
  displayedColumns = ['id', 'p1', 'p2', 'nbTurn', 'nbMoves', 'winner'];

  constructor(private http: HttpClient, private router: Router, private data: MyData) {
  }

  ngOnInit() {
    this.name1 = 'Tangi';
    this.name2 = 'Taha';
    this.scenario = 'STANDARD';
    this.human = true;
    this.modeMenu = 'new';
    this.dataSource = [];
    this.http.get(`game/savedGames/`).
    subscribe((returnedData) => {
      console.log('loading games ... ');
      // console.log(Object.values(returnedData));
      Object.values(returnedData).forEach( id => {
        this.http.put(`game/loadGame/${id}`, {}, {}).
        subscribe(gameDetail => {
          // console.log('load a game');
          // this.data.setStorage(returnedData);
          this.dataSource.push(gameDetail);
          // console.log(gameDetail);
          // console.log(this.dataSource);
        });
      });
    });
  }

  onSubmit() {
    if (!this.human) {
      // CREATE PVC
      this.data.loaded = false;
      this.router.navigate(['board'], { queryParams: {m: 'PVC', n1: this.name1, sce: this.scenario, d: this.difficulty }});
    } else {
      // CREATE PVP
      this.data.loaded = false;
      this.router.navigate(['board'], { queryParams: {m: 'PVP', n1: this.name1, n2: this.name2, sce: this.scenario }});
    }
    console.log('name1: ' + this.name1 + '\n' +
      '  name2: ' + this.name2 + '\n' +
      '  scenario: ' + this.scenario + '\n' +
      '  difficulty: ' + this.difficulty + '\n' +
      '  human: ' + this.human + '');
  }

  newGameclicked() {
    this.modeMenu = 'new';
  }
  loadGameclicked() {
    this.modeMenu = 'load';
  }

  cellClick(g) {
    console.log('CLICKED');
    console.log(g);
    this.human = (g.p2.type !== 'AIPlayer');
    console.log(this.human);
    if (!this.human) {
      // CREATE PVC
      console.log('PVC');
      console.log(g.p1.name);
      console.log(g.scenario);
      console.log(g.p2.difficulty);
      this.data.storage = g;
      this.data.loaded = true;
      this.router.navigate(['board'], { queryParams: {m: 'PVC', n1: g.p1.name, sce: g.scenario, d: g.p2.difficulty }});
    } else {
      // CREATE PVP
      console.log('PVP');
      console.log(g.p1.name);
      console.log(g.p2.name);
      console.log( g.scenario);
      this.data.storage = g;
      this.data.loaded = true;
      this.router.navigate(['board'], { queryParams: {m: 'PVP', n1: g.p1.name, n2: g.p2.name, sce: g.scenario }});
    }
  }


}
