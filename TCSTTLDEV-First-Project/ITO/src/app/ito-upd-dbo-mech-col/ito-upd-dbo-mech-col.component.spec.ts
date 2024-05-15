import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdDboMechColComponent } from './ito-upd-dbo-mech-col.component';

describe('ItoUpdDboMechColComponent', () => {
  let component: ItoUpdDboMechColComponent;
  let fixture: ComponentFixture<ItoUpdDboMechColComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdDboMechColComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdDboMechColComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
