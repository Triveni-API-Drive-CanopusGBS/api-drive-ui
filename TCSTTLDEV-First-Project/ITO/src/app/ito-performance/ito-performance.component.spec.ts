import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoPerformanceComponent } from './ito-performance.component';

describe('ItoPerformanceComponent', () => {
  let component: ItoPerformanceComponent;
  let fixture: ComponentFixture<ItoPerformanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoPerformanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoPerformanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
