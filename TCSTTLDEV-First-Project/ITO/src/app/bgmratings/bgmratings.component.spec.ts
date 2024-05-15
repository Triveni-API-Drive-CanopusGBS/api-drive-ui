import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BgmratingsComponent } from './bgmratings.component';

describe('BgmratingsComponent', () => {
  let component: BgmratingsComponent;
  let fixture: ComponentFixture<BgmratingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BgmratingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BgmratingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
