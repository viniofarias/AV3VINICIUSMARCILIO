package br.edu.ifba.inf011.model.chainResponsability;

import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.strategy.StrategyNotificador;

public abstract class Handler {
	private Handler proximo;
	protected StrategyNotificador strategy;
	
	public abstract void handle(Evento e);
	public abstract String enviaNotificacao(Evento e);
	
	public Handler setProximo(Handler proximo) {
		this.proximo = proximo;
		return this;
	}
	
	public void setStrategy(StrategyNotificador strategy) {
		this.strategy = strategy;
	}
		
	//MÉTODO HOOK -> PODE IMPLEMENTAR OU NÃO
	public void adicionandoEventoAoGoogleCalendar(String formatada) { 
		
	}
	
	// MÉTODO TEMPLATE
	public void notificar(Evento e) {
		String formatada = enviaNotificacao(e);
		adicionandoEventoAoGoogleCalendar(formatada);
	}
	
	protected void repassaParaOProximo(Evento e) {
		if(this.proximo != null)
			proximo.handle(e);
	}
	
}
