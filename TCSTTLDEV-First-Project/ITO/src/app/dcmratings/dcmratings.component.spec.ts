import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DcmratingsComponent } from './dcmratings.component';

describe('DcmratingsComponent', () => {
  let component: DcmratingsComponent;
  let fixture: ComponentFixture<DcmratingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DcmratingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DcmratingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
