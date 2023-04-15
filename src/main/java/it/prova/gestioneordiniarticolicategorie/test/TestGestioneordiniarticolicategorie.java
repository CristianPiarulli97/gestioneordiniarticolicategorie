package it.prova.gestioneordiniarticolicategorie.test;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Ordine;
import it.prova.gestioneordiniarticolicategorie.service.ArticoloService;
import it.prova.gestioneordiniarticolicategorie.service.CategoriaService;
import it.prova.gestioneordiniarticolicategorie.service.MyServiceFactory;
import it.prova.gestioneordiniarticolicategorie.service.OrdineService;

import java.time.LocalDate;

public class TestGestioneordiniarticolicategorie {

	public static void main(String[] args) {

		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

		try {
			
	

		//	testInserimentoNuovoOrdine(ordineServiceInstance);
			
		//	testInserisciArticolo(articoloServiceInstance, ordineServiceInstance);
			
			
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

}
