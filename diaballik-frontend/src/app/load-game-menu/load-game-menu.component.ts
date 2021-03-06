import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { MyData } from '../mydata';
import { forkJoin } from 'rxjs';


@Component({
  selector: 'app-load-game-menu',
  templateUrl: './load-game-menu.component.html',
  styleUrls: ['./load-game-menu.component.css']
})
export class LoadGameMenuComponent implements OnInit {
  name1: string;
  name2: string;
  scenario: string;
  difficulty: string;
  human: boolean;
  modeMenu: any;
  loaded: boolean = false;
  selectedRow:any;

  //  dataSource: DataTableDataSource;
  dataSource: any;
  displayedColumns = ['id', 'p1', 'p2', 'nbTurn', 'nbMoves', 'winner'];

  constructor(private http: HttpClient, private router: Router, private data: MyData) {
  }

  ngOnInit() {
    //this.dataSource = new DataTableDataSource();
    this.name1 = 'Tangi';
    this.name2 = 'Taha';
    this.scenario = 'STANDARD';
    this.human = true;
    this.dataSource = [];
    this.http.get(`game/savedGames/`).
      subscribe(returnedData => {
        console.log('loading games ... ');
        console.log(Object.values(returnedData));
        const allObs = forkJoin(...Object.values(returnedData).map(id => this.http.put(`game/loadGame/${id}`, {}, {})));
        allObs.subscribe(gameDetails => {
          gameDetails.forEach(element => {
            this.dataSource.push(element);
          });
          this.loaded = true;
        });
      }
      );
  }

  rowClick(g) {
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
      this.router.navigate(['board'], { queryParams: { m: 'PVC', n1: g.p1.name, sce: g.scenario, d: g.p2.difficulty } });
    } else {
      // CREATE PVP
      console.log('PVP');
      console.log(g.p1.name);
      console.log(g.p2.name);
      console.log(g.scenario);
      this.data.storage = g;
      this.data.loaded = true;
      this.router.navigate(['board'], { queryParams: { m: 'PVP', n1: g.p1.name, n2: g.p2.name, sce: g.scenario } });
    }
  }

  rowHighlight(g){
    this.selectedRow = g.id;
  }

  rowUnhighlight(){
    this.selectedRow = null;
  }


}
