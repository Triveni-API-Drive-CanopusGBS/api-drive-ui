import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoQuotCompleteComponent } from './ito-quot-complete.component';

describe('ItoQuotCompleteComponent', () => {
  let component: ItoQuotCompleteComponent;
  let fixture: ComponentFixture<ItoQuotCompleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoQuotCompleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoQuotCompleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
