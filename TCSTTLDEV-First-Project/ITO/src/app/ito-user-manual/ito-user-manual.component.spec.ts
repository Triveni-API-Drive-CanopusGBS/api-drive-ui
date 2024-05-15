import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUserManualComponent } from './ito-user-manual.component';

describe('ItoUserManualComponent', () => {
  let component: ItoUserManualComponent;
  let fixture: ComponentFixture<ItoUserManualComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUserManualComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUserManualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
