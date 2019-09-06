import { LoginService } from 'src/app/services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ComprobacionUsuarioComponent } from './../comprobacion-usuario/comprobacion-usuario.component';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit, HostListener, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { TiempoExcedidoComponent } from '../tiempo-excedido/tiempo-excedido.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  activarDescanso = false;
  activo = false;
  tiempo;
  activoBanio = false;
  activoNormal = false;
  activoReunion = false;
  activoComida = false;
  idNormal;
  activar = false;
  abierto = false;
  tiempoRandom;
  nombre: string;
  userInactive: Subject<any> = new Subject();
  userActivity;
  constructor(public dialog: MatDialog, private snackBar: MatSnackBar, private loginService: LoginService, 
              private authService: LoginService, private router: Router) {

  }


  ngOnInit() {

    if(localStorage.getItem('nombreUsuario') === null){
      this.router.navigate(['']);
    }
    else{
      this.comprobacionLocal();
      this.loginService.getFichaje(localStorage.getItem('nombreUsuario')).subscribe(data => {

      });
      // this.tiempoRandom = Math.random() * (600000 - 30*60*10000) + (360000);

      this.nombre = localStorage.getItem('nombreUsuario');

      this.setTimeout();
      Notification.requestPermission().then(function (result) {
  
      });
  
      // Let's check if the browser supports notifications
      if (!('Notification' in window)) {
        alert('This browser does not support desktop notification');
      } else if (Notification.permission === 'granted') {
        // If it's okay let's create a notification
        let notification;
        if (this.activar === false) {
          this.userInactive.subscribe(() => this.openDialogSolicitar());
        }
  
      } else if (Notification.permission !== 'denied') {
        Notification.requestPermission().then(function (permission) {
          // If the user accepts, let's create a notification
          if (permission === 'granted') {
            const notification = new Notification('Hi there!');
          }
        });
      }
    }
    
  }
  // changeTitle() {
  //      this.count ++;
  //      const newTitle = '(' + this.count + ') ' + this.title;
  //      document.title = newTitle;
  //    }


  comprobacionLocal() {
    if (localStorage.getItem('idBanio') != null) {
      this.descansoBanio();
    } else if (localStorage.getItem('idNormal') != null) {
      this.descansoNormal();
    } else if (localStorage.getItem('idReunion') != null) {
      this.descansoReunion();
    }else if (localStorage.getItem('idComida') != null) {
      this.descansoComida();
    }else if (localStorage.getItem('idInactivo') != null) {
      this.openDialogSolicitar();
    }
  }
  descansoBanio() {
    if (!this.activo || this.activoBanio) {
      this.activar = true;
      this.activo = true;
      this.activoBanio = true;
      
      
      const banio = document.getElementById('banio');
      if (this.activarDescanso === false) {
        if (localStorage.getItem('idBanio') === null) {
          this.loginService.registroAdd(localStorage.getItem('nombreUsuario'), 'banio').subscribe(data => {
            localStorage.setItem('idBanio', data.toString());
          });
        }

        banio.style.backgroundColor = 'lightblue';
        this.activarDescanso = true;
      } else {
        this.activar = false;
        banio.style.backgroundColor = 'white';
        this.activarDescanso = false;
        this.activo = false;
        this.activoBanio = false;
        this.loginService.registroEnd(localStorage.getItem('idBanio')).subscribe(data => {
          localStorage.removeItem('idBanio');
        });
      }

    } else {
      const snackBarRef = this.snackBar.open('No puedes tener activo mas de 1 tipo de descanso', 'Cerrar', { duration: 3000 });

    }
  }
  descansoNormal() {
    if (!this.activo || this.activoNormal) {
      this.activar = true;
      this.activo = true;
      this.activoNormal = true;
      const descanso = document.getElementById('normal');
      if (this.activarDescanso === false) {
        if (localStorage.getItem('idNormal') === null) {
          this.loginService.registroAdd(localStorage.getItem('nombreUsuario'), 'normal').subscribe(data => {
            localStorage.setItem('idNormal', data.toString());
          });
        }

        descanso.style.backgroundColor = 'gainsboro';
        this.activarDescanso = true;
      } else {
        descanso.style.backgroundColor = 'white';
        this.activarDescanso = false;
        this.activo = false;
        this.activoNormal = false;
        this.loginService.registroEnd(localStorage.getItem('idNormal')).subscribe(data => {
          localStorage.removeItem('idNormal');
          this.activar = false;
        });


      }

    } else {
      const snackBarRef = this.snackBar.open('No puedes tener activo mas de 1 tipo de descanso', 'Cerrar', { duration: 3000 });

    }
  }
  descansoComida() {
    if (!this.activo || this.activoComida) {
      this.activo = true;
      this.activoComida = true;
      this.activar = true;
      const descanso = document.getElementById('comida');
      if (this.activarDescanso === false) {
        if (localStorage.getItem('idComida') === null) {
          this.loginService.registroAdd(localStorage.getItem('nombreUsuario'), 'comida').subscribe(data => {
            localStorage.setItem('idComida', data.toString());
          });
        }
        descanso.style.backgroundColor = 'cadetblue';
        this.activarDescanso = true;
      } else {
        descanso.style.backgroundColor = 'white';
        this.activarDescanso = false;
        this.activo = false;
        this.activoComida = false;
        this.activar = false;
        this.loginService.registroEnd(localStorage.getItem('idComida')).subscribe(data => {
          localStorage.removeItem('idComida');
        });


      }

    } else {
      const snackBarRef = this.snackBar.open('No puedes tener activo mas de 1 tipo de descanso', 'Cerrar', { duration: 3000 });

    }
  }
  descansoReunion() {
    if (!this.activo || this.activoReunion) {
      this.activar = true;
      this.activo = true;
      this.activoReunion = true;
      const descanso = document.getElementById('reunion');
      if (this.activarDescanso === false) {
        if (localStorage.getItem('idReunion') === null) {
          this.loginService.registroAdd(localStorage.getItem('nombreUsuario'), 'reunion').subscribe(data => {
            localStorage.setItem('idReunion', data.toString());
          });
        }
        descanso.style.backgroundColor = 'burlywood';
        this.activarDescanso = true;
      } else {
        descanso.style.backgroundColor = 'white';
        this.activarDescanso = false;
        this.activo = false;
        this.activoNormal = false;
        this.activar = false;
        this.loginService.registroEnd(localStorage.getItem('idReunion')).subscribe(data => {
          localStorage.removeItem('idReunion');
        });


      }

    } else {
      const snackBarRef = this.snackBar.open('No puedes tener activo mas de 1 tipo de descanso', 'Cerrar', { duration: 3000 });

    }
  }

  setTimeout() {

    if(this.activar === false){
      this.tiempo =  Math.random()*(30*60*1000-10*60*1000+1)+(10*60*1000);
    this.userActivity = setTimeout(() => this.userInactive.next(undefined), this.tiempo);
  }}

  @HostListener('window:mousemove') refreshUserState() {

    clearTimeout(this.userActivity);
    this.setTimeout();
  }
  openDialogTiempoExcedido() {


    let dialogRef;

    dialogRef = this.dialog.open(TiempoExcedidoComponent, {
      height: 'auto',
      width: '40%',
    });
    dialogRef.afterClosed().subscribe(result => {

      this.abierto = false;
   
    });

  }
  openDialogSolicitar(): void {

    if (localStorage.getItem('idInactivo') === null) {
      this.loginService.registroAdd(localStorage.getItem('nombreUsuario'), 'inactivo').subscribe(data => {
        localStorage.setItem('idInactivo', data.toString());
      });
    }

    let dialogRef;
    if (this.abierto === false) {
      this.abierto = true;
      const notification = new Notification('Control de inactividad');

      dialogRef = this.dialog.open(ComprobacionUsuarioComponent, {
      });
      dialogRef.afterClosed().subscribe(result => {

        this.loginService.registroEnd(localStorage.getItem('idInactivo')).subscribe(data => {
            localStorage.removeItem('idInactivo');

        });
        this.abierto = false;
      });
    }
  }
  ngOnDestroy() {
    this.userInactive.unsubscribe();
  }
  cerrarSesion() {
    this.loginService.finFichaje(localStorage.getItem('nombreUsuario')).subscribe(data => {
      localStorage.removeItem('nombreUsuario');
      this.router.navigate(['']);
    });
  }
}
