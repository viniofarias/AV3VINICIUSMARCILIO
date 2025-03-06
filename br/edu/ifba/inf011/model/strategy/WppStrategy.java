package br.edu.ifba.inf011.model.strategy;

import br.edu.ifba.inf011.model.evento.Evento;

public class WppStrategy implements StrategyNotificador{

	@Override
	public String formataMensagem(Evento e) {
		return e.getDescricao() + " (WhatsApp) ";
	}

	@Override
	public void enviaMensagem(String formatada) {
		System.out.println("whatsapp enviado " + formatada);		
	}

}
