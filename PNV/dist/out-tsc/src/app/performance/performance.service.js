import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { API_CONFIG } from '../app.api';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { ErrorHandler } from 'src/app/app.error-handler';
let PerformanceService = class PerformanceService {
    constructor(http) {
        this.http = http;
    }
    classindicadores(gerencia) {
        return this.http.get(`${API_CONFIG}/cadindicadores/gerencia/${gerencia}`)
            .pipe(map((res) => res, catchError(ErrorHandler.handleError)));
    }
    gerencias() {
        return this.http.get(`${API_CONFIG}/gerencias`)
            .pipe(map((res) => res, catchError(ErrorHandler.handleError)));
    }
    indicadores(referencia, indicadorId) {
        return this.http.get(`${API_CONFIG}/indicadores/grafico/${referencia}/${indicadorId}`)
            .pipe(map((res) => res, catchError(ErrorHandler.handleError)));
    }
    indicadoresByDay(indicador, referencia) {
        return this.http.get(`${API_CONFIG}/indicadores/${indicador}/${referencia}`)
            .pipe(map((res) => res, catchError(ErrorHandler.handleError)));
    }
    indicadoresAtt(exeindicadorId, datareferencia, dataindicador, ciclo, orcado, realizado, pdd, atendente, atendimento, coment, forecast, minimo, maximo, meta, previsao, dentroprazo, foraprazo, dentroprazoreg, foraprazoreg, acao, analise, colaborador, indicadorId, unidadeId) {
        const headers = new HttpHeaders()
            .set("Content-Type", "application/json");
        let bodyObj = {
            "exeindicadorId": exeindicadorId,
            "datareferencia": datareferencia,
            "dataindicador": dataindicador,
            "ciclo": ciclo,
            "periodicidade": "DIARIO",
            "orcado": orcado,
            "realizado": realizado,
            "pecld": pdd,
            "forecast": forecast,
            "minimo": minimo,
            "maximo": maximo,
            "meta": meta,
            "previsao": previsao,
            "dentroprazo": dentroprazo,
            "foraprazo": foraprazo,
            "dentroprazoreg": dentroprazoreg,
            "foraprazoreg": foraprazoreg,
            "atendente": atendente,
            "atendimento": atendimento,
            "comentario": coment,
            "analise": analise,
            "acao": acao,
            "colaborador": colaborador,
            "indicadorId": {
                "indicadorId": indicadorId
            },
            "undcodigo": {
                "unidadeId": unidadeId
            }
        };
        return this.http.put(`${API_CONFIG}/indicadores/${exeindicadorId}`, JSON.stringify(bodyObj), { headers })
            .pipe(map(this.extractData), catchError(ErrorHandler.handleError));
    }
    extractData(res) {
        let body = res;
        return body;
    }
};
PerformanceService = tslib_1.__decorate([
    Injectable(),
    tslib_1.__metadata("design:paramtypes", [HttpClient])
], PerformanceService);
export { PerformanceService };
//# sourceMappingURL=performance.service.js.map