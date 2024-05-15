import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoEndUserHomeComponent } from './ito-end-user-home.component';

describe('ItoEndUserHomeComponent', () => {
  let component: ItoEndUserHomeComponent;
  let fixture: ComponentFixture<ItoEndUserHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoEndUserHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoEndUserHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
