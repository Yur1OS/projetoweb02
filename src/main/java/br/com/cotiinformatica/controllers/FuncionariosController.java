package br.com.cotiinformatica.controllers;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.contiinformatica.dtos.PostFuncionarioDTO;
import br.com.contiinformatica.dtos.PutFuncionarioDTO;
import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.repositories.FuncionarioRepository;
import io.swagger.annotations.ApiOperation;

@Controller
public class FuncionariosController {

	@ApiOperation("Serviço para criação de funcionários")
	@RequestMapping(value = "/api/funcionarios", method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody PostFuncionarioDTO dto) {

		try {

			Funcionario funcionario = new Funcionario();
			funcionario.setEmpresa(new Empresa());

			funcionario.setNome(dto.getNome());
			funcionario.setCpf(dto.getCpf());
			funcionario.setMatricula(dto.getMatricula());
			funcionario.setDataAdmissao(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataAdmissao()));
			funcionario.getEmpresa().setIdEmpresa(dto.getIdEmpresa());

			FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
			funcionarioRepository.create(funcionario);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro: " + e.getMessage());
		}
		return null;
	}

	@ApiOperation("Serviço para Atualização de funcionários")
	@RequestMapping(value = "/api/funcionarios", method = RequestMethod.PUT)
	public ResponseEntity<String> put(@RequestBody PutFuncionarioDTO dto) {

		try {

			Funcionario funcionario = new Funcionario();
			funcionario.setEmpresa(new Empresa());

			funcionario.setIdFuncionario(dto.getIdFuncionario());
			funcionario.setNome(dto.getNome());
			funcionario.setCpf(dto.getCpf());
			funcionario.setMatricula(dto.getMatricula());
			funcionario.setDataAdmissao(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataAdmissao()));
			funcionario.getEmpresa().setIdEmpresa(dto.getIdEmpresa());

			FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
			funcionarioRepository.update(funcionario);

			return ResponseEntity.status(HttpStatus.OK).body("Funcionário Atualizado com sucesso !!!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro: " + e.getMessage());
		}
	}

	@RequestMapping(value = "/api/funcionarios/{idFuncionario}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("idFuncionario") Integer idFuncionario) {

		try {

			FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
			funcionarioRepository.delete(idFuncionario);

			return ResponseEntity.status(HttpStatus.OK).body("Funcionário excluído com sucesso !!!");

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro: " + e.getMessage());

		}

	}

	@ApiOperation("Serviço para consultar todos os Funcionários")
	@RequestMapping(value = "/api/funcionarios", method = RequestMethod.GET)
	public ResponseEntity<List<Funcionario>> getAll() {

		try {

			FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
			List<Funcionario> funcionarios = funcionarioRepository.findAll();

			return ResponseEntity.status(HttpStatus.OK).body(funcionarios);

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}

	@ApiOperation("Serviço de consulta pra 1 funcionario")
	@RequestMapping(value = "/api/funcionarios/{idFuncionario}", method = RequestMethod.GET)
	public ResponseEntity<Funcionario> getById(@PathVariable("idFuncionario") Integer idFuncionario) {

		try {

			FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
			Funcionario funcionario = funcionarioRepository.findById(idFuncionario);

			return ResponseEntity.status(HttpStatus.OK).body(funcionario);

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}

	}

}
