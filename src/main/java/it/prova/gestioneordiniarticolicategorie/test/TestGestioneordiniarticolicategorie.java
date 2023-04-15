package it.prova.gestioneordiniarticolicategorie.test;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;
import it.prova.gestioneordiniarticolicategorie.service.ArticoloService;
import it.prova.gestioneordiniarticolicategorie.service.CategoriaService;
import it.prova.gestioneordiniarticolicategorie.service.MyServiceFactory;
import it.prova.gestioneordiniarticolicategorie.service.OrdineService;

import java.time.LocalDate;
import java.util.List;

public class TestGestioneordiniarticolicategorie {

	public static void main(String[] args) {

		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

		try {
			
	

		//	testInserimentoNuovoOrdine(ordineServiceInstance);
			
		//	testInserisciArticolo(articoloServiceInstance, ordineServiceInstance);
			
		//	testAggiornamentoOrdineEsistente(ordineServiceInstance);
			
		//	testAggiornamentoArticoloEsistente(articoloServiceInstance);
			
			
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	
	
	
	
	private static void testInserisciArticolo(ArticoloService articoloServiceInstance,OrdineService ordineServiceInstance) throws Exception {
	
		Ordine ordineDaCollegare = new Ordine("Matteo Matano", "Via dei promontori", LocalDate.of(2023, 04, 15),
				LocalDate.of(2023, 05, 15));
		ordineServiceInstance.inserisciNuovo(ordineDaCollegare);
		
		Articolo articoloDaInserire = new Articolo("Tastiera","XXX543",3.4);
		articoloDaInserire.setOrdine(ordineDaCollegare);
		articoloServiceInstance.inserisciNuovo(articoloDaInserire);

	}
	
	private static void testInserimentoNuovoOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println("------------- testInserimentoNuovoOrdine INIZIO -------------");

		Ordine ordineDaInserire = new Ordine("Cristian Piarulli", "Via Antonio antonioli", LocalDate.of(2023, 04, 24),
				LocalDate.of(2023, 05, 01));
		ordineServiceInstance.inserisciNuovo(ordineDaInserire);
		if (ordineDaInserire.getId() == null) {
			throw new RuntimeException("testInserimentoNuovoOrdine FALLITO: Ordine non inserito.");
		}
		System.out.println("------------- testInserimentoNuovoOrdine FINE -------------");
	}

	
	private static void testAggiornamentoOrdineEsistente(OrdineService ordineServiceInstance) throws Exception {
		System.out.println("------------- testAggiornamentoOrdineEsistente INIZIO -------------");

		List<Ordine> listaOrdiniPresenti = ordineServiceInstance.listAll();
		if (listaOrdiniPresenti.size() < 1) {
			throw new RuntimeException("testAggiornamentoOrdineEsistente FALLITO: non sono presenti voci nel DB.");
		}
		Ordine ordineDaAggiornare = listaOrdiniPresenti.get(0);
		System.out.println("Prima dell'aggiornamento: " + ordineDaAggiornare);
		String nuovoIndirizzo = "Viaaaaaaaaa";
		ordineDaAggiornare.setIndirizzoSpedizione(nuovoIndirizzo);
		ordineServiceInstance.aggiorna(ordineDaAggiornare);

		Ordine ordineReloaded = ordineServiceInstance.caricaSingoloElemento(ordineDaAggiornare.getId());
		if (ordineReloaded.getId() != ordineDaAggiornare.getId()) {
			throw new RuntimeException("testAggiornamentoOrdineEsistente FALLITO: update non avvenuto.");
		}
		System.out.println("Dopo l'aggiornamento: " + ordineReloaded);
		System.out.println("------------- testAggiornamentoOrdineEsistente FINE -------------");

	}
	
	private static void testAggiornamentoArticoloEsistente(ArticoloService articoloServiceInstance) throws Exception {
		System.out.println("------------- testAggiornamentoArticoloEsistente INIZIO -------------");

		List<Articolo> listaArticoliPresenti = articoloServiceInstance.listAll();
		if (listaArticoliPresenti.size() < 1) {
			throw new RuntimeException("testAggiornamentoArticoloEsistente FALLITO: non sono presenti voci nel DB.");
		}

		Articolo articoloDaAggiornare = listaArticoliPresenti.get(0);
		System.out.println("Prima dell'aggiornamento: " + articoloDaAggiornare);
		String nuovoNome = "Mouse";
		articoloDaAggiornare.setDescrizione(nuovoNome);
		articoloServiceInstance.aggiorna(articoloDaAggiornare);

		Articolo articoloReloaded = articoloServiceInstance.caricaSingoloElemento(articoloDaAggiornare.getId());
		if (articoloReloaded.getId() != articoloDaAggiornare.getId()) {
			throw new RuntimeException("testAggiornamentoArticoloEsistente FALLITO: update non avvenuto.");
		}
		System.out.println("Dopo l'aggiornamento: " + articoloReloaded);
		System.out.println("------------- testAggiornamentoArticoloEsistente FINE -------------");

	}

	
}
