import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { LoginService } from 'src/app/services/login.service';
import { Usuario } from 'src/app/usuario/usuario';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login: FormGroup;
  abierto = false;
  activar = false;
  docBody;
  count = 0;
  title = document.title;

  userActivity;
  userInactive: Subject<any> = new Subject();

  constructor(public dialog: MatDialog, private fb: FormBuilder, private loginService: LoginService,  private router: Router, private snackBar: MatSnackBar ) {
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

  onSubmit() {

    let usuario: Usuario = this.login.value;
    this.loginService.getLogin(usuario).subscribe(data => {
      
      if (data != false) {
        localStorage.setItem('nombreUsuario', this.login.value.nombreUsuario);
        // let snackBarRef = this.snackBar.open('Iniciando sesión...');
        this.router.navigate(['/home']);

      } else {
        const snackBarRef = this.snackBar.open('Usuario o contraseña incorrecta', 'cerrar', { duration: 3000 });

      }
    },
    (err) => {
      const snackBarRef = this.snackBar.open('Error en la base de datos', 'cerrar', { duration: 3000 });

    });


  }

}
