import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menurelatorio',
  template: `
          <ul app-submenu [item]="items" target="_blank" root="true" id="items" class="navigation-menu" visible="true" parentActive="true"></ul>
          `
})
export class MenurelatorioComponent implements OnInit {
  public items: any[];
  constructor() { }
  qtd=0
  indicadores: any[];
  gerencias: any[];

  permissoes: any[] = [];
  usuPerformance: boolean = false;
  usuComissao: boolean = false;
  usuTransporte: boolean = false;

  ngOnInit() {

    //Preencehendo array de permissoes e liberando acessos
    let i =0
    while (sessionStorage.getItem("permissao "+ i) != null){
        let permissao = sessionStorage.getItem("permissao "+ i)
        this.permissoes.push(permissao)
        //Liberando acessos
        if(permissao === "ROLE_ADMIN"){
            this.usuPerformance = true
            this.usuComissao = true
            this.usuTransporte = true
        }else if(permissao === "ROLE_ADMIN_COMISSAO"){
            this.usuComissao = true
        }else if(permissao === "ROLE_ADMIN_FROTAS"){
            this.usuTransporte = true
        }else if(permissao === "ROLE_ADMIN_INDICADOR"){
            this.usuPerformance
             = true
        }
        
        i++
    }
  }


}
