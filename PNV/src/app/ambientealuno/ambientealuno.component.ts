import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ambientealuno',
  templateUrl: './ambientealuno.component.html',
  styleUrls: ['./ambientealuno.component.css']
})
export class AmbientealunoComponent implements OnInit {

  constructor() { }

  ModulosDisponiveis: any[]=[];
  ListaDeModulos: boolean = true

  ngOnInit() {
    this.ModulosDisponiveis=
    [
      {
        id: 1, modulo: "Módulo 01",  atividadespendentes:0,  aulaspendentes:0,  provaspendentes:0
      },
      {
        id: 2, modulo: "Módulo 02",  atividadespendentes:1,  aulaspendentes:1,  provaspendentes:1
      },
      {
        id: 3, modulo: "Módulo 03",  atividadespendentes:2,  aulaspendentes:2,  provaspendentes:2
      },
      {
        id: 4, modulo: "Módulo 04",  atividadespendentes:2,  aulaspendentes:2,  provaspendentes:2
      },
      {
        id: 5, modulo: "Módulo 05",  atividadespendentes:2,  aulaspendentes:2,  provaspendentes:2
      },
      {
        id: 6, modulo: "Módulo 06",  atividadespendentes:2,  aulaspendentes:2,  provaspendentes:2
      }
    ]
  }

  Modulo: boolean = false
  ModuloSelecionado: any
  acessarModulo(moduloselecionado){
    this.ListaDeModulos=false
    this.Modulo=true
    this.ModuloSelecionado = moduloselecionado
  }
  
}
