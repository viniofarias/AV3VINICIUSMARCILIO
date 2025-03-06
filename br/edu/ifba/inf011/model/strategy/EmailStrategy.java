package br.edu.ifba.inf011.model.strategy;

import br.edu.ifba.inf011.model.evento.Evento;

public class EmailStrategy implements StrategyNotificador{

	@Override
	public String formataMensagem(Evento e) {
		return e.getDescricao() + " (EMAIL) ";
	}

	@Override
	public void enviaMensagem(String formatada) {
		System.out.println("email enviado " + formatada);
	}

}
