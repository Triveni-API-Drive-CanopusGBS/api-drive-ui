import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdateConsultantComponent } from './ito-update-consultant.component';

describe('ItoUpdateConsultantComponent', () => {
  let component: ItoUpdateConsultantComponent;
  let fixture: ComponentFixture<ItoUpdateConsultantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdateConsultantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdateConsultantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
