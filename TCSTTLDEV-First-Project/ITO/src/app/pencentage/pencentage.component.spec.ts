import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PencentageComponent } from './pencentage.component';

describe('PencentageComponent', () => {
  let component: PencentageComponent;
  let fixture: ComponentFixture<PencentageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PencentageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PencentageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
