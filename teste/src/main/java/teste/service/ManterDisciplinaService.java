package teste.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import teste.model.Disciplina;
import teste.model.dao.DisciplinaDao;

public class ManterDisciplinaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private DisciplinaDao disciplinaDao;

    public void salvar(Disciplina disciplina) {
        disciplinaDao.salvar(disciplina);
    }

    public void excluir(Disciplina disciplina) {
        disciplinaDao.excluir(disciplina);
    }

    public List<Disciplina> buscarTodos() {
        return disciplinaDao.buscarTodos();
    }
}
