package com.clouding.airline.dto;

public class EmbarqueDTO {

	Long vuelo_id;
	Long agencia_id;
	
	public EmbarqueDTO() {
		
	}
	public Long getVuelo_id() {
		return vuelo_id;
	}
	public void setVuelo_id(Long vuelo_id) {
		this.vuelo_id = vuelo_id;
	}
	public Long getAgencia_id() {
		return agencia_id;
	}
	public void setAgencia_id(Long agencia_id) {
		this.agencia_id = agencia_id;
	}
}
