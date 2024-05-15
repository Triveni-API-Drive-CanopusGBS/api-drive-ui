import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoUpdatePackgComponent } from './ito-update-packg.component';

describe('ItoUpdatePackgComponent', () => {
  let component: ItoUpdatePackgComponent;
  let fixture: ComponentFixture<ItoUpdatePackgComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoUpdatePackgComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoUpdatePackgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
