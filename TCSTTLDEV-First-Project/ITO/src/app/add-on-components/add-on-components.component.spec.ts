import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AddOnComponentsComponent } from './add-on-components.component';

describe('AddOnComponentsComponent', () => {
  let component: AddOnComponentsComponent;
  let fixture: ComponentFixture<AddOnComponentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AddOnComponentsComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddOnComponentsComponent);
    this.component = fixture.debugElement.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
  it('should be', () => {
    this.component.changed();
    expect(component.lastMsg).toBe("okay");
  });
});
