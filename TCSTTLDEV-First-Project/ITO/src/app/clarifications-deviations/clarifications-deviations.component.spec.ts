import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClarificationsDeviationsComponent } from './clarifications-deviations.component';

describe('ClarificationsDeviationsComponent', () => {
  let component: ClarificationsDeviationsComponent;
  let fixture: ComponentFixture<ClarificationsDeviationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClarificationsDeviationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClarificationsDeviationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
