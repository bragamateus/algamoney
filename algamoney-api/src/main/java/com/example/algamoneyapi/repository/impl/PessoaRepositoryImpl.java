package com.example.algamoneyapi.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;

@Repository
public class PessoaRepositoryImpl implements PessoaRepository {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Pessoa buscar(Long id) {
		return manager.find(Pessoa.class, id);
	}
	
	@Override
	public List<Pessoa> buscarTodos() {
		return manager.createQuery("from Pessoa", Pessoa.class).getResultList();
	}
	

	@Transactional
	@Override
	public Pessoa salvar(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return manager.merge(pessoa);
	}

	@Transactional
	@Override
	public void remover(Pessoa pessoa) {
		// TODO Auto-generated method stub
		pessoa = buscar(pessoa.getCodigo());
		manager.remove(pessoa);
		
	}


}
