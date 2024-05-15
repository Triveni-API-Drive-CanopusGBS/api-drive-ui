import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ItoHomePageComponent } from './ito-home-page.component';

describe('ItoHomePageComponent', () => {
  let component: ItoHomePageComponent;
  let fixture: ComponentFixture<ItoHomePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ItoHomePageComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ItoHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should have header `Latest Cost Estimation', async(() => {
    const fixture = TestBed.createComponent(ItoHomePageComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.h1).toEqual('Latest Cost Estimation');
  }));
});
