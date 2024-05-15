import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdPriceDboEleAddinstrComponent } from './ito-upd-price-dbo-ele-addinstr.component';

describe('ItoUpdPriceDboEleAddinstrComponent', () => {
  let component: ItoUpdPriceDboEleAddinstrComponent;
  let fixture: ComponentFixture<ItoUpdPriceDboEleAddinstrComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdPriceDboEleAddinstrComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdPriceDboEleAddinstrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
