import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdDboEleColComponent } from './ito-upd-dbo-ele-col.component';

describe('ItoUpdDboEleColComponent', () => {
  let component: ItoUpdDboEleColComponent;
  let fixture: ComponentFixture<ItoUpdDboEleColComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdDboEleColComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdDboEleColComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
