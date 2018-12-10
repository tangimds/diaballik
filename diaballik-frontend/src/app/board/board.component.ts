import {Component, OnInit, AfterViewInit, ViewChildren, QueryList, ElementRef} from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { MyData } from '../mydata';

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

  constructor(private http: HttpClient, private router: Router, private data: MyData) {
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

  public movePiece(x, y) {
    this.http.put(`game/newGamePVC/MENDES/STANDARD/NOOB`, {}, {}).
    subscribe(returnedData => console.log(returnedData));
  }

  public onDragStart(event) {
    console.log('START DRAG');
    event.preventDefault();
  }

  public onDrag(event) {
    console.log('DRAGGING');
    event.preventDefault();
  }

  public onDragEnd(event) {
    console.log('DRAG END');
    event.preventDefault();
  }

  public onClick(e) {
    console.log(e.target.attributes['data-x'].value);

  }

  public test() {
    this.http.put(`game/movePiece/1/1/1/2`, {}, {}).
    subscribe(returnedData => {
      this.data.setStorage(returnedData);
      console.log(returnedData);
    });

    this.http.put(`game/movePiece/1/2/1/3`, {}, {}).
    subscribe(returnedData => {
      this.data.setStorage(returnedData);
      console.log(returnedData);
    });

    this.http.put(`game/moveBall/4/1/7/1`, {}, {}).
    subscribe(returnedData => {
      this.data.setStorage(returnedData);
      console.log(returnedData);
    });
  }

  public playAI() {
    this.http.get(`game/IAPlay`, {}).
    subscribe(returnedData => {
      this.data.setStorage(returnedData);
      console.log(returnedData);
    });
  }

}
