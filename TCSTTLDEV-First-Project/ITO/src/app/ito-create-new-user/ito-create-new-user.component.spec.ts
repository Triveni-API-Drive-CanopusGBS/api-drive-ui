import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoCreateNewUserComponent } from './ito-create-new-user.component';

describe('ItoCreateNewUserComponent', () => {
  let component: ItoCreateNewUserComponent;
  let fixture: ComponentFixture<ItoCreateNewUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoCreateNewUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoCreateNewUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
