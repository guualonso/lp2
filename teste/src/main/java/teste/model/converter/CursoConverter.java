package teste.model.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import lombok.extern.log4j.Log4j;
import teste.model.Curso;
import teste.model.dao.CursoDao;
import teste.util.cdi.CDIServiceLocator;

@Log4j
@FacesConverter(forClass=Curso.class)
public class CursoConverter implements Converter<Object> {

	@Inject
	private CursoDao cursoDao;
	
	public CursoConverter() {
		this.cursoDao = CDIServiceLocator.getBean(CursoDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Curso retorno = null;
		if (value != null && !value.isEmpty()) {
			retorno = this.cursoDao.buscarPeloCodigo(Long.valueOf(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long id = ((Curso) value).getId();
			String retorno = (id == null ? null : id.toString());			
			return retorno;
		}		
		return "";
	}

}
