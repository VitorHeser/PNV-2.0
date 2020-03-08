import { Component, OnInit, Input } from '@angular/core';
import { ModuloComponent } from '../modulo.component';
import { AmbientealunoService } from '../../ambientealuno.service';

@Component({
  selector: 'app-atividades',
  templateUrl: './atividades.component.html',
  styleUrls: ['./atividades.component.css']
})
export class AtividadesComponent implements OnInit {

  modulo = this.amb.modulo;
  constructor(private serv:AmbientealunoService, private amb: ModuloComponent) { }
  voltar(){
    this.amb.visualizarAmbienteModulo=true;
    this.amb.visualizarAulas=false;
    this.amb.visualizarAtividades=false;
    this.amb.visualizarProvas=false;
    this.amb.visualizarDocumentos=false;
  }
  

  atividades: any[]
  ngOnInit() {

    
  }


  //PDIALOG DE ATIVIDADES
  //==============================================================
  atividadeselecionada:boolean = false
  AtividadeSelect:any
  iniciarAtividade(Atividade){
    this.atividadeselecionada=true
    this.AtividadeSelect = Atividade
  }
  aoFechar(){
    this.atividadeselecionada=false
    this.AtividadeSelect = null

  }
}
