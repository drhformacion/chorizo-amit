import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComprobacionUsuarioComponent } from './comprobacion-usuario.component';

describe('ComprobacionUsuarioComponent', () => {
  let component: ComprobacionUsuarioComponent;
  let fixture: ComponentFixture<ComprobacionUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComprobacionUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComprobacionUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
