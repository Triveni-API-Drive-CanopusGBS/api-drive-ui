import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdDboEleAddonComponent } from './ito-upd-dbo-ele-addon.component';

describe('ItoUpdDboEleAddonComponent', () => {
  let component: ItoUpdDboEleAddonComponent;
  let fixture: ComponentFixture<ItoUpdDboEleAddonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdDboEleAddonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdDboEleAddonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
