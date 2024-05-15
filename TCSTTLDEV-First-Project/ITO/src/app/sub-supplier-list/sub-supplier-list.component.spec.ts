import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubSupplierListComponent } from './sub-supplier-list.component';

describe('SubSupplierListComponent', () => {
  let component: SubSupplierListComponent;
  let fixture: ComponentFixture<SubSupplierListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubSupplierListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubSupplierListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
