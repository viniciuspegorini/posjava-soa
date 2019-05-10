package br.edu.utfpr.pb.posjava4api.service;

import java.util.List;

import br.edu.utfpr.pb.posjava4api.model.Produtora;

public interface ProdutoraService 
		extends CrudService<Produtora, Long>{

	
	List<Produtora> findByNomeLike(String nome);
}



