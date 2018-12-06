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

  constructor(private http: HttpClient, private router: Router, private data: MyData) {
    console.log(data);
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    // this.tiles.forEach(tile => console.log(tile.nativeElement));
  }
}
