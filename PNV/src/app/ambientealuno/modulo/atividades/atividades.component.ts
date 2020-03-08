import { Component, OnInit, Input } from '@angular/core';
import { ModuloComponent } from '../modulo.component';

@Component({
  selector: 'app-atividades',
  templateUrl: './atividades.component.html',
  styleUrls: ['./atividades.component.css']
})
export class AtividadesComponent implements OnInit {

  modulo = this.amb.modulo;
  constructor(private amb: ModuloComponent) { }
  voltar(){
    this.amb.visualizarAmbienteModulo=true;
    this.amb.visualizarAulas=false;
    this.amb.visualizarAtividades=false;
    this.amb.visualizarProvas=false;
    this.amb.visualizarDocumentos=false;
  }
  

  atividades: any[]
  ngOnInit() {
    this.atividades=
    [
      { id:1, atividade: "Projeção Vocal", status: 1, avaliacao: 100, vencimento: '09/03/2020'},
      { id:2, atividade: "Tríade", status: 1, avaliacao: 50, vencimento: '16/03/2020'},
      { id:3, atividade: "Dicção", status: 0, avaliacao: 0, vencimento: '23/03/2020'},
      { id:4, atividade: "Melismas", status: 0, avaliacao: 0, vencimento: '30/03/2020'}
    ]
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
