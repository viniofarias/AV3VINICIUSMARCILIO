package br.edu.ifba.inf011.model.chainResponsability;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.strategy.EmailStrategy;

public class PrioridadeMenorQue5IniciaEntreHojeAnteontem extends Handler {
	@Override
	public void handle(Evento e) {
		if(e.getPrioridade() >= 1 && e.getPrioridade() < 5 && e.iniciaEntre(LocalDateTime.now().minus(2, ChronoUnit.DAYS),LocalDateTime.now())) {
			notificar(e);
		}
		else
			repassaParaOProximo(e);
			
	}

	@Override
	public String enviaNotificacao(Evento e) {
		setStrategy(new EmailStrategy());
		String formatada = strategy.formataMensagem(e);
		return formatada;
	}
	
	@Override
	public void adicionandoEventoAoGoogleCalendar(String formatada) {
		System.out.println("Adicionando evento ao Google Calendar: " + formatada);
	}
}
