import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  @ViewChild('f', { static: false })
  signupForm!: NgForm;
  resposta = '';
  generos = ['FEMININO', 'MASCULINO', 'OUTROS'];
  user = {
    dataNascimento: '',
    nacionalidade: '',
    naturalidade: '',
    nome: '',
    email: '',
    respostas: '',
    resposta: '',
    sexo: ''
  };
  submitted = false;

  // onSubmit(form: NgForm) {
  //   console.log(form);
  // }

  onSubmit() {
    this.submitted = true;
    this.user.dataNascimento = this.signupForm.value.userData.dataNascimento;
    this.user.nacionalidade = this.signupForm.value.userData.nacionalidade;
    this.user.naturalidade = this.signupForm.value.userData.naturalidade;
    this.user.nome = this.signupForm.value.userData.nome;
    this.user.sexo = this.signupForm.value.userData.sexo;
    this.user.email = this.signupForm.value.userData.email;
    this.user.resposta = this.signupForm.value.respostas;

    this.signupForm.reset();
  }
}
