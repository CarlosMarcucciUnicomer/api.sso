package com.unicomer.api.sso.models.dto.sms;

import java.util.Objects;

public class SmsRequestDto {
	private String usuario;
	private String contrasena;
	private String numero;
	private String mensaje;
	private String remitente;

	public SmsRequestDto() {
	}

	public SmsRequestDto(String usuario, String contrasena, String numero, String mensaje, String remitente) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.numero = numero;
		this.mensaje = mensaje;
		this.remitente = remitente;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getRemitente() {
		return this.remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public SmsRequestDto usuario(String usuario) {
		setUsuario(usuario);
		return this;
	}

	public SmsRequestDto contrasena(String contrasena) {
		setContrasena(contrasena);
		return this;
	}

	public SmsRequestDto numero(String numero) {
		setNumero(numero);
		return this;
	}

	public SmsRequestDto mensaje(String mensaje) {
		setMensaje(mensaje);
		return this;
	}

	public SmsRequestDto remitente(String remitente) {
		setRemitente(remitente);
		return this;
	}

	@Override
		public boolean equals(Object o) {
				if (o == this)
						return true;
				if (!(o instanceof SmsRequestDto)) {
						return false;
				}
				SmsRequestDto smsRequestDto = (SmsRequestDto) o;
				return Objects.equals(usuario, smsRequestDto.usuario) && Objects.equals(contrasena, smsRequestDto.contrasena) && Objects.equals(numero, smsRequestDto.numero) && Objects.equals(mensaje, smsRequestDto.mensaje) && Objects.equals(remitente, smsRequestDto.remitente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuario, contrasena, numero, mensaje, remitente);
	}

	@Override
	public String toString() {
		return "{" +
			" usuario='" + getUsuario() + "'" +
			", contrasena='" + getContrasena() + "'" +
			", numero='" + getNumero() + "'" +
			", mensaje='" + getMensaje() + "'" +
			", remitente='" + getRemitente() + "'" +
			"}";
	}

}

