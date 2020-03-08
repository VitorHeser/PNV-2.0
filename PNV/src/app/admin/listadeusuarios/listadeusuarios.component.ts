import { Component, OnInit } from '@angular/core';
import { Usuarios, Permissoes } from "../usuarios.model";
import { AdminService } from './../Admin.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-listadeusuarios',
  templateUrl: './listadeusuarios.component.html',
  styleUrls: ['./listadeusuarios.component.css']
})
export class ListadeusuariosComponent implements OnInit {

  constructor(private adminserv: AdminService, private messageService: MessageService) {}

  usuarios:Usuarios[]; 
  usuariosCompleto:Usuarios[]; 
  gerencias:any[]; 
  supervisoes:any[]; 
  unidades:any[]; 

  UsuarioSelect: Usuarios;
  EditUsuario: boolean = false;
  
  PermissoesSelect: Usuarios;
  UsuarioEditarPermissoes: any;
  EditPermissoes: boolean = false;

  sourcePermissoes: Permissoes[];
  targetPermissoes: Permissoes[];

  ngOnInit() {
    this.adminserv.listgerencias().subscribe(
      response =>{
        this.gerencias=response
      }
    )
    this.adminserv.listsupervisoes().subscribe(
      response =>{
        this.supervisoes = response
      }
    )
    this.adminserv.listunidades().subscribe(
      response =>{
        this.unidades = response
      }
    )

    this.atualizarlista();
  }
  selecionar(usuario){
    this.UsuarioSelect=usuario
    this.EditUsuario = true
  }


  //====================================================================================

  resetarSenha(usuario: Usuarios){
    this.adminserv.resetSenha(usuario).subscribe(
      response => {
        if(response === null){
          this.messageService.add({sticky: true, severity:'success', summary: 'Dados Salvos!', 
          detail:'Senha '+usuario.nome+ ' foi resetada!'});
        }
      },
      error =>  { 
        this.messageService.add({severity:'error', summary: "Dados não Enviados!", detail: error.message, life: 500});
      }
    );
    this.EditUsuario = false
    this.UsuarioSelect=null
  }

  //====================================================================================

  updateUser(usuario: Usuarios){
    this.adminserv.updateUsers(usuario).subscribe(
      response => {
        if(response === null){
          this.messageService.add({sticky: true, severity:'success', summary: 'Dados Salvos!', 
          detail:'Usuário '+usuario.nome+ ' foi alterado com sucesso!'});
        }
      },
      error =>  { 
        this.messageService.add({severity:'error', summary: "Dados não Enviados!", detail: error.message, life: 500});
      }
    );
    this.EditUsuario = false
    this.UsuarioSelect=null
  }

  //====================================================================================

  atribuirPermissoes(usuario: Usuarios){
    this.EditPermissoes = true;
    this.PermissoesSelect = usuario;
    this.adminserv.selecionarusuario(usuario).subscribe(
      response =>{
        this.UsuarioEditarPermissoes = response;
        this.targetPermissoes=response['perfis'];
        this.adminserv.listpermissoes().subscribe(
          response => {
            this.newarray(this.targetPermissoes,response);           
          }
        );
      }
    );
  }

  editarPermissoes(){
    var newpermissoes =[]
    for(var i=0; i<this.targetPermissoes.length;i++){
      newpermissoes.push(
        {
          perfilId: this.targetPermissoes[i].perfilId,
          perfil: this.targetPermissoes[i].perfil,
          permissao: this.targetPermissoes[i].permissao,
          descricao: this.targetPermissoes[i].descricao
        }
      )
    }
    var usuarioupdatePerms =
    {
      usuarioId: this.UsuarioEditarPermissoes.usuarioId,
      nome: this.UsuarioEditarPermissoes.nome,
      email: this.UsuarioEditarPermissoes.email,
      login: this.UsuarioEditarPermissoes.login,
      ativo: this.UsuarioEditarPermissoes.ativo,
      cargo: this.UsuarioEditarPermissoes.cargo,
      foto: this.UsuarioEditarPermissoes.foto,
      undcodigo: this.UsuarioEditarPermissoes.undcodigo,
      gerenciaId: this.UsuarioEditarPermissoes.gerenciaId,
      supervisaoId: this.UsuarioEditarPermissoes.supervisaoId,
      perfis: newpermissoes
    }
    this.adminserv.updateUser(usuarioupdatePerms).subscribe(
      response => {
        if(response === null){
          this.messageService.add({sticky: true, severity:'success', summary: 'Dados Salvos!', 
          detail:'Usuário '+usuarioupdatePerms.nome+ ' foi alterado com sucesso!'});
        }
      },
      error =>  { 
        this.messageService.add({severity:'error', summary: "Dados não Enviados!", detail: error.message, life: 500});
      }
    );
    this.UsuarioEditarPermissoes = null
    this.targetPermissoes = []
    this.sourcePermissoes = []
    this.EditPermissoes = false
    this.EditUsuario = false
    this.UsuarioSelect = null
    this.atualizarlista();
  }

  newarray(minhasperm: any[], todasperm:any[]){
    this.sourcePermissoes = todasperm
    
    for(var i = 0; i<minhasperm.length;i++){
      for(var j = 0; j<todasperm.length;j++){
        if(minhasperm[i].perfilId === todasperm[j].perfilId){
          this.sourcePermissoes.splice(j,1)
          break;
        }
      }
    }  
  }
  //====================================================================================
  VALOR1
  Filter(){
    this.VALOR1 = this.VALOR1==undefined ? null : this.VALOR1=="" ? null : this.VALOR1;
    var agend = this.usuarios;
    if(this.VALOR1!==null){
      // console.log(this.VALOR1)
      agend = agend.filter(item => item.nome.toUpperCase().includes(this.VALOR1.toUpperCase()))
    }else{
      this.atualizarlista();
    }

    this.usuarios = agend

  }
  
  atualizarlista(){
    this.usuarios=[]
    this.adminserv.listusers().subscribe(
      response =>{
        this.usuarios = response['content']
      }
    )
  }

  Mensagem(dado, perfil){
    this.messageService.add({sticky: true, severity:'info', summary: perfil, detail:dado, life: 500});
  }
}
