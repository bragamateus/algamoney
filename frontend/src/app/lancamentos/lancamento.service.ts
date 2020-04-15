import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import 'rxjs/add/operator/toPromise';
@Injectable({
  providedIn: 'root'
})
export class LancamentoService {

  constructor(private http: HttpClient) { }

  lancamentosUrl = 'http://localhost:8000/lancamentos';

  pesquisar(): Promise<any> {
    return this.http.get(`${this.lancamentosUrl}?resumo`)
    .toPromise()
    .then(response => {
        console.log(response);
    });
  }
}
