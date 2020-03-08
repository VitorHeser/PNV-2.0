import { Component, OnInit, Input } from '@angular/core';
import { ModuloComponent } from '../modulo.component';

@Component({
  selector: 'app-provas',
  templateUrl: './provas.component.html',
  styleUrls: ['./provas.component.css']
})
export class ProvasComponent implements OnInit {

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
  }

}
