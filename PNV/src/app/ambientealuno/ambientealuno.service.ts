import { Injectable } from '@angular/core';
import {API_CONFIG} from '../app.api'


import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { map, catchError } from 'rxjs/operators';
import { ErrorHandler } from 'src/app/app.error-handler';


@Injectable()
export class AmbientealunoService {
  
  constructor(private http: HttpClient){}

  cadastroBots(): Observable<any[]>{
     return  this.http.get(`${API_CONFIG}/alunos`) 
     .pipe(map((res : any[]) => res, catchError(ErrorHandler.handleError)))
  }

  
  turmas(){
    return this.http.get(`${API_CONFIG}/turmas`) 
    .pipe(map((res : any[]) => res, catchError(ErrorHandler.handleError)))
  }
  turma(i){
    return this.http.get(`${API_CONFIG}/turmas/${i}`) 
    .pipe(map((res : any[]) => res, catchError(ErrorHandler.handleError)))
 }
}