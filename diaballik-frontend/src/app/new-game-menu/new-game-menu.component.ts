import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {MyData} from '../mydata';

@Component({
  selector: 'app-new-game-menu',
  templateUrl: './new-game-menu.component.html',
  styleUrls: ['./new-game-menu.component.css']
})
export class NewGameMenuComponent implements OnInit {
  name1: string;
  name2: string;
  scenario: string;
  difficulty: string;
  human: boolean;
  modeMenu: any;
  imageUrl: string;

  displayedColumns = ['id', 'p1', 'p2', 'nbTurn', 'nbMoves', 'winner'];

  // scenarios = [{name:'Standard', path:'../assets/standard.png'},{name:'EAU', path:'../assets/eau.png'},{name:'Random', path:'../assets/random.png'}]
  scenarios = ['Standard', 'EAU', 'Random'];
  scenarioImages = {Standard:'../assets/standard.png', EAU:'../assets/eau.png', Random:'../assets/random.png'};


  constructor(private router: Router, private data: MyData) {
  }

  ngOnInit() {
    this.name1 = 'Tangi';
    this.name2 = 'Taha';
    this.scenario = 'Standard';
    this.human = false;
    this.difficulty = 'NOOB';

    console.log(this.scenario);
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

  changeImage(changes:any){
    console.log(changes);
  }


}
