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
  acessarTurma(turmaselecionado){
    this.ListaDeTurmas=false
    this.ListaDeModulos=true
    this.TurmaSelecionada = turmaselecionado
    this.ModulosDisponiveis = turmaselecionado.modulos
  }




  Modulo: boolean = false
  ModuloSelecionado: any
  acessarModulo(moduloselecionado){
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
