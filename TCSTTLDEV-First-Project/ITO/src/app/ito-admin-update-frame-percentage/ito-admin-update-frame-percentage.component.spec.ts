import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAdminUpdateFramePercentageComponent } from './ito-admin-update-frame-percentage.component';

describe('ItoAdminUpdateFramePercentageComponent', () => {
  let component: ItoAdminUpdateFramePercentageComponent;
  let fixture: ComponentFixture<ItoAdminUpdateFramePercentageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAdminUpdateFramePercentageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAdminUpdateFramePercentageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
