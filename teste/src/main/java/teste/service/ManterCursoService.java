package teste.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import teste.model.Curso;
import teste.model.dao.CursoDao;

public class ManterCursoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject	
	private CursoDao cursoDao;
	
	@Transactional
	public Curso salvar(Curso curso) {
		if (curso.getId() != null) {
			Curso cursoManaged = cursoDao.buscarPeloCodigo(curso.getId());
			if (cursoManaged != null) {
				cursoManaged.setNome(curso.getNome());
				return cursoDao.salvar(cursoManaged);
			}
		}
		return cursoDao.salvar(curso);
	}
	
	public void excluir(Curso curso) {
		this.cursoDao.excluir(curso);
	}

    public List<Curso> buscarTodos() {
        return cursoDao.buscarTodos();
    }
	
}
