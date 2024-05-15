import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TechinalComponent } from './techinal.component';

describe('TechinalComponent', () => {
  let component: TechinalComponent;
  let fixture: ComponentFixture<TechinalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TechinalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TechinalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
