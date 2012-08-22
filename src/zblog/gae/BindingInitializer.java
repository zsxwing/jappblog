package zblog.gae;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import freemarker.log.Logger;

public class BindingInitializer implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(String.class,
				new StringTrimmerEditor(false));
		binder.registerCustomEditor(Boolean.class, new BooleanEditor());
		binder.registerCustomEditor(Long.class, new LongEditor());
	}

	public class BooleanEditor extends PropertyEditorSupport {

		public void setAsText(String text) {
			if (text == null) {
				setValue(Boolean.FALSE);
			} else {
				String value = text.trim();
				setValue(Boolean.valueOf((value)));
			}
		}

		public String getAsText() {
			Object value = getValue();
			return (value != null ? value.toString() : "false");
		}

	}

	public class LongEditor extends PropertyEditorSupport {

		private Logger logger = Logger.getLogger(LongEditor.class.getName());

		public void setAsText(String text) {
			logger.warn(text);
			
			if (text == null) {
				setValue(null);
			} else {
				String value = text.trim();
				try {
					setValue(Long.valueOf((value)));
				} catch (NumberFormatException e) {
					setValue(null);
				}
			}
		}

		public String getAsText() {
			Object value = getValue();
			return value.toString();
		}
	}

}
