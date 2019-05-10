package br.edu.utfpr.pb.posjava4api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.pb.posjava4api.model.Serie;

public interface SerieService 
		extends CrudService<Serie, Long>{

	Page<Serie> findByNomeLikeOrResumoLike(String nome, String resumo, Pageable pageable);

	long countByNomeLikeOrResumoLike(String nome, String resumo);
}
