import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAdminFramesPwrPriceComponent } from './ito-admin-frames-pwr-price.component';

describe('ItoAdminFramesPwrPriceComponent', () => {
  let component: ItoAdminFramesPwrPriceComponent;
  let fixture: ComponentFixture<ItoAdminFramesPwrPriceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAdminFramesPwrPriceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAdminFramesPwrPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
