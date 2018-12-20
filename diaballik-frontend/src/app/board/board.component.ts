import {Component, OnInit, AfterViewInit, ViewChildren, QueryList, ElementRef} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {MyData} from '../mydata';
import {CdkDragDrop, CdkDragEnter, CdkDragExit, CdkDragMove} from '@angular/cdk/drag-drop';
import {until} from 'selenium-webdriver';
import elementTextContains = until.elementTextContains;

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit, AfterViewInit {

  /*
  global variables declaration
   */
  private list: any[];
  private passing: boolean;
  private moving: boolean;
  private currentSelection: any;
  private gameWon: boolean;
  private nbMoves: any;

  constructor(private http: HttpClient, private router: Router, private data: MyData) {
    this.list = [];
    this.passing = false;
    this.moving = false;

    if (!this.data.loaded) {
      this.nbMoves = 0;
      const urlTree = this.router.parseUrl(this.router.url);
      // PVC
      if (urlTree.queryParams.m === 'PVC') {
        const n1 = (urlTree.queryParams.n1).toUpperCase();
        const sce = (urlTree.queryParams.sce).toUpperCase();
        const d = (urlTree.queryParams.d).toUpperCase();
        this.http.put(`game/newGamePVC/${n1}/${sce}/${d}`, {}, {}).subscribe(returnedData => {
          console.log('init game : ');
          console.log(returnedData);
          this.data.setStorage(returnedData);
        });
      }

      // PVP
      if (urlTree.queryParams.m === 'PVP') {
        const n1 = (urlTree.queryParams.n1).toUpperCase();
        const n2 = (urlTree.queryParams.n2).toUpperCase();
        const sce = (urlTree.queryParams.sce).toUpperCase();
        this.http.put(`game/newGamePVP/${n1}/${n2}/${sce}`, {}, {}).subscribe(returnedData => {
          console.log('init game : ');
          console.log(returnedData);
          this.data.setStorage(returnedData);
        });
      }
    } else {
      this.nbMoves = this.data.storage.actions.length;
    }
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    this.changeIndicator();
    this.gameWon = this.data.storage.winner !== 'NONE';
    // console.log('afterviexinti');
    this.nbMoves = this.data.storage.actions.length;
    // console.log(this.data.storage);
    // console.log(this.gameWon);
  }

  /*
  the AI play its 3 moves
   */
  public playAI(delay) {
    // TODO : ajout indicateur 'is playing'
    setTimeout(() => {
      this.http.get(`game/IAPlay`, {})
        .subscribe(returnedData => {
            this.data.setStorage(returnedData);
            this.nbMoves++;
            console.log(returnedData);
            if (this.isWon()) {
              return;
            }
            setTimeout(() => {
              this.http.get(`game/IAPlay`, {})
                .subscribe(returnedData2 => {
                    this.data.setStorage(returnedData2);
                    this.nbMoves++;
                    console.log(returnedData2);
                    if (this.isWon()) {
                      return;
                    }
                    setTimeout(() => {
                      this.http.get(`game/IAPlay`, {})
                        .subscribe(returnedData3 => {
                            this.data.setStorage(returnedData3);
                            this.nbMoves++;
                            console.log(returnedData3);
                            this.changeIndicator();
                            if (this.isWon()) {
                              return;
                            }
                            }
                          , error => console.log(error) // error path
                        );
                    }, delay);
                  }, error => console.log(error) // error path
                );
            }, delay);
            }, error => console.log(error) // error path
        );
    }, delay);
  }

  /*
  called when there is a click on a piece
   */
  clickPiece(event: MouseEvent) {
    this.unselectAll();
    if (this.isWon()) {
      return;
    }
    (event.currentTarget as Element).classList.add('selected');
    if (!this.passing) {
      this.currentSelection = event.currentTarget as Element;
      this.moving = true;
    }
    if (this.passing) {
      const dest = event.currentTarget as Element;
      this.http.put(`game/moveBall/` +
        `${this.currentSelection.getAttribute('data-x')}/${this.currentSelection.getAttribute('data-y')}/` +
        `${dest.getAttribute('data-x')}/${dest.getAttribute('data-y')}`, {}, {}).subscribe(returnedData => {
        this.data.setStorage(returnedData);
        this.nbMoves++;
        this.currentSelection = null;
        this.passing = false;
        console.log(returnedData);
        this.changeIndicator();
      }, error => {
        console.log(error);
        this.currentSelection = null;
        this.passing = false;
        alert('Pass unauthorized');
      });
    }
  }

  /*
  called when there is a click on a tile
   */
  clickTile(event: MouseEvent) {
    this.unselectAll();
    if (this.isWon()) {
      return;
    }
    if (this.moving) {
      const dest = event.currentTarget as Element;
      this.http.put(`game/movePiece/` +
        `${this.currentSelection.getAttribute('data-x')}/${this.currentSelection.getAttribute('data-y')}/` +
        `${dest.getAttribute('data-x')}/${dest.getAttribute('data-y')}`, {}, {}).subscribe(returnedData => {
        this.data.setStorage(returnedData);
        this.currentSelection = null;
        this.moving = false;
        console.log(returnedData);
        this.nbMoves++;
        this.changeIndicator();
      }, error => {
        console.log(error);
        this.currentSelection = null;
        this.moving = false;
        alert('Move unauthorized');
      });
    }
  }

  /*
  called when there is a click on a ball
   */
  clickBall(event: MouseEvent) {
    this.unselectAll();
    if (this.isWon()) {
      return;
    }
    (event.currentTarget as Element).classList.add('selected');
    if (!this.moving && !this.passing) {
      this.currentSelection = event.currentTarget as Element;
      this.passing = true;
    }
  }

  /*
  deselect the piece or ball selected
  reset the variables
   */
  clearAll() {
    this.unselectAll();
    console.log('CLEAR');
    this.currentSelection = null;
    this.passing = false;
    this.moving = false;
    return false;
  }

  unselectAll() {
    const pieces = document.getElementsByClassName('piece');
    for (let i = 0; i < pieces.length; i++) {
      pieces[i].classList.remove('selected');
    }
    const balls = document.getElementsByClassName('ball');
    for (let i = 0; i < balls.length; i++) {
      balls[i].classList.remove('selected');
    }
  }

  changeIndicator() {
    const ind1 = document.getElementById('indicator1');
    const ind2 = document.getElementById('indicator2');

    const p1 = document.getElementById('p1');
    const p2 = document.getElementById('p2');
    console.log('IND');
    if ((this.data.storage.nbTurn % 2) === 0) {
      // pair
      p2.classList.remove('playing');
      p1.classList.add('playing');
    } else {
      // impair
      p1.classList.remove('playing');
      p2.classList.add('playing');
    }

    if ((this.data.storage.nbTurn % 2) === 1 && this.data.storage.p2.type === 'AIPlayer' && !this.isWon()) {
      this.playAI(1000);
    }
  }

  saveGame() {
    this.http.get(`game/save/`).
    subscribe(() => {
      console.log('saving game ' + this.data.storage.id);
      alert('Game ' + this.data.storage.id + ' saved !');
    });
  }

  goMenu() {
    this.data.storage = {};
    this.router.navigate(['menu']);
  }

  clickUndo() {
    console.log('UNDO');
    console.log(this.gameWon);
    if (!this.gameWon) {
      return;
    }
    this.http.get(`game/replay/undo`).
    subscribe((returnedData) => {
      console.log('undo ');
      this.data.setStorage(returnedData);
      console.log(returnedData);
      this.nbMoves--;
    });
  }

  clickRedo() {
    if (!this.gameWon) {
      return;
    }
    this.http.get(`game//replay/redo`).
    subscribe((returnedData) => {
      console.log('redo ');
      this.data.setStorage(returnedData);
      this.nbMoves++;
      console.log(returnedData);

    });
  }

  isWon() {
    if (this.data.storage.winner !== 'NONE' || this.gameWon) {
      this.gameWon = true;
      return true;
    } else {
      this.gameWon = false;
      return false;
    }
  }
}
