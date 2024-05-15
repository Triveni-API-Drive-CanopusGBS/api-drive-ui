import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoEditQuotComponent } from './ito-edit-quot.component';

describe('ItoEditQuotComponent', () => {
  let component: ItoEditQuotComponent;
  let fixture: ComponentFixture<ItoEditQuotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoEditQuotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoEditQuotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
