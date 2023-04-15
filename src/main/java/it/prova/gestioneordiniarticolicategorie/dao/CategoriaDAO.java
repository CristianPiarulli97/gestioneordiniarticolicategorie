package it.prova.gestioneordiniarticolicategorie.dao;

import java.util.List;

import it.prova.gestioneordiniarticolicategorie.model.Categoria;

public interface CategoriaDAO extends IBaseDAO<Categoria>{

	public List<String> listCodiciCategoriaDuranteUnMese (int anno, int mese) throws Exception;

}
