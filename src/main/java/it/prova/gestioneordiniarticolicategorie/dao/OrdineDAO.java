package it.prova.gestioneordiniarticolicategorie.dao;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine>{

	public List<Ordine> findAllByCategoria(Long idCategoria) throws Exception;

	public List<String> indirizziDegliOrdiniContenentiNumeroSeriale(String stringaNumeroSeriale);

	public Ordine getRecentOrdineByCategoria(Long idCategoria);

}
