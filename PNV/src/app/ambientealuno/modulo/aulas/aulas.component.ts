import { Component, OnInit, Input } from '@angular/core';
import { ModuloComponent } from '../modulo.component';

@Component({
  selector: 'app-aulas',
  templateUrl: './aulas.component.html',
  styleUrls: ['./aulas.component.css']
})
export class AulasComponent implements OnInit {

  modulo = this.amb.modulo;
  constructor(private amb: ModuloComponent) { }

  voltar(){
    this.amb.visualizarAmbienteModulo=true;
    this.amb.visualizarAulas=false;
    this.amb.visualizarAtividades=false;
    this.amb.visualizarProvas=false;
    this.amb.visualizarDocumentos=false;
  }

  ngOnInit() {
    console.log("INICIADO")
  }

}
