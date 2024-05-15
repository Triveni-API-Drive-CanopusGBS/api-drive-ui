import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PanelNamesComponent } from './panel-names.component';

describe('PanelNamesComponent', () => {
  let component: PanelNamesComponent;
  let fixture: ComponentFixture<PanelNamesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PanelNamesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PanelNamesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
