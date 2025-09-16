package teste.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import lombok.extern.log4j.Log4j;
import teste.model.Disciplina;
import teste.util.jpa.Transactional;

@Log4j
public class DisciplinaDao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @Transactional
    public Disciplina salvar(Disciplina disciplina) throws PersistenceException {
        log.info("salvar DAO... disciplina = " + disciplina);
        try {
            return manager.merge(disciplina);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public void excluir(Disciplina disciplina) throws PersistenceException {
        try {
            Disciplina d = manager.find(Disciplina.class, disciplina.getId());
            manager.remove(d);
            manager.flush();
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Disciplina buscarPeloCodigo(Long id) {
        return manager.find(Disciplina.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Disciplina> buscarTodos() {
        String query = "select d from Disciplina d";
        Query q = manager.createQuery(query);
        return q.getResultList();
    }
}
