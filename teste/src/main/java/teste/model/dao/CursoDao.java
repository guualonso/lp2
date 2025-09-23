package teste.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import lombok.extern.log4j.Log4j;
import teste.model.Curso;
import teste.util.jpa.Transactional;

@Log4j
public class CursoDao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager manager;
        
    @Transactional
    public Curso salvar(Curso curso) throws PersistenceException {
        log.info("Salvando curso: " + curso);
        
        try {
            return manager.merge(curso);
        } catch (PersistenceException e) {
            log.error("Erro ao salvar curso: " + curso, e);
            throw e;
        }
    }
    
    @Transactional
    public void excluir(Curso curso) throws PersistenceException {
        log.info("Excluindo curso: " + curso);

        try {
            Curso cursoParaExcluir = manager.find(Curso.class, curso.getId());
            
            if (cursoParaExcluir != null) {
                cursoParaExcluir.getDisciplinas().clear();
                manager.merge(cursoParaExcluir);
                manager.flush();

                manager.remove(cursoParaExcluir);
                manager.flush();
            } else {
                log.warn("Tentativa de excluir curso não encontrado: " + curso.getId());
            }
        } catch (PersistenceException e) {
            log.error("Erro ao excluir curso: " + curso, e);
            throw e;
        } 
    }

    
    public Curso buscarPeloCodigo(Long id) {
		log.debug("Buscando curso pelo código: " + id);
		
		String query = "select c from Curso c left join fetch c.disciplinas where c.id = :id";
		TypedQuery<Curso> q = manager.createQuery(query, Curso.class);
		q.setParameter("id", id);
		
		try {
			return q.getSingleResult();
		} catch (Exception e) {
			log.warn("Curso não encontrado com ID: " + id, e);
			return null;
		}
	}

	public List<Curso> buscarTodos() {
		log.debug("Buscando todos os cursos");
		
		String query = "select distinct c from Curso c left join fetch c.disciplinas";
		TypedQuery<Curso> q = manager.createQuery(query, Curso.class);
		
		return q.getResultList();
	}
}