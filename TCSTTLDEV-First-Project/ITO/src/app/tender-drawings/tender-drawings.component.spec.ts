import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TenderDrawingsComponent } from './tender-drawings.component';

describe('TenderDrawingsComponent', () => {
  let component: TenderDrawingsComponent;
  let fixture: ComponentFixture<TenderDrawingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TenderDrawingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TenderDrawingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
