import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoConsultantHomeComponent } from './ito-consultant-home.component';

describe('ItoConsultantHomeComponent', () => {
  let component: ItoConsultantHomeComponent;
  let fixture: ComponentFixture<ItoConsultantHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoConsultantHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoConsultantHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
