import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TerminalPointsComponent } from './terminal-points.component';

describe('TerminalPointsComponent', () => {
  let component: TerminalPointsComponent;
  let fixture: ComponentFixture<TerminalPointsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TerminalPointsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TerminalPointsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
