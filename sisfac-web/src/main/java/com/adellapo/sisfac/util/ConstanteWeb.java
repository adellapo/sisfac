package com.adellapo.sisfac.util;

public enum ConstanteWeb {

	EMITIDO("1"), ELIMINADO("2"), ANULADO("3");

	private String estado;

	private ConstanteWeb(String estado) {

		this.estado = estado;

	}

	public String getValor() {

		return estado;

	}

	public int getValorNumerico() {

		try {

			Integer estadoInt = Integer.parseInt(estado);

			return estadoInt;

		} catch (Exception e) {

			return 0;

		}

	}

}
