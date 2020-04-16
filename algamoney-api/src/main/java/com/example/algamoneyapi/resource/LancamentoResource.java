
package com.example.algamoneyapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@GetMapping
	public Page<Lancamento> listar(Pageable pageable) {
		Page<Lancamento> lancamentoPage = lancamentoRepository.findAll(pageable);
		
		List<Lancamento> lancamento = lancamentoPage.getContent();
		
		Page<Lancamento> lancamentoModelPage = new PageImpl<>(lancamento, pageable,
				lancamentoPage.getTotalElements());
		
		return lancamentoModelPage;
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(lancamentoSalvo.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(lancamentoSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPorId(@PathVariable Long codigo) {
		Optional<Lancamento> lancamento = lancamentoRepository.findById(codigo);
		if (!lancamento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lancamento.get());
	}
}
