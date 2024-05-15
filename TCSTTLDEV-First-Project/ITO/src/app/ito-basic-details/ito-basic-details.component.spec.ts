import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoBasicDetailsComponent } from './ito-basic-details.component';

describe('ItoBasicDetailsComponent', () => {
  let component: ItoBasicDetailsComponent;
  let fixture: ComponentFixture<ItoBasicDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoBasicDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoBasicDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
