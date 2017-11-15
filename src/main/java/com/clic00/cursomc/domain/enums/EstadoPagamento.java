package com.clic00.cursomc.domain.enums;

public enum EstadoPagamento {
	CANCELADO(1, "Cancelado"), PENDENTE(2, "Pendente"), QUITADO(3, "Quitado");

	private int codigo;
	private String descricao;

	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Código: " + codigo + " inválido!");
	}
}
