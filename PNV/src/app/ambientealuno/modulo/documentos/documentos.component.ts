import { Component, OnInit, Input } from '@angular/core';
import { ModuloComponent } from '../modulo.component';

@Component({
  selector: 'app-documentos',
  templateUrl: './documentos.component.html',
  styleUrls: ['./documentos.component.css']
})
export class DocumentosComponent implements OnInit {

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
