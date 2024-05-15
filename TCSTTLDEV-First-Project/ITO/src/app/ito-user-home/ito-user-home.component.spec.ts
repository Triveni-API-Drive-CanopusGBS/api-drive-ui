import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUserHomeComponent } from './ito-user-home.component';

describe('ItoUserHomeComponent', () => {
  let component: ItoUserHomeComponent;
  let fixture: ComponentFixture<ItoUserHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUserHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUserHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
