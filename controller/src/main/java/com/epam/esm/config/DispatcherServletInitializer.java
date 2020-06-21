package com.epam.esm.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration;

/**
 * Class DispatcherServletInitializer  for task 2.
 *
 * @author KIR TANANUSHKA
 * @version 1.0
 */
public class DispatcherServletInitializer
				extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * Method getRootConfigClasses returns the root config classes.
	 *
	 * @return the rootConfigClasses (type Class&lt;?&gt;[]).
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[0];
	}

	/**
	 * Method getServletConfigClasses returns the servlet config classes.
	 *
	 * @return the servletConfigClasses (type Class&lt;?&gt;[]).
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{ModelSpringConfig.class};
	}

	/**
	 * Method getServletMappings returns the servlet mappings.
	 *
	 * @return the servletMappings (type String[]).
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	/**
	 * Method customizeRegistration.
	 *
	 * @param registration of type Dynamic
	 */
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}
}
