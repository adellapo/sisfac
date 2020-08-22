package com.adellapo.sisfac.convertidor;

import java.io.FileOutputStream;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.adellapo.sisfac.entidad.Producto;
import com.adellapo.sisfac.entidad.TipoProducto;
import com.adellapo.sisfac.negocio.ProductoFacade;
import com.adellapo.sisfac.negocio.TipoProductoFacade;

@ManagedBean(name = "convPro")
public class ConvProducto implements Converter {

	@EJB
	private ProductoFacade adminProducto;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		try {

			if (!value.equals("")) {

				// TODO: BORRAR DESDE ACA
				try {
					System.out.println("ESTOY EN getAsObject");
					System.out.print("value: ");
					System.out.print(value);
					System.out.println("Integer.valueOf(value) ===> " + Integer.valueOf(value));
				} catch (Exception e) {
					System.out.println("ERRRRRRRRRRRRROR getAsObject");
				}
				// BORRAR HASTA ACA

				Producto pro = adminProducto.consultarPorId(Integer.valueOf(value));

				return pro;

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

			Producto pro = (Producto) value;
			
			// TODO: BORRAR DESDE ACA
			try {
				System.out.println("ESTOY EN getAsString");
				System.out.println("value ===> " + value.toString());
				System.out.println("pro.getProCodigo() ===> " + pro.getProCodigo());
			} catch (Exception e) {
				System.out.println("ERRRRRRRRRRRRROR getAsString");
			}
			// BORRAR HASTA ACA
			
			return String.valueOf(pro.getProCodigo());

		} else {

			return null;

		}

	}

}
