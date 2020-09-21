import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VeiculoDetailsComponent } from './veiculo-details.component';

describe('VeiculoDetailsComponent', () => {
  let component: VeiculoDetailsComponent;
  let fixture: ComponentFixture<VeiculoDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VeiculoDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VeiculoDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
