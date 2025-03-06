package br.edu.ifba.inf011.model.chainResponsability;

import java.time.LocalDate;

import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.strategy.EmailStrategy;

public class Prioridade5Hoje extends Handler{
	@Override
	public void handle(Evento e) {
		if(e.getPrioridade() == 5 && e.iniciaEm(LocalDate.now())) {
			notificar(e);
		}
		else
			repassaParaOProximo(e);
			
	}

	@Override
	public String enviaNotificacao(Evento e) {
		setStrategy(new EmailStrategy());
		String formatada = strategy.formataMensagem(e);
		strategy.enviaMensagem(formatada);
		return formatada;
	}
	
	@Override
	public void adicionandoEventoAoGoogleCalendar(String formatada) {
		System.out.println("Adicionando evento ao Google Calendar: " + formatada);
	}
}
