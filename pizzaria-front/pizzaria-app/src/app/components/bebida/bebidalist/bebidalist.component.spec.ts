import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BebidasListComponent } from './bebidalist.component';

describe('BebidalistComponent', () => {
  let component: BebidasListComponent;
  let fixture: ComponentFixture<BebidasListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BebidasListComponent]
    });
    fixture = TestBed.createComponent(BebidasListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
