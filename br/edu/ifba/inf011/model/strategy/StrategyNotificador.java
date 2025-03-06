package br.edu.ifba.inf011.model.strategy;

import br.edu.ifba.inf011.model.evento.Evento;

public interface StrategyNotificador {
	public String formataMensagem(Evento e);
	public void enviaMensagem(String formatada);
}
