import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdateUserComponent } from './ito-update-user.component';

describe('ItoUpdateUserComponent', () => {
  let component: ItoUpdateUserComponent;
  let fixture: ComponentFixture<ItoUpdateUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdateUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdateUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
