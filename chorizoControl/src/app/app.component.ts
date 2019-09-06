import { Usuario } from './usuario/usuario';
import { LoginService } from './services/login.service';
import { Component, HostListener, Inject, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ComprobacionUsuarioComponent } from './components/comprobacion-usuario/comprobacion-usuario.component';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  login: FormGroup;
  abierto = false;
  activar = false;
  docBody;
  count = 0;
  title = document.title;

  userActivity;
  userInactive: Subject<any> = new Subject();

  constructor(public dialog: MatDialog, private fb: FormBuilder,private loginService: LoginService ) {
    const n = 0;


  }


  ngOnInit(): void {

    // this.docBody = document.getElementById('site-body');
    // this.docBody = this.changeTitle();

    //   var options = {
    //     body: "Este es le cuerpo de la notificación",

    // };

    // var notif = new Notification("Ejemplo de notificación", options);
    // setTimeout(notif.close, 1000);

    this.login = this.fb.group({

      nombreUsuario: ['', Validators.required],
      contrasenia: ['', Validators.required],


    });


  }

  onSubmit(){
    let usuario: Usuario = this.login.value;
    console.log(usuario);
    this.loginService.getLogin(usuario).subscribe(data => {
      console.log(data);

    })
  }
}



// this.setTimeout();
//   Notification.requestPermission().then(function(result) {

//       console.log(result);
//     });

//     // Let's check if the browser supports notifications
//   if (!('Notification' in window)) {
//       alert('This browser does not support desktop notification');
//     } else if (Notification.permission === 'granted') {
//       // If it's okay let's create a notification
//       let notification;
//       if (this.activar === false) {
//       this.userInactive.subscribe(() =>     this.openDialogSolicitar() );
//     }

//     } else if (Notification.permission !== 'denied') {
//       Notification.requestPermission().then(function(permission) {
//         // If the user accepts, let's create a notification
//         if (permission === 'granted') {
//           const notification = new Notification('Hi there!');
//         }
//       });
//     }


//   }
//    changeTitle() {
//     this.count ++;
//     const newTitle = '(' + this.count + ') ' + this.title;
//     document.title = newTitle;
//   }


// notificacion() {





//   }
// setTimeout() {
//     this.userActivity = setTimeout(() => this.userInactive.next(undefined), 3000);
//   }

// @HostListener('window:mousemove') refreshUserState() {
//     clearTimeout(this.userActivity);
//     this.setTimeout();


//   }
//   openDialogSolicitar(): void {

//    let dialogRef;
//    if (this.abierto === false) {
//       this.abierto = true;
//       const  notification = new Notification('Hiaaa there!');
//       this.changeTitle();
//       dialogRef = this.dialog.open(ComprobacionUsuarioComponent, {
//     });
//       dialogRef.afterClosed().subscribe(result => {
//       console.log('The dialog was closed');
//       this.abierto = false;
//     });
//   }