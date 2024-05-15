import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoCreateEndUserComponent } from './ito-create-end-user.component';

describe('ItoCreateEndUserComponent', () => {
  let component: ItoCreateEndUserComponent;
  let fixture: ComponentFixture<ItoCreateEndUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoCreateEndUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoCreateEndUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
