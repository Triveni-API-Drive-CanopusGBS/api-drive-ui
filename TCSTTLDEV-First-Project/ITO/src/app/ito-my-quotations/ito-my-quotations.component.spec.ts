import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoMyQuotationsComponent } from './ito-my-quotations.component';

describe('ItoMyQuotationsComponent', () => {
  let component: ItoMyQuotationsComponent;
  let fixture: ComponentFixture<ItoMyQuotationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoMyQuotationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoMyQuotationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
