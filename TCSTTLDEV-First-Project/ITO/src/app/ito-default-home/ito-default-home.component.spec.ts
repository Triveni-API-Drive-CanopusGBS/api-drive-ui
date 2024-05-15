import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoDefaultHomeComponent } from './ito-default-home.component';

describe('ItoDefaultHomeComponent', () => {
  let component: ItoDefaultHomeComponent;
  let fixture: ComponentFixture<ItoDefaultHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoDefaultHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoDefaultHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should have', () => {
    expect(component.user).toEqual('userDetail');
  });
});
