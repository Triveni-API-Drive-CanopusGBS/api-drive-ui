import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTransportExportComponent } from './update-transport-export.component';

describe('UpdateTransportExportComponent', () => {
  let component: UpdateTransportExportComponent;
  let fixture: ComponentFixture<UpdateTransportExportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateTransportExportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateTransportExportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
