package br.edu.ifba.inf011.model.chainResponsability;

import br.edu.ifba.inf011.model.evento.Evento;

public class Notificador {
	private Handler handler;
	
	public Notificador(Handler handler) {
		this.handler = handler;
	}
	
	public void notificar(Evento e) {
		handler.handle(e);
	}
}
