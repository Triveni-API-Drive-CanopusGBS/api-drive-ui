import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdateEndUserComponent } from './ito-update-end-user.component';

describe('ItoUpdateEndUserComponent', () => {
  let component: ItoUpdateEndUserComponent;
  let fixture: ComponentFixture<ItoUpdateEndUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdateEndUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdateEndUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
