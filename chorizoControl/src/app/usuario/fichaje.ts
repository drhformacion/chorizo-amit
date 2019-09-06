import { Usuario } from './usuario';
import { Registro } from './registro';

export class Fichaje {
    id: number;
    inicioFichaje: string;
    finFichaje: string;
    tiempoTotal: string;
    usuario: Usuario;
    registro:Set <Registro>;
    }
