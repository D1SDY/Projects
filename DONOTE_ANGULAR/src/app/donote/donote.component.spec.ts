import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonoteComponent } from './donote.component';

describe('DonoteComponent', () => {
  let component: DonoteComponent;
  let fixture: ComponentFixture<DonoteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonoteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
