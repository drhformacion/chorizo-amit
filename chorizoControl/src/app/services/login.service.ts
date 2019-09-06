import { Usuario } from './../usuario/usuario';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Registro } from '../usuario/registro';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private httpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
    // "Access-Control-Allow-Origin": "*"
  });
  private baseurl = 'http://192.168.1.77:8080';
  //   http://2.153.125.117:6969/usuario
  //   http://192.168.1.46:8080
  constructor(private http: HttpClient,) { }

  getLogin(u: Usuario)  {
    return this.http.post<boolean>(this.baseurl + "/login", u, { headers: this.httpHeaders })
  }

  getFichaje(nombreUsuario: string) {
    return this.http.post<number>(this.baseurl + "/inicio/"+ nombreUsuario, { headers: this.httpHeaders })

  }
  finFichaje(nombreUsuario: string) {
    return this.http.post<number>(this.baseurl + "/fin/"+ nombreUsuario, { headers: this.httpHeaders })
  }

  registroAdd(nombreUsuario: string, tipoEvento: string){
    return this.http.post<number>(this.baseurl + "/registroAdd/"+ nombreUsuario + '/' + tipoEvento, { headers: this.httpHeaders })
  }
  registroEnd(idEvento: string){
    return this.http.post<Registro>(this.baseurl + "/registroEnd/"+ idEvento, { headers: this.httpHeaders })
  }

  tiempoTotal(nombreUsuario: string){
    return this.http.post<number>(this.baseurl + "/tiempoTotal/"+ nombreUsuario, { headers: this.httpHeaders })
  }
  TiempoTotalDescanso(nombreUsuario: string){
    return this.http.post<[{}]>(this.baseurl + "/tiempoIndividual/"+ nombreUsuario, { headers: this.httpHeaders })
  }
  
}
