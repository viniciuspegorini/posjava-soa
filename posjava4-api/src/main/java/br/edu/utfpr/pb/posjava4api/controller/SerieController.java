package br.edu.utfpr.pb.posjava4api.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.utfpr.pb.posjava4api.model.Serie;
import br.edu.utfpr.pb.posjava4api.service.CrudService;
import br.edu.utfpr.pb.posjava4api.service.SerieService;

@RestController
@RequestMapping("serie")
public class SerieController 
			extends CrudController<Serie, Long>{

	@Autowired
	private SerieService serieService;
	
	@Valid
	@Override
	protected CrudService<Serie, Long> getService() {
		return serieService;
	}
	
	@GetMapping("search")
	public Page<Serie> findByNomeLike(
			@RequestParam String filter,
			@RequestParam int page,
			@RequestParam int size,
			@RequestParam(required = false) String order,
			@RequestParam(required = false) Boolean asc){
			PageRequest pageRequest = 
					PageRequest.of(page, size);
			if (order != null && asc != null) {
				PageRequest.of(page, size, 
						asc ? Sort.Direction.ASC :
							Sort.Direction.DESC, 
							order);
			}
		return serieService
				.findByNomeLikeOrResumoLike("%" + filter + "%", "%" + filter + "%", pageRequest);
	}
	
	@GetMapping("search/count")
	public long findByNomeLike(
			@RequestParam String filter) {
		
		return serieService
				.countByNomeLikeOrResumoLike("%" + filter + "%", "%" + filter + "%");
	}
	
	//UPLOAD
	@PostMapping("upload/{id}")
	public void upload(@PathVariable Long id,
		@RequestParam("imagem") MultipartFile imagem,
		HttpServletRequest request) throws Exception {
		
		if (imagem != null) {
			saveFile(id, imagem, request);
		}
	}
	
	private void saveFile(Long id, 
			MultipartFile imagem, 
			HttpServletRequest request) throws Exception {
		File dir = new File(
				request.getServletContext()
					   .getRealPath("/images/"));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		String caminhoAnexo = request
					.getServletContext()
				    .getRealPath("images/");
		String extensao = imagem.getOriginalFilename()
				.substring(
		imagem.getOriginalFilename().lastIndexOf("."),
		imagem.getOriginalFilename().length());
		String nomeArquivo = id + extensao;
		try {
			FileOutputStream fileOut = 
				new FileOutputStream(
				  new File(caminhoAnexo+nomeArquivo));
			BufferedOutputStream stream = 
				new BufferedOutputStream(fileOut);
			stream.write(imagem.getBytes());
			stream.close();
			
			Serie serie = getService().findOne(id);
			serie.setImagem(nomeArquivo);
			getService().save(serie);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao fazer"
					+ "upload da imagem. " + 
					e.getMessage());
		}
	}
}



