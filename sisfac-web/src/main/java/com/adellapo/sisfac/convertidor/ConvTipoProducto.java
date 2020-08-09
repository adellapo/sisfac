package com.adellapo.sisfac.convertidor;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.adellapo.sisfac.entidad.TipoProducto;
import com.adellapo.sisfac.negocio.TipoProductoFacade;

@ManagedBean(name = "convTipPro")
public class ConvTipoProducto implements Converter {

	@EJB
	private TipoProductoFacade adminTipoProducto;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		try {

			if (!value.equals("")) {

				TipoProducto tipPro = adminTipoProducto.consultarPorId(Integer.valueOf(value));

				return tipPro;

			} else {

				return null;

			}

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {

			TipoProducto tipPro = (TipoProducto) value;

			return String.valueOf(tipPro.getTipproCodigo());

		} else {

			return null;

		}

	}

}
