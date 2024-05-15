import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoNewVariantUpdatesComponent } from './ito-new-variant-updates.component';

describe('ItoNewVariantUpdatesComponent', () => {
  let component: ItoNewVariantUpdatesComponent;
  let fixture: ComponentFixture<ItoNewVariantUpdatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoNewVariantUpdatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoNewVariantUpdatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
