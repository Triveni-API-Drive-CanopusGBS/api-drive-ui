import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrectionCommisionComponent } from './errection-commision.component';

describe('ErrectionCommisionComponent', () => {
  let component: ErrectionCommisionComponent;
  let fixture: ComponentFixture<ErrectionCommisionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ErrectionCommisionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ErrectionCommisionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
