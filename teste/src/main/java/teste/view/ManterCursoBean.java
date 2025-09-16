package teste.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import teste.model.Curso;
import teste.model.Disciplina;
import teste.service.ManterCursoService;
import teste.service.ManterDisciplinaService;

@Log4j
@Getter
@Setter
@Named
@ViewScoped
public class ManterCursoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private ManterDisciplinaService manterDisciplinaService;

	private List<Disciplina> disciplinas = new ArrayList<>();

	@Inject
	private ManterCursoService manterCursoService;
	
	private Curso curso = new Curso();
	private List<Curso> cursos = new ArrayList<>();
	
	// Novo atributo para armazenar o curso selecionado na tabela
	private Curso cursoSelecionado;

	@PostConstruct
	public void inicializar() {
		this.cursos = manterCursoService.buscarTodos();
    	this.disciplinas = manterDisciplinaService.buscarTodos();
    	limpar();
	}
	
	public void salvar() {
		log.info(curso.toString());
		manterCursoService.salvar(curso);
		
		FacesContext.getCurrentInstance().
        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
        		"O curso foi gravado com sucesso!", 
        		curso.toString()));
		
		log.info("curso: " + curso.toString());
	}
	
	public void excluir() {
		try {
			manterCursoService.excluir(curso);
			this.cursos = manterCursoService.buscarTodos();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Curso " + curso.getNome() + " excluído com sucesso.", null));
			log.info("curso excluido = " + curso.getNome());
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um problema", null));
		}
	}
		
	public void limpar() {
		this.curso = new Curso();
	}

	public void onCursoNomeClick(Curso cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;

		if (cursoSelecionado != null) {
			log.info("Curso selecionado: " + cursoSelecionado.getNome());
			FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Curso selecionado: " + cursoSelecionado.getNome(), null));
		}
	}
}
