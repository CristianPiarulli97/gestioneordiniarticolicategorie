package it.prova.gestioneordiniarticolicategorie.test;

import it.prova.gestioneordiniarticolicategorie.dao.EntityManagerUtil;
import it.prova.gestioneordiniarticolicategorie.model.Articolo;
import it.prova.gestioneordiniarticolicategorie.model.Categoria;
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
			
		//	testInserimentoNuovaCategoria(categoriaServiceInstance);
			
		//	testAggiornamentoCategoriaEsistente(categoriaServiceInstance);
			
		//	testAssociaArticoloACategoria(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
		//	testRimozioneCompletaArticolo(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
		
		//	testRimozioneCompletaCategoria(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
			
			
			
		
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}

	}

	
	
	
	
	private static void testInserisciArticolo(ArticoloService articoloServiceInstance,OrdineService ordineServiceInstance) throws Exception {
	
		Ordine ordineDaCollegare = new Ordine("Riccardo Binachini", "Via delle baleniere", LocalDate.of(2023, 04, 15),
				LocalDate.of(2023, 05, 15));
		ordineServiceInstance.inserisciNuovo(ordineDaCollegare);
		
		Articolo articoloDaInserire = new Articolo("Cavo USB","X00143",3.4);
		articoloDaInserire.setOrdine(ordineDaCollegare);
		articoloServiceInstance.inserisciNuovo(articoloDaInserire);

	}
	
	private static void testInserimentoNuovoOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println("------------- testInserimentoNuovoOrdine INIZIO -------------");

		Ordine ordineDaInserire = new Ordine("Manuel Piarulli", "Via Antonio antonioli", LocalDate.of(2023, 04, 24),
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
	
	private static void testInserimentoNuovaCategoria(CategoriaService categoriaServiceInstance) throws Exception {
		

		Categoria categoriaDaInserire = new Categoria("Giardinaggio", "19");
		categoriaServiceInstance.inserisciNuovo(categoriaDaInserire);
		if (categoriaDaInserire.getId() == null) {
			throw new RuntimeException("testInserimentoNuovaCategoria FALLITO: Ordine non inserito.");
		}
		System.out.println("------------- testInserimentoNuovaCategoria FINE -------------");
	}

	private static void testAggiornamentoCategoriaEsistente(CategoriaService categoriaServiceInstance)
			throws Exception {
		System.out.println("------------- testAggiornamentoCategoriaEsistente INIZIO -------------");

		List<Categoria> listaCategoriePresenti = categoriaServiceInstance.listAll();
		if (listaCategoriePresenti.size() < 1) {
			throw new RuntimeException("testAggiornamentoCategoriaEsistente FALLITO: non sono presenti voci nel DB.");
		}

		Categoria categoriaDaAggiornare = listaCategoriePresenti.get(1);
		System.out.println("Prima dell'aggiornamento: " + categoriaDaAggiornare);
		String nuovoCodice = "9999";
		categoriaDaAggiornare.setCodice(nuovoCodice);
		categoriaServiceInstance.aggiorna(categoriaDaAggiornare);

		Categoria categoriaReloaded = categoriaServiceInstance.caricaSingoloElemento(categoriaDaAggiornare.getId());
		if (categoriaReloaded.getId() != categoriaDaAggiornare.getId()) {
			throw new RuntimeException("testAggiornamentoCategoriaEsistente FALLITO: update non avvenuto.");
		}
		System.out.println("Dopo l'aggiornamento: " + categoriaReloaded);
		System.out.println("------------- testAggiornamentoCategoriaEsistente FINE -------------");

	}
	
	private static void testAssociaArticoloACategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		
		System.out.println("------------- testAssociaArticoloECategoria INIZIO -------------");
		Categoria nuovaCategoria = new Categoria("Sport", "83");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria);
		if (nuovaCategoria.getId() == null) {
			throw new RuntimeException("testAssociaArticoloECategoria FALLITO: Ordine non inserito.");
		}
		Ordine nuovoOrdine = new Ordine("Serena Sisna", "Via del dragone", LocalDate.of(2023, 5, 1),
				LocalDate.of(2023, 7, 12));
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);
		if (nuovoOrdine.getId() == null) {
			throw new RuntimeException("testAssociaArticoloECategoria FALLITO: Ordine non inserito.");
		}
		Articolo nuovoArticolo = new Articolo("Scarpini Total90", "0722", 299D, LocalDate.now());
		nuovoArticolo.setOrdine(nuovoOrdine);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);
		if (nuovoArticolo.getId() == null) {
			throw new RuntimeException("testAssociaArticoloECategoria FALLITO: Ordine non inserito.");
		}

		categoriaServiceInstance.aggiungiArticolo(nuovaCategoria, nuovoArticolo);
		Categoria categoriaReloaded = categoriaServiceInstance.caricaSingoloElemento(nuovaCategoria.getId());
		if (categoriaReloaded.getArticoli().isEmpty()) {
			throw new RuntimeException("testAssociaArticoloECategoria FALLITO: articolo non associato a categoria");
		}

		System.out.println("------------- testAssociaArticoloECategoria FINE -------------");

	}
	
	private static void testAssociaCategoriaAdArticolo(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("------------- testAssociaCategoriaAdArticolo INIZIO -------------");
		Categoria nuovaCategoria = new Categoria("Abbigliamento", "201");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria);
		if (nuovaCategoria.getId() == null) {
			throw new RuntimeException("testAssociaCategoriaAdArticolo FALLITO: Ordine non inserito.");
		}
		Ordine nuovoOrdine = new Ordine("Luca De Luca", "Via Casilina 52", LocalDate.of(2023, 5, 31),
				LocalDate.of(2023, 8, 30));
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);
		if (nuovoOrdine.getId() == null) {
			throw new RuntimeException("testAssociaCategoriaAdArticolo FALLITO: Ordine non inserito.");
		}
		Articolo nuovoArticolo = new Articolo("Scarpe Nike", "0001", 299D, LocalDate.now());
		nuovoArticolo.setOrdine(nuovoOrdine);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);
		if (nuovoArticolo.getId() == null) {
			throw new RuntimeException("testAssociaCategoriaAdArticolo FALLITO: Ordine non inserito.");
		}

		articoloServiceInstance.aggiungiCategoria(nuovoArticolo, nuovaCategoria);
		Articolo articoloReloaded = articoloServiceInstance.caricaSingoloElemento(nuovoArticolo.getId());
		if (articoloReloaded.getCategorie().isEmpty()) {
			throw new RuntimeException("testAssociaCategoriaAdArticolo FALLITO: articolo non associato a categoria");
		}

		System.out.println("------------- testAssociaCategoriaAdArticolo FINE -------------");
	}
	
	private static void testRimozioneCompletaArticolo(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("------------- testRimozioneCompletaArticolo INIZIO -------------");
		Categoria nuovaCategoria1 = new Categoria("Abbigliamento", "201");
		Categoria nuovaCategoria2 = new Categoria("Informatica", "202");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria1);
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria2);
		if (nuovaCategoria1.getId() == null) {
			throw new RuntimeException("testRimozioneCompletaArticolo FALLITO: categoria non inserita.");
		}
		if (nuovaCategoria2.getId() == null) {
			throw new RuntimeException("testRimozioneCompletaArticolo FALLITO: categoria non inserita.");
		}
		Ordine nuovoOrdine = new Ordine("Luca De Luca", "Via Casilina 52", LocalDate.of(2023, 5, 31),
				LocalDate.of(2023, 8, 30));
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);
		if (nuovoOrdine.getId() == null) {
			throw new RuntimeException("testRimozioneCompletaArticolo FALLITO: Ordine non inserito.");
		}
		Articolo nuovoArticolo = new Articolo("Scarpe Nike", "0001", 299D, LocalDate.now());
		nuovoArticolo.setOrdine(nuovoOrdine);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo);
		if (nuovoArticolo.getId() == null) {
			throw new RuntimeException("testRimozioneCompletaArticolo FALLITO: articolo non inserito.");
		}

		articoloServiceInstance.aggiungiCategoria(nuovoArticolo, nuovaCategoria1);
		articoloServiceInstance.aggiungiCategoria(nuovoArticolo, nuovaCategoria2);

		Articolo articoloDaRimuovere = articoloServiceInstance.caricaArticoloEager(nuovoArticolo.getId());
		if (articoloDaRimuovere.getId() == null) {
			throw new RuntimeException("testRimozioneCompletaArticolo FALLITO: Ordine non inserito.");
		}
		if (articoloDaRimuovere.getCategorie().isEmpty()) {
			throw new RuntimeException("testRimozioneCompletaArticolo FALLITO: categoria non associata ad articolo");
		}
		// rimozione completa articolo
		articoloServiceInstance.rimozioneArticoloCompleta(articoloDaRimuovere.getId());

		System.out.println("------------- testRimozioneCompletaArticolo FINE -------------");

	}
	
	private static void testRimozioneCompletaCategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println("------------- testRimozioneCompletaCategoria INIZIO -------------");
		Categoria nuovaCategoria = new Categoria("Cucina", "201");
		categoriaServiceInstance.inserisciNuovo(nuovaCategoria);
		if (nuovaCategoria.getId() == null) {
			throw new RuntimeException("testRimozioneCompletaCategoria FALLITO: categoria non inserita.");
		}
		Ordine nuovoOrdine = new Ordine("Me", "Via aaaa ", LocalDate.of(2023, 1, 01),
				LocalDate.of(2023, 8, 30));
		ordineServiceInstance.inserisciNuovo(nuovoOrdine);
		if (nuovoOrdine.getId() == null) {
			throw new RuntimeException("testRimozioneCompletaCategoria FALLITO: Ordine non inserito.");
		}
		Articolo nuovoArticolo1 = new Articolo("Mestolo", "122", 333d, LocalDate.now());
		Articolo nuovoArticolo2 = new Articolo("Frullatore", "022", 55d, LocalDate.now());
		nuovoArticolo1.setOrdine(nuovoOrdine);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo1);
		if (nuovoArticolo1.getId() == null) {
			throw new RuntimeException("testRimozioneCompletaCategoria FALLITO: articolo non inserito.");
		}
		nuovoArticolo2.setOrdine(nuovoOrdine);
		articoloServiceInstance.inserisciNuovo(nuovoArticolo2);
		if (nuovoArticolo2.getId() == null) {
			throw new RuntimeException("testRimozioneCompletaCategoria FALLITO: articolo non inserito.");
		}

		categoriaServiceInstance.aggiungiArticolo(nuovaCategoria, nuovoArticolo1);
		categoriaServiceInstance.aggiungiArticolo(nuovaCategoria, nuovoArticolo2);
		Categoria categoriaReloaded = categoriaServiceInstance.caricaCategoriaEager(nuovaCategoria.getId());
		if (categoriaReloaded.getArticoli().isEmpty()) {
			throw new RuntimeException("testRimozioneCompletaCategoria FALLITO: articolo non associato a categoria");
		}

		// rimozione completa categoria
		categoriaServiceInstance.rimozioneCompletaCategoria(categoriaReloaded.getId());

		System.out.println("------------- testRimozioneCompletaCategoria FINE -------------");

	}
}
