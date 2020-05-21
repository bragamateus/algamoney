package com.example.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaAtual = pessoaRepository.buscar(codigo);

		if (pessoaAtual == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(pessoa, pessoaAtual, "codigo");
		return pessoaAtual = pessoaRepository.salvar(pessoaAtual);
	}
	
	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Pessoa pessoaAtual = pessoaRepository.buscar(codigo);
		if (pessoaAtual == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return pessoaAtual;
	}

}
