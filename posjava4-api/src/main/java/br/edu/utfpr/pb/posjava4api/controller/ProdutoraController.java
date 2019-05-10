package br.edu.utfpr.pb.posjava4api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.posjava4api.model.Produtora;
import br.edu.utfpr.pb.posjava4api.service.CrudService;
import br.edu.utfpr.pb.posjava4api.service.ProdutoraService;

@RestController
@RequestMapping("produtora")
public class ProdutoraController 
			extends CrudController<Produtora, Long>{

	@Autowired
	private ProdutoraService produtoraService;
	
	@Valid
	@Override
	protected CrudService<Produtora, Long> getService() {
		return produtoraService;
	}
	//http://localhost:8085/produtora/filter/nome?nome=y
	@GetMapping("filter/nome")
	public List<Produtora> findByNomeLike(
			@RequestParam String nome){
		return produtoraService
				.findByNomeLike("%" + nome + "%");
	}
	
}



