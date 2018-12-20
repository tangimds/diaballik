import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {MyData} from '../mydata';
import {NgForm} from '@angular/forms';
import {homedir} from 'os-homedir';
import * as os from "os";


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
  displayedColumns = ['id', 'p1', 'p2'];

  constructor(private http: HttpClient, private router: Router, private data: MyData) {
  }

  ngOnInit() {
    this.name1 = 'Tangi';
    this.name2 = 'Taha';
    this.scenario = 'STANDARD';
    this.human = true;
    this.modeMenu = 'new';
    this.dataSource = [
      {"p1":{"type":"HumanPlayer","@id":1,"name":"jean","color":"WHITE"},"p2":{"type":"AIPlayer","@id":2,"name":null,"color":"BLACK","difficulty":"NOOB"},"scenario":"STANDARD","nbTurn":2,"id":1544048964387,"actions":[{"type":"MovePiece","piece":{"type":"Piece","color":"WHITE","x":1,"y":2},"dx":0,"dy":1},{"type":"MovePiece","piece":{"type":"Piece","color":"WHITE","x":3,"y":2},"dx":0,"dy":1},{"type":"MovePiece","piece":{"type":"Piece","color":"WHITE","x":3,"y":2},"dx":1,"dy":0},{"type":"MovePiece","piece":{"type":"Piece","color":"BLACK","x":1,"y":5},"dx":0,"dy":-1},{"type":"MovePiece","piece":{"type":"Piece","color":"BLACK","x":1,"y":5},"dx":0,"dy":-1},{"type":"MoveBall","startingPiece":{"type":"Piece","color":"BLACK","x":4,"y":7},"endingPiece":{"type":"Piece","color":"BLACK","x":3,"y":7}}],"board":{"currentWhiteHolder":{"type":"Piece","color":"WHITE","x":4,"y":1},"currentBlackHolder":{"type":"Piece","color":"BLACK","x":3,"y":7},"pieces":[{"type":"Piece","color":"WHITE","x":1,"y":2},{"type":"Piece","color":"WHITE","x":3,"y":2},{"type":"Piece","color":"WHITE","x":3,"y":1},{"type":"Piece","color":"WHITE","x":4,"y":1},{"type":"Piece","color":"WHITE","x":5,"y":1},{"type":"Piece","color":"WHITE","x":6,"y":1},{"type":"Piece","color":"WHITE","x":7,"y":1},{"type":"Piece","color":"BLACK","x":1,"y":5},{"type":"Piece","color":"BLACK","x":2,"y":7},{"type":"Piece","color":"BLACK","x":3,"y":7},{"type":"Piece","color":"BLACK","x":4,"y":7},{"type":"Piece","color":"BLACK","x":5,"y":7},{"type":"Piece","color":"BLACK","x":6,"y":7},{"type":"Piece","color":"BLACK","x":7,"y":7}]},"winner":"NONE"}
      ,
      {'id':66666,'p1':{'type':'HumanPlayer','@id':1,'name':'mabite','color':'WHITE'},'p2':{'type':'HumanPlayer','@id':1,'name':'toto','color':'WHITE'}}
    ];
  }

  onSubmit() {
    if (!this.human) {
      // CREATE PVC
      this.router.navigate(['board'], { queryParams: {m: 'PVC', n1: this.name1, sce: this.scenario, d: this.difficulty }});
    } else {
      // CREATE PVP
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


}
