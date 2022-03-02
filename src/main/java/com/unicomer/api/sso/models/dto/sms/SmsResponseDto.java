package com.unicomer.api.sso.models.dto.sms;

import java.util.Objects;

public class SmsResponseDto {
	private SmsDResponseDto d;

	public SmsResponseDto() {
	}

	public SmsResponseDto(SmsDResponseDto d) {
		this.d = d;
	}

	public SmsDResponseDto getD() {
		return this.d;
	}

	public void setD(SmsDResponseDto d) {
		this.d = d;
	}

	public SmsResponseDto d(SmsDResponseDto d) {
		setD(d);
		return this;
	}

	@Override
		public boolean equals(Object o) {
				if (o == this)
						return true;
				if (!(o instanceof SmsResponseDto)) {
						return false;
				}
				SmsResponseDto smsResponseDto = (SmsResponseDto) o;
				return Objects.equals(d, smsResponseDto.d);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(d);
	}

	@Override
	public String toString() {
		return "{" +
			" d='" + getD() + "'" +
			"}";
	}
}

class SmsDResponseDto {
	private String __type;
	private Integer codigoEstado;
	private Boolean esEnvioExitoso;
	private String MensajeRespuesta;
	private Long referencia;

	public SmsDResponseDto() {
	}

	public SmsDResponseDto(String __type, Integer codigoEstado, Boolean esEnvioExitoso, String MensajeRespuesta, Long referencia) {
		this.__type = __type;
		this.codigoEstado = codigoEstado;
		this.esEnvioExitoso = esEnvioExitoso;
		this.MensajeRespuesta = MensajeRespuesta;
		this.referencia = referencia;
	}

	public String get__type() {
		return this.__type;
	}

	public void set__type(String __type) {
		this.__type = __type;
	}

	public Integer getCodigoEstado() {
		return this.codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public Boolean isEsEnvioExitoso() {
		return this.esEnvioExitoso;
	}

	public Boolean getEsEnvioExitoso() {
		return this.esEnvioExitoso;
	}

	public void setEsEnvioExitoso(Boolean esEnvioExitoso) {
		this.esEnvioExitoso = esEnvioExitoso;
	}

	public String getMensajeRespuesta() {
		return this.MensajeRespuesta;
	}

	public void setMensajeRespuesta(String MensajeRespuesta) {
		this.MensajeRespuesta = MensajeRespuesta;
	}

	public Long getReferencia() {
		return this.referencia;
	}

	public void setReferencia(Long referencia) {
		this.referencia = referencia;
	}

	public SmsDResponseDto __type(String __type) {
		set__type(__type);
		return this;
	}

	public SmsDResponseDto codigoEstado(Integer codigoEstado) {
		setCodigoEstado(codigoEstado);
		return this;
	}

	public SmsDResponseDto esEnvioExitoso(Boolean esEnvioExitoso) {
		setEsEnvioExitoso(esEnvioExitoso);
		return this;
	}

	public SmsDResponseDto MensajeRespuesta(String MensajeRespuesta) {
		setMensajeRespuesta(MensajeRespuesta);
		return this;
	}

	public SmsDResponseDto referencia(Long referencia) {
		setReferencia(referencia);
		return this;
	}

	@Override
		public boolean equals(Object o) {
				if (o == this)
						return true;
				if (!(o instanceof SmsDResponseDto)) {
						return false;
				}
				SmsDResponseDto smsDResponseDto = (SmsDResponseDto) o;
				return Objects.equals(__type, smsDResponseDto.__type) && Objects.equals(codigoEstado, smsDResponseDto.codigoEstado) && Objects.equals(esEnvioExitoso, smsDResponseDto.esEnvioExitoso) && Objects.equals(MensajeRespuesta, smsDResponseDto.MensajeRespuesta) && Objects.equals(referencia, smsDResponseDto.referencia);
	}

	@Override
	public int hashCode() {
		return Objects.hash(__type, codigoEstado, esEnvioExitoso, MensajeRespuesta, referencia);
	}

	@Override
	public String toString() {
		return "{" +
			" __type='" + get__type() + "'" +
			", codigoEstado='" + getCodigoEstado() + "'" +
			", esEnvioExitoso='" + isEsEnvioExitoso() + "'" +
			", MensajeRespuesta='" + getMensajeRespuesta() + "'" +
			", referencia='" + getReferencia() + "'" +
			"}";
	}
}