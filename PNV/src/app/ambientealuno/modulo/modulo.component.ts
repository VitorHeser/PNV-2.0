import { Component, OnInit, Input } from '@angular/core';
import { AmbientealunoComponent } from '../ambientealuno.component'
import {CarouselModule} from 'primeng/carousel';

@Component({
  selector: 'app-modulo',
  templateUrl: './modulo.component.html',
  styleUrls: ['./modulo.component.css']
})
export class ModuloComponent implements OnInit {

  constructor(private amb: AmbientealunoComponent) { }

  @Input() modulo;
  opcoes: any

  visualizarAmbienteModulo=true

  ngOnInit() {
    this.opcoes = 
    [
      {id:1, label: "Aulas"},
      {id:2, label: "Atividades"},
      {id:3, label: "Provas"},
      {id:4, label: "Documentos"}
    ]
  }

  voltar(){
    this.amb.ListaDeModulos=true;
    this.amb.Modulo=false;
    this.amb.ModuloSelecionado=null;
  }

  visualizarAtividades:boolean =false
  visualizarAulas:boolean =false
  visualizarProvas:boolean =false
  visualizarDocumentos:boolean =false

  moduloSelecionado:any
  acessar(opcao){
    this.moduloSelecionado = opcao
    this.visualizarAmbienteModulo = false
    if(opcao['id']===1){
      this.visualizarAulas = true
    }else if(opcao['id']===2){
      this.visualizarAtividades = true
    }else if(opcao['id']===3){
      this.visualizarProvas = true
    }else if(opcao['id']===4){
      this.visualizarDocumentos = true
    }
  }
}
