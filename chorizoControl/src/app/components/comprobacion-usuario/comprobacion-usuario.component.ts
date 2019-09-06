import { LoginService } from 'src/app/services/login.service';
// import { MatDialogRef } from '@angular/material/dialog';
// import { Component, OnInit } from '@angular/core';
// import { interval, timer } from 'rxjs';

// @Component({
//   selector: 'app-comprobacion-usuario',
//   templateUrl: './comprobacion-usuario.component.html',
//   styleUrls: ['./comprobacion-usuario.component.css']
// })
// export class ComprobacionUsuarioComponent implements OnInit {
//   second = 0;
//   secondString = '00';
//   minute = 5;
//   constructor(private dialogRef: MatDialogRef<ComprobacionUsuarioComponent>) {
//     interval(1000).subscribe((res) => {
//       this.second += 1;
//       this.secondString = this.second.toString();
//       if(this.second<10){
//         this.secondString ='0'+this.second.toString()
//       }
//       if (this.second === 60) {
//         this.second = 0;
//         this.secondString = '00';
//         this.minute ++;

//       }
//     }
// );
//     // timer(1000, 1000).subscribe(res => console.log(res));
//    }

//   ngOnInit() {
//   }
//   onNoClick(): void {
//     this.dialogRef.close();

//   }

// }
import { MatDialogRef } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { interval, timer } from 'rxjs';

@Component({
  selector: 'app-comprobacion-usuario',
  templateUrl: './comprobacion-usuario.component.html',
  styleUrls: ['./comprobacion-usuario.component.css']
})
export class ComprobacionUsuarioComponent implements OnInit {
  second = 59;
  secondString = '59';
  minute: number = 0;
  finCuenta = false;

  constructor(private dialogRef: MatDialogRef<ComprobacionUsuarioComponent>, private loginService: LoginService) {
    const temporizador = document.getElementById('temporizador');
    interval(1000).subscribe((res) => {
      if (this.finCuenta === false) {
        this.second--;

        this.secondString = this.second.toString();
        if (this.second < 10) {
          this.secondString = '0' + this.second.toString();
          if (this.second === 0 && this.minute > 0) {
            this.minute--;
            this.second = 59;
            this.secondString = '59';
          }
        }

        if (this.second === 0 && this.minute === 0) {
          this.finCuenta = true;
          console.log('final');
        }
      }
      else {
        // temporizador.style.color = 'red'
        this.second += 1;
        this.secondString = this.second.toString();
        if (this.second < 10) {
          this.secondString = '0' + this.second.toString();
        }
        if (this.second === 60) {
          this.second = 0;
          this.secondString = '00';
          this.minute++;

        }
      }
    }
    );
    // timer(1000, 1000).subscribe(res => console.log(res));
  }

  ngOnInit() {
    // this.minute = parseInt( localStorage.getItem('minute'));
    // this.secondString = localStorage.getItem('secondString')
  }
  onNoClick(): void {

    this.dialogRef.close();

  }

}
