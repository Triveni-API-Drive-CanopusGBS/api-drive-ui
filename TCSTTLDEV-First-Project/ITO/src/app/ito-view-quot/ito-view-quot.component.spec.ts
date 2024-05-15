import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoViewQuotComponent } from './ito-view-quot.component';

describe('ItoEditQuotComponent', () => {
  let component: ItoViewQuotComponent;
  let fixture: ComponentFixture<ItoViewQuotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoViewQuotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoViewQuotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
