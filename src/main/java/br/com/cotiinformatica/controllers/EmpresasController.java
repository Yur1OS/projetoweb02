package br.com.cotiinformatica.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.contiinformatica.dtos.PostEmpresaDTO;
import br.com.contiinformatica.dtos.PutEmpresaDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.repositories.EmpresaRepository;
import io.swagger.annotations.ApiOperation;

@Controller
public class EmpresasController {

	@ApiOperation("Serviço para cadastro de empresas")
	@RequestMapping(value = "/api/empresas", method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody PostEmpresaDTO dto) {
		
		try {
			
			Empresa empresa = new Empresa();
			
			empresa.setNomeFantasia(dto.getNomeFantasia());
			empresa.setRazaoSocial(dto.getRazaoSocial());
			empresa.setCnpj(dto.getCnpj());
			
			EmpresaRepository empresaRepository = new EmpresaRepository();
			empresaRepository.create(empresa);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Empresa cadastrada com sucesso!");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Ocorreu um erro: " + e.getMessage());
		}
	}

	@ApiOperation("Serviço para atualização de empresas")
	@RequestMapping(value = "/api/empresas", method = RequestMethod.PUT)
	public ResponseEntity<String> put(@RequestBody PutEmpresaDTO dto) {
		
		try {
		
			Empresa empresa = new Empresa();
			
			empresa.setIdEmpresa(dto.getIdEmpresa());
			empresa.setNomeFantasia(dto.getNomeFantasia());
			empresa.setRazaoSocial(dto.getRazaoSocial());
			empresa.setCnpj(dto.getCnpj());
			
			EmpresaRepository empresaRepository = new EmpresaRepository();
			empresaRepository.update(empresa);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body("Empresa atualizada com sucesso.");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Ocorreu um erro: " + e.getMessage());
		}
	}

	@ApiOperation("Serviço para exclusão de empresas")
	@RequestMapping(value = "/api/empresas/{idEmpresa}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("idEmpresa") Integer idEmpresa) {
		
		try {
		
			EmpresaRepository empresaRepository = new EmpresaRepository();
			empresaRepository.delete(idEmpresa);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body("Empresa excluída com sucesso.");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Ocorreu um erro: " + e.getMessage());
		}		
	}

	@ApiOperation("Serviço para consultar todas as empresas")
	@RequestMapping(value = "/api/empresas", method = RequestMethod.GET)
	public ResponseEntity<List<Empresa>> getAll() {
		
		try {
			
			EmpresaRepository empresaRepository = new EmpresaRepository();
			List<Empresa> empresas = empresaRepository.findAll();
			
			return ResponseEntity.status(HttpStatus.OK).body(empresas);			
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@ApiOperation("Serviço para consulta de empresa por ID")
	@RequestMapping(value = "/api/empresas/{idEmpresa}", method = RequestMethod.GET)
	public ResponseEntity<Empresa> getById(@PathVariable("idEmpresa")Integer idEmpresa ){
		
		try {
			
			EmpresaRepository empresaRepository = new EmpresaRepository();
			Empresa empresa = empresaRepository.findById(idEmpresa);
			
			return ResponseEntity.status(HttpStatus.OK).body(empresa);
			
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}









