package br.edu.utfpr.pb.posjava4api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.edu.utfpr.pb.posjava4api.model.Serie;
import br.edu.utfpr.pb.posjava4api.repository.SerieRepository;
import br.edu.utfpr.pb.posjava4api.service.SerieService;

@Service
public class SerieServiceImpl 
			extends CrudServiceImpl<Serie, Long>
			implements SerieService{

	@Autowired
	private SerieRepository serieRepository;
	
	@Override
	protected JpaRepository<Serie, Long> getRepository() {
		return serieRepository;
	}

	@Override
	public Page<Serie> findByNomeLikeOrResumoLike(String nome, String resumo, Pageable pageable) {
		
		return serieRepository.findByNomeLikeOrResumoLike(nome, resumo, pageable);
	}
	
	@Override
	public long countByNomeLikeOrResumoLike(String nome, String resumo) {
		return serieRepository.countByNomeLikeOrResumoLike(nome, resumo);
	}
}
