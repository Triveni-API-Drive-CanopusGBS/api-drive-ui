import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoEndUserDetailsComponent } from './ito-end-user-details.component';

describe('ItoEndUserDetailsComponent', () => {
  let component: ItoEndUserDetailsComponent;
  let fixture: ComponentFixture<ItoEndUserDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoEndUserDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoEndUserDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
