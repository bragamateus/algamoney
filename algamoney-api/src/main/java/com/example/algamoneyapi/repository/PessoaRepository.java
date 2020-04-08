package com.example.algamoneyapi.repository;

import java.util.List;

import com.example.algamoneyapi.model.Pessoa;

public interface PessoaRepository{

	List<Pessoa> buscarTodos();
	
	Pessoa buscar(Long id);
	
	Pessoa salvar(Pessoa pessoa);
	
	void remover(Pessoa pessoa);
}
