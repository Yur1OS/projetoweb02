package br.com.contiinformatica.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PutFuncionarioDTO {
	
	private Integer idFuncionario;
	private String nome;
	private String cpf;
	private String matricula;
	private String dataAdmissao;
	private Integer idEmpresa;

}
