import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ElectricalHeaderFooterComponent } from './electrical-header-footer.component';

describe('ElectricalHeaderFooterComponent', () => {
  let component: ElectricalHeaderFooterComponent;
  let fixture: ComponentFixture<ElectricalHeaderFooterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ElectricalHeaderFooterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ElectricalHeaderFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
