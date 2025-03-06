package br.edu.ifba.inf011.model.chainResponsability;

import java.time.LocalDate;

import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.strategy.EmailStrategy;
import br.edu.ifba.inf011.model.strategy.WppStrategy;

public class Prioridade10Hoje extends Handler {
	
	@Override
	public void handle(Evento e) {
		if(e.getPrioridade() == 10 && e.iniciaEm(LocalDate.now())) {
			notificar(e);
		}
		else
			repassaParaOProximo(e);
			
	}

	@Override
	public String enviaNotificacao(Evento e) {
		setStrategy(new WppStrategy());
		String formatada = strategy.formataMensagem(e);
		strategy.enviaMensagem(formatada);
		setStrategy(new EmailStrategy());
		strategy.enviaMensagem(formatada);
		return formatada;
	}
	
	@Override
	public void adicionandoEventoAoGoogleCalendar(String formatada) {
		System.out.println("Adicionando evento ao Google Calendar: " + formatada);
	}

}
