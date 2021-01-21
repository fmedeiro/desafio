import { NgxMaskModule } from 'ngx-mask';
import { Component, ViewChild, OnInit, OnDestroy } from '@angular/core';
import { NgForm } from '@angular/forms';

import { DesafioService } from './desafio.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  ngOnDestroy() {
    this.clientes = [];
    this.cliente = {
      cpf: '',
      dataNascimento: '',
      nacionalidade: '',
      naturalidade: '',
      nome: '',
      email: '',
      sexo: 0
    };
  }

/*   listaTestes: Array<any> = [
    { nome: 'Ana' },
    { nome: 'Fernando' },
    ]; */

  clientes: Array<any>;
  clientes2: Array<any>;
  esconder = 1;
  i = 0;
  cpfMasked = '';

  constructor(private desafioService: DesafioService) { }

  ngBeforeOnInit() {
    //this.esconder = false;
    //this.listar();
  }

  ngOnInit() {
    //this.listar();
  }

  confirmarExclusaoTodos() {
    if (window.confirm("Tem certeza de que gostaria de deletar todos os registros?")) {
      this.desafioService.excluirTodosRegistros().subscribe();
      this.desafioService.listar().subscribe;
    }
  }

  cadastrar(cliente) {
    console.log("tinha q cadastrar o: " + cliente.cpf);
    this.desafioService.cadastrar(cliente)
    .subscribe(response => {this.cliente = response; console.log("teste 67 = " + this.cliente.cpf);});
  }

/*   confirmarExclusao(clienteDeletar: any) {
    if (window.confirm("Tem certeza de que gostaria de deletar o registro com nome: "+clienteDeletar.nome)) {
      this.excluirRegistro(clienteDeletar);
    }
  } */

/*   excluirRegistro(cliente) {
    this.desafioService.excluirRegistro(cliente).subscribe();
  } */

  listar() {
/*     this.clientes=<any>this.desafioService.listar()
      .subscribe(response => this.clientes = response); */
      this.esconder = 0;
      this.desafioService.listar()
      .subscribe(response => {this.clientes2 = response; this.printMy(this.clientes2); console.log("teste 0 = " + this.clientes2.length);});
  }

  irCadastrar() {
      this.esconder = 1;
  }

  printMy(clientes4: Array<any>) {
    this.clientes = clientes4;
    console.log("teste 1 = " + this.clientes)
  }

  @ViewChild('f', { static: false })
  signupForm!: NgForm;
  generos = ['FEMININO', 'MASCULINO', 'OUTROS'];
  cliente = {
    cpf: '',
    dataNascimento: '',
    nacionalidade: '',
    naturalidade: '',
    nome: '',
    email: '',
    sexo: 0
  };
  submitted = false;

  formatAndSetCpf(cpfUnmasked: any) {
    cpfUnmasked=cpfUnmasked.replace(/\D/g,"");
    cpfUnmasked=cpfUnmasked.replace(/(\d{3})(\d)/,"$1.$2");
    cpfUnmasked=cpfUnmasked.replace(/(\d{3})(\d)/,"$1.$2");
    cpfUnmasked=cpfUnmasked.replace(/(\d{3})(\d{1,2})$/,"$1-$2");
    this.cliente.cpf = cpfUnmasked;
    cpfUnmasked = '';
  }

  onSubmit() {
    this.submitted = true;
    this.formatAndSetCpf(this.signupForm.value.userData.cpf);
    this.cliente.dataNascimento = this.signupForm.value.userData.dataNascimento.concat("T00:00:00");
    this.cliente.nacionalidade = this.signupForm.value.userData.nacionalidade;
    this.cliente.naturalidade = this.signupForm.value.userData.naturalidade;
    this.cliente.nome = this.signupForm.value.userData.nome;
    this.cliente.sexo = this.signupForm.value.userData.sexo === 'FEMININO' ? 0 : this.signupForm.value.userData.sexo === 'MASCULINO' ? 1 : 2;
    this.cliente.email = this.signupForm.value.userData.email;

    console.log(this.cliente);

    this.cadastrar(this.cliente);
    console.log("teste teste = " + this.clientes);

    this.signupForm.reset();
    this.listar();
  }
}
