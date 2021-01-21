import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { DesafioService } from '../desafio.service';

@Component({
  selector: 'app-detalhes',
  templateUrl: './detalhes.component.html',
  styleUrls: ['./detalhes.component.css']
})

export class DetalhesComponent implements OnInit, OnDestroy {

ngOnDestroy() {
  this.clientes = [];
}

  @Input() clientes: Array<any>;

  constructor(private desafioService: DesafioService) { }

  cadastrar(cliente) {
    console.log("tinha q cadastrar o: " + cliente.cpf);
    this.desafioService.cadastrar(cliente)
    .subscribe(response => {cliente = response; console.log("teste 66 = " + cliente.cpf);});
  }

  confirmarExclusao(clienteDeletar: any) {
    if (window.confirm("Tem certeza de que gostaria de deletar o registro com nome: "+clienteDeletar.nome+", CPF: "+clienteDeletar.cpf)) {
      this.desafioService.excluirRegistro(clienteDeletar).subscribe();
      this.desafioService.listar().subscribe;
    }
  }

  ngOnInit(): void {
  }

}
