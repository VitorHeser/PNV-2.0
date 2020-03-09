import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';
import { Usuario } from './login.model';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public usuario: Usuario = new Usuario();
  public permissao: string;
  public novasenha: string;
  public novasenhaconfirm: string;
  public senhapad: boolean =false;

  constructor(
    
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService
  ) { }

  ngOnInit() {

  }

  alterarsenha(){
    
    if(this.novasenha === this.novasenhaconfirm){
      var id
      this.authService.usuario(sessionStorage.getItem('email'))
      .subscribe(res=>{//console.log(res)
      this.authService.senhaUpdate(res['usuarioId'] , this.novasenha)
      .subscribe(
        response => {
          if(response === null){
            this.messageService.add({severity:'success', summary: 'Sucesso!', detail:'Senha alterada corretamente!!!', life: 5000});
            this.senhapad = false;
          }
        },
        error =>  { 
          this.messageService.add({severity:'error', summary: "Senha não alterada!", detail:error.message, life: 5000});
          console.log(error)
        }
      );
    });

      this.senhapad = false;
    }else{
      this.messageService.add({severity:'error', summary: "Senha não alterada!", detail:'As senhas digitadas não conferem!!!', life: 5000});
    } 
  }

  logar(){
    sessionStorage.clear
    this.authService.fazerLogin(this.usuario)
    .subscribe(
      resp => {
          const keys = resp.data.get('Authorization');
          localStorage.setItem('token', keys);
          this.authService.getToken(localStorage.getItem('token'));
          this.authService.userDados()
          //console.log(this.authService.dados) // Comentar essa linha depois de pronto o controle de acesso
          // Aqui embaixo implementar subida dos dados de permissão para SessionStorage
         
        // Faz o check se é primeiro acesso
        if(this.usuario.senha === '123'){
          this.messageService.add({severity:'error', summary: "Login não efetuado!", detail:'A senha é a padrão para primeiro acesso!!!', life: 5000});
          
          this.senhapad = true;
        }else{
          this.authService.checkAutenticado(resp.status);
          
          // Testando a construção em auth service
          this.router.navigate(['/']);  //precisa melhorar a permissões no menu
          
        }
      },
      error=>{
        if(error.status === 0){
          this.messageService.add({severity:'error', summary: "Login não efetuado!", detail: "Servidor Inoperante!" , life: 5000});
        }else{
        this.messageService.add({severity:'error', summary: "Login não efetuado!", detail:" error.error.message ", life: 5000});
        }
        console.log(error)
      }
    )
  }

}
