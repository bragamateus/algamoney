import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs';
@Injectable({
    providedIn: 'root'
})
export class LancamentoService {

    constructor(private http: HttpClient) { }

    lancamentosUrl = 'http://localhost:8000/lancamentos';

    pesquisar(): Observable<any> {
        return this.http.get(`${this.lancamentosUrl}?resumo`);
    }
}
