import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TenderDrawingNewComponent } from './tender-drawing-new.component';

describe('TenderDrawingNewComponent', () => {
  let component: TenderDrawingNewComponent;
  let fixture: ComponentFixture<TenderDrawingNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TenderDrawingNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TenderDrawingNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
