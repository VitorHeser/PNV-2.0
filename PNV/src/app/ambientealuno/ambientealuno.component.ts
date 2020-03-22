import { Component, OnInit } from '@angular/core';
import { AmbientealunoService } from './ambientealuno.service';
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-ambientealuno',
  templateUrl: './ambientealuno.component.html',
  styleUrls: ['./ambientealuno.component.css']
})
export class AmbientealunoComponent implements OnInit {

  constructor(private amb:AmbientealunoService, private auth: AuthService) { }

  ModulosDisponiveis: any[]=[];
  TurmasDisponiveis: any[]=[];

  ngOnInit() {
    this.TurmasDisponiveis = this.auth.turmas
  }
  
  ListaDeTurmas: boolean = true
  ListaDeModulos: boolean = false
  TurmaSelecionada: any

  aulasTotais:any[]=[]
  atividadesPendentes:any[]=[]
  provasPendentes:any[]=[]

  aulaspendentes:any[]=[]
  atividadespendentes:any[]=[]
  provaspendentes:any[]=[]

  acessarTurma(turmaselecionado){
    this.ListaDeTurmas=false
    this.ListaDeModulos=true
    this.TurmaSelecionada = turmaselecionado
    console.log(this.TurmaSelecionada)
    this.amb.turma(this.TurmaSelecionada.turmaId).subscribe(
      response=>{
        console.log(this.TurmaSelecionada)
        this.ModulosDisponiveis = response['modulos']


        for(var i =0; i<response['modulos'].length;i++){
          var modulo = response['modulos'][i]

          var aulaspend = 0;
          var atividadespen=0;
          var provapend = 0;

          //CONTAR ATIVIDADES TOTAL E PENDENTES
          for(var i =0; i<modulo["atividades"].length;i++){
            var atividade = modulo['atividades'][i]
            atividadespen = atividade.notaFinal ===null ? atividadespen + 1 : atividadespen
          }
          this.aulasTotais.push(modulo["atividades"].length)
          this.atividadespendentes.push(atividadespen)
          //=======================================================================================


        }
        console.log(this.atividadespendentes)
        
      }
    )

  }




  Modulo: boolean = false
  ModuloSelecionado: any
  acessarModulo(moduloselecionado){
    console.log(this.ModuloSelecionado)
    this.ListaDeModulos=false
    this.Modulo=true
    this.ModuloSelecionado = moduloselecionado
  }

  voltar(){
    this.ListaDeTurmas=true
    this.ListaDeModulos=false
    this.Modulo=false
    this.ModuloSelecionado = null

  }
  
}
