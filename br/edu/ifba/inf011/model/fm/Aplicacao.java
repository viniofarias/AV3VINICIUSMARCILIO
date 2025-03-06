package br.edu.ifba.inf011.model.fm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import br.edu.ifba.inf011.academico.DatabaseAcademico;
import br.edu.ifba.inf011.model.Calendario;
import br.edu.ifba.inf011.model.Equipe;
import br.edu.ifba.inf011.model.Geolocalizacao;
import br.edu.ifba.inf011.model.chainResponsability.Handler;
import br.edu.ifba.inf011.model.chainResponsability.Notificador;
import br.edu.ifba.inf011.model.chainResponsability.Prioridade10Hoje;
import br.edu.ifba.inf011.model.chainResponsability.Prioridade5Hoje;
import br.edu.ifba.inf011.model.chainResponsability.PrioridadeMenorQue5IniciaEntreHojeAnteontem;
import br.edu.ifba.inf011.model.decorator.OnlineCalendarioDecorator;
import br.edu.ifba.inf011.model.decoratorNotificacao.WppNotificador;
import br.edu.ifba.inf011.model.evento.Evento;
import br.edu.ifba.inf011.model.evento.Lembrete;
import br.edu.ifba.inf011.model.evento.Partida;
import br.edu.ifba.inf011.model.evento.TipoEvento;
import br.edu.ifba.inf011.model.evento.builder.BuilderPartida;

public class Aplicacao extends AplicacaoCalendario{

	
	@Override
	protected Calendario createCalendario(){
		try {
			return 
					new OnlineCalendarioDecorator(
							new OnlineCalendarioDecorator(
									new CalendarioPessoal("inf011@ads.ifba.edu.br", 12, 2024), 
							"http://feriados.com"),
					"http://detroitlions.com");
		}catch(Exception ex) {
			return new CalendarioPessoal("inf011@ads.ifba.edu.br", 12, 2024);
		}	
	}

	
	public void run() throws Exception {
		
		this.adicionarEvento(((BuilderPartida) 
				  			   Partida.builder()
				  					  .setInicio(LocalDateTime.of(1970, 6, 21, 12, 0))
				  					  .setLocalizacao(new Geolocalizacao("Estádio Azteca - Cidade do México")))
					  				  .setPlacar(4, 1)
					  				  .build("Final da Copa do Mundo de 1970", new Equipe("Brasil"), new Equipe("Italia")));
		
		this.adicionarEvento(Lembrete.builder()
							.setInicio(LocalDateTime.now())
							.setPrioridade(4)
							.build(TipoEvento.LEMBRETE, "Avaliação II de Padrões de Projeto"));
		
		this.adicionarEvento(Lembrete.builder()
				.setInicio(LocalDateTime.of(2025, 3, 6, 00, 00))
				.setPrioridade(10)
				.build(TipoEvento.LEMBRETE, "Reunião Semanal"));
		
		this.adicionarEvento(Lembrete.builder()
				.setInicio(LocalDateTime.now())
				.setPrioridade(5)
				.build(TipoEvento.LEMBRETE, "Avaliação III de Padrões de Projeto"));
				
		
		Collection<Evento> hoje = this.today();
		
		for(Evento e : hoje) {
			Handler handler = new Prioridade10Hoje()
					.setProximo(new Prioridade5Hoje()
							.setProximo(new PrioridadeMenorQue5IniciaEntreHojeAnteontem()));
			Notificador notificador = new Notificador(handler);


			notificador.notificar(e);	
		}
		
	}
	



	public static void main(String[] args) throws Exception {
		(new Aplicacao()).run();
	}

}
