import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplyChainComponentComponent } from './supply-chain-component.component';

describe('SupplyChainComponentComponent', () => {
  let component: SupplyChainComponentComponent;
  let fixture: ComponentFixture<SupplyChainComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SupplyChainComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupplyChainComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
