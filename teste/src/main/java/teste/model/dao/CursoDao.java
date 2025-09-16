package teste.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import lombok.extern.log4j.Log4j;
import teste.model.Curso;
import teste.util.jpa.Transactional;

@Log4j
public class CursoDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;
		
	@Transactional
	public Curso salvar(Curso curso) throws PersistenceException {
		
		log.info("salvar DAO... curso = " + curso);
		
		try {
			return manager.merge(curso);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Transactional
	public void excluir(Curso curso) throws PersistenceException {

		try {
			Curso a = manager.find(Curso.class, curso.getId());
			manager.remove(a);
			manager.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw e;
		} 
	}
	
	
	public Curso buscarPeloCodigo(Long id) {
		return manager.find(Curso.class, id);
	}

	@SuppressWarnings("unchecked")
    public List<Curso> buscarTodos() {

        String query="select c from Curso c";
		
		Query q = manager.createQuery(query);
		
		return q.getResultList();
    }
}
