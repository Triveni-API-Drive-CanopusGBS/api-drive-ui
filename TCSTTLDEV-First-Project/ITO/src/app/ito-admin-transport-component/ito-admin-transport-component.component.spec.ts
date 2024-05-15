import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoAdminTransportComponentComponent } from './ito-admin-transport-component.component';

describe('ItoAdminTransportComponentComponent', () => {
  let component: ItoAdminTransportComponentComponent;
  let fixture: ComponentFixture<ItoAdminTransportComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ItoAdminTransportComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoAdminTransportComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
