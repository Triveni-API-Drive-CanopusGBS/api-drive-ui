import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTerminalMastComponent } from './admin-terminal-mast.component';

describe('AdminTerminalMastComponent', () => {
  let component: AdminTerminalMastComponent;
  let fixture: ComponentFixture<AdminTerminalMastComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminTerminalMastComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTerminalMastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
