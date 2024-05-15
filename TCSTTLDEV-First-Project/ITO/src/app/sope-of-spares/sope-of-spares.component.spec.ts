import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SopeOfSparesComponent } from './sope-of-spares.component';

describe('SopeOfSparesComponent', () => {
  let component: SopeOfSparesComponent;
  let fixture: ComponentFixture<SopeOfSparesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SopeOfSparesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SopeOfSparesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
