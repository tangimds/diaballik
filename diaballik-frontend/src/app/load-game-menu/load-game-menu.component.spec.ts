import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadGameMenuComponent } from './load-game-menu.component';

describe('LoadGameMenuComponent', () => {
  let component: LoadGameMenuComponent;
  let fixture: ComponentFixture<LoadGameMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoadGameMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoadGameMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
