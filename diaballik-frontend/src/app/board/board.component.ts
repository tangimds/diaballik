import {Component, OnInit, AfterViewInit, ViewChildren, QueryList, ElementRef} from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { MyData } from '../mydata';
import {CdkDragDrop, CdkDragEnter, CdkDragExit, CdkDragMove} from '@angular/cdk/drag-drop';
import {until} from 'selenium-webdriver';
import elementTextContains = until.elementTextContains;

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit, AfterViewInit {

  @ViewChildren('tiles')
  private tiles: QueryList<ElementRef>;

  @ViewChildren('pieces')
  private pieces: QueryList<ElementRef>;

  private list: any[];
  private passing: boolean;
  private moving: boolean;

  constructor(private http: HttpClient, private router: Router, private data: MyData) {
    this.list = [];
    this.passing = false;
    this.moving = false;
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    // this.tiles.forEach(tile => console.log(tile.nativeElement));
    // this.pieces.forEach(p => console.log(p.nativeElement));
  }

  public rightClick(event: MouseEvent): void {
    // this does not work (there is no backend in this example), but it shows how
    // to send an HTTP request (a PUT request here) to a backend server.
    // Note the `string text ${code}` used to build strings.
    // 'as' is used to cast.
    // 'getAttribute('data-x')' gets the value of the attribute data-x of the targeted div (see the HTML file).
    // https://angular.io/guide/http
    this.http.put(`game/myapi/${(event.currentTarget as Element).getAttribute('data-x')}`, {}, {}).
    // This is reactive programming + future object:
    // the request is sent. You can subscribe to (wait for) the returned data as follows:
    subscribe(returnedData => console.log(returnedData));
  }

  public playAI() {
    this.http.get(`game/IAPlay`, {}).
    subscribe(returnedData => {
      this.data.setStorage(returnedData);
      console.log(returnedData);
    }, error => console.log(error) // error path
    );
  }

  clickPiece(event: MouseEvent) {
    this.unselectAll();
    (event.currentTarget as Element).classList.add('selected');
    if (!this.passing) {
      this.list = [];
      this.list.push(event.currentTarget as Element);
      this.moving = true;
      console.log('MOVING' + this.moving);
    }
    if (this.passing) {
      this.list.push(event.currentTarget as Element);
      console.log('PAAAAAASS');
      this.http.put(`game/moveBall/${this.list[0].getAttribute('data-x')}/${this.list[0].getAttribute('data-y')}/${this.list[1].getAttribute('data-x')}/${this.list[1].getAttribute('data-y')}`, {}, {}).
      subscribe(returnedData => {
        this.data.setStorage(returnedData);
        this.list = [];
        this.passing = false;
        console.log(returnedData);
      }, error => {
        console.log(error);
        this.list = [];
        this.passing = false;
        alert('Pass unauthorized');

      });
    }
    console.log(this.list);
  }

  clickTile(event: MouseEvent) {
    this.unselectAll();
    if (this.moving) {
      this.list.push(event.currentTarget as Element);
      this.http.put(`game/movePiece/${this.list[0].getAttribute('data-x')}/${this.list[0].getAttribute('data-y')}/${this.list[1].getAttribute('data-x')}/${this.list[1].getAttribute('data-y')}`, {}, {}).
      subscribe(returnedData => {
        this.data.setStorage(returnedData);
        this.list = [];
        this.moving = false;
        console.log(returnedData);
      }, error => {
        console.log(error);
        this.list = [];
        this.moving = false;
        alert('Move unauthorized');
      });
    }
    console.log(this.list);
  }

  clickBall(event: MouseEvent) {
    this.unselectAll();
    (event.currentTarget as Element).classList.add('selected');
    if (!this.moving && !this.passing) {
      this.list.push(event.currentTarget as Element);
      this.passing = true;
    }
    console.log(this.list);
  }

  clearAll() {
    this.unselectAll();
    console.log('CLEAR');
    this.list = [];
    this.passing = false;
    this.moving = false;
    return false;
  }

  unselectAll() {
    let e: HTMLCollectionOf<Element>;
    e = document.getElementsByClassName('piece');
    for (const i of e) {
      i.classList.remove('selected');
      console.log(i);
    }
    e = document.getElementsByClassName('ball');
    for (const i of e) {
      i.classList.remove('selected');
      console.log(i);
    }
  }
  isSelected(e: Element) {
    // console.log('SELECTED');
    // console.log(e);
    this.list.forEach( el => {
      if (el === e) {
        // e.classList.add('selected');
        console.log(true);
        return true;
      } else {
        // e.classList.remove('selected');
        console.log(false);

        return false;
      }
    });
  }
}
