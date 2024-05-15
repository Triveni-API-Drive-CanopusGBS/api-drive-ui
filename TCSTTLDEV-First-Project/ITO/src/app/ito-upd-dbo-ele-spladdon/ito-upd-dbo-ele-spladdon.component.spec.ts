import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdDboEleSpladdonComponent } from './ito-upd-dbo-ele-spladdon.component';

describe('ItoUpdDboEleSpladdonComponent', () => {
  let component: ItoUpdDboEleSpladdonComponent;
  let fixture: ComponentFixture<ItoUpdDboEleSpladdonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdDboEleSpladdonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdDboEleSpladdonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
