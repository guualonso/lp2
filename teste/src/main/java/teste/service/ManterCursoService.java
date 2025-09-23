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
		Curso cursoComDisciplinas = cursoDao.buscarPeloCodigo(curso.getId());

		if (cursoComDisciplinas.getDisciplinas() != null &&
			!cursoComDisciplinas.getDisciplinas().isEmpty()) {
			
			throw new RuntimeException("Não é possível excluir. Existem disciplinas associadas ao curso.");
		}

		cursoDao.excluir(cursoComDisciplinas);
	}

    public List<Curso> buscarTodos() {
        return cursoDao.buscarTodos();
    }
	
}
