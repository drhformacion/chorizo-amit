import { LoginService } from './../../services/login.service';
import { MatDialogRef } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tiempo-excedido',
  templateUrl: './tiempo-excedido.component.html',
  styleUrls: ['./tiempo-excedido.component.css']
})
export class TiempoExcedidoComponent implements OnInit {

  tiempoTotal;
  constructor(private dialogRef: MatDialogRef<TiempoExcedidoComponent>, private loginService: LoginService ) { }
  displayedColumns: string[] = ['item', 'cost'];
  transactions: any[] = [
    {item: 'Beach ball', cost: 4},
    {item: 'Towel', cost: 5},
    {item: 'Frisbee', cost: 2},
    {item: 'Sunscreen', cost: 4},
    {item: 'Cooler', cost: 25},
    {item: 'Swim suit', cost: 15},
  ];

  descanso: any[] = [];

  ngOnInit() {
    this.loginService.tiempoTotal(localStorage.getItem('nombreUsuario')).subscribe(data => {
      this.tiempoTotal = data;
    });
    setTimeout(() => {
      this.loginService.TiempoTotalDescanso(localStorage.getItem('nombreUsuario')).subscribe((data: any) => {
        this.descanso = [
          {nombre: 'Baño', tiempoTotal: data.ttb, tiempoExcedido: data.teb},
          {nombre: 'Normal', tiempoTotal: data.ttn, tiempoExcedido: data.ten},
          {nombre: 'Reunion', tiempoTotal: data.ttr},
          {nombre: 'Comida', tiempoTotal: data.ttc, tiempoExcedido: data.tec},
        ];
        console.log(this.descanso)
      });
    }, 1000);

  }

  /** Gets the total cost of all transactions. */
  getTotalCost() {
    return this.transactions.map(t => t.cost).reduce((acc, value) => acc + value, 0);
  }
  onNoClick(): void {

    this.dialogRef.close();

  }


}
