import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdateF2fAddonComponent } from './ito-update-f2f-addon.component';

describe('ItoUpdateF2fAddonComponent', () => {
  let component: ItoUpdateF2fAddonComponent;
  let fixture: ComponentFixture<ItoUpdateF2fAddonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdateF2fAddonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdateF2fAddonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
