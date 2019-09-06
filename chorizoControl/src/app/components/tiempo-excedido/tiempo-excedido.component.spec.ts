import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TiempoExcedidoComponent } from './tiempo-excedido.component';

describe('TiempoExcedidoComponent', () => {
  let component: TiempoExcedidoComponent;
  let fixture: ComponentFixture<TiempoExcedidoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TiempoExcedidoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TiempoExcedidoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
