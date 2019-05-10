package br.edu.utfpr.pb.posjava5client.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.edu.utfpr.pb.posjava5client.model.Genero;
import br.edu.utfpr.pb.posjava5client.service.GeneroService;

@RestController
@RequestMapping("/genero")
public class GeneroController {

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoadBalancerClient loadClient;

	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/")
	@ResponseBody
	@HystrixCommand(fallbackMethod = "getGeneroFallback")
	public List<Genero> getGeneros() {
		Genero[] lista = restTemplate.getForObject( getUriBalanced("api-service") + "/genero", Genero[].class);
		List<Genero> generos = Arrays.asList(lista);
		return generos; //generoService.getGeneros();
	}

	public URI getUri(String service) {
		List<ServiceInstance> list = client.getInstances(service);

		if (list != null && list.size() > 0) {
			return list.get(0).getUri();
		}
		return null;
	}

	public URI getUriBalanced(String service) {
		ServiceInstance instance = loadClient.choose(service);
		return instance.getUri();
	}

	public List<Genero> getGeneroFallback() {
		List<Genero> lista = new ArrayList<>();

		Genero c = new Genero();
		c.setId(555L);
		c.setNome("Teste");
		lista.add(c);
		return lista;
	}

}
