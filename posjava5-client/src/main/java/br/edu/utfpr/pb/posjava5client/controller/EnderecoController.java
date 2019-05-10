package br.edu.utfpr.pb.posjava5client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.edu.utfpr.pb.posjava5client.model.Endereco;
import br.edu.utfpr.pb.posjava5client.service.ViaCepService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ViaCepService viaCepClient;
	
	@GetMapping("{cep}")
	public Endereco getEnderecoByCep(@PathVariable String cep) {
		return viaCepClient.buscaEnderecoPor(cep);
		//restTemplate.getForObject("https://viacep.com.br/ws/{cep}/json",Endereco.class, cep); 
	}
}
