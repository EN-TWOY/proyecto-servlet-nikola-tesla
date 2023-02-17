package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import utils.MysqlDBConexion;

/**
 * Servlet implementation class ServletReporte
 */
@WebServlet("/ServletReporte")
public class ServletReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReporte() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipo = request.getParameter("tipo");
		
		if (tipo.equals("materia")) {
			materia(request, response);
		} else if (tipo.equals("administrador")) {
			administrador(request, response);
		} else if (tipo.equals("estudiante")) {
			estudiante(request, response);
		} else if (tipo.equals("profesor")) {
			profesor(request, response);
		} else if (tipo.equals("carrera")) {
			carrera(request, response);
		} else if (tipo.equals("contrato")) {
			contrato(request, response);
		} else if (tipo.equals("matricula")) {
			matricula(request, response);
		}
	}
	
	protected void materia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// tipo_de_contenido
		response.setContentType("application/pdf");

		// visualizar_pdf
		ServletOutputStream out = response.getOutputStream();

		// conexionBD
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from materia ";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			Document documento = new Document();

			PdfWriter.getInstance(documento, out);

			// abrir_documento
			documento.open();

			/*
			 * ******************
			 * contenidoDocumento
			 * ******************
			 */
			
			// nombreUni
			Paragraph uni = new Paragraph();
			uni.setFont(FontFactory.getFont("Helvetica", 13, Font.BOLD, BaseColor.BLUE));
			uni.setAlignment(Paragraph.ALIGN_CENTER);
			uni.add("UNIVERSIDAD NIKOLA TESLA");
			uni.add(new Phrase(Chunk.NEWLINE));

			// imgLogo
			Image logo = Image.getInstance("C:\\Users\\Usuario\\Desktop\\ggg\\Proyecto - LPII - Universidad Nikola Tesla\\src\\main\\webapp\\img\\tesla.png");
			logo.scaleToFit(70, 70);
			logo.setAlignment(Chunk.ALIGN_JUSTIFIED_ALL);

			// correoUni
			Paragraph correo = new Paragraph();
			correo.setAlignment(Paragraph.ALIGN_RIGHT);
			correo.add(new Phrase(Chunk.NEWLINE));
			correo.add("institucion@nikolatesla.edu.pe");

			// fecha
			Paragraph fecha = new Paragraph();
			fecha.setAlignment(Paragraph.ALIGN_RIGHT);
			/*---obtenerFechaActual------*/
			DateFormat objFecha = new SimpleDateFormat("yyyy-MM-dd");
			String date = objFecha.format(new Date());
			fecha.add("Fecha: " + date);
			/*---------------------------*/

			// hora
			Paragraph hora = new Paragraph();
			hora.setAlignment(Paragraph.ALIGN_RIGHT);
			/*---obtenerHoraActual------*/
			DateFormat objHora = new SimpleDateFormat("hh:mm:ss");
			String hour = objHora.format(new Date());
			hora.add("Hora: " + hour);
			/*---------------------------*/
			hora.add(new Phrase(Chunk.NEWLINE));
			hora.add(new Phrase(Chunk.NEWLINE));
			
			
			// line01
			Paragraph line01 = new Paragraph();
			line01.setAlignment(Paragraph.ALIGN_CENTER);
			line01.add("____________________________________________________________________________");

			// titulo
			Paragraph titulo = new Paragraph();
			titulo.setFont(FontFactory.getFont("Tahoma", 17, Font.BOLD, BaseColor.DARK_GRAY));
			titulo.setAlignment(Paragraph.ALIGN_CENTER);
			titulo.add(new Phrase(Chunk.NEWLINE));
			titulo.add("Reporte De Informe De Materias");
			titulo.add(new Phrase(Chunk.NEWLINE));
			titulo.add(new Phrase(Chunk.NEWLINE));

			// visualizar_los_datos_en_el_pdf
			documento.add(uni);
			documento.add(logo);
			documento.add(correo);
			documento.add(fecha);
			documento.add(hora);
			documento.add(line01);
			documento.add(titulo);

			/*
			 * *****************
			 */

			// tablaColumnas
			PdfPTable tabla = new PdfPTable(2);
			// porcentajeTabla
			tabla.setWidthPercentage(90);
			
			// 1raCelda
			PdfPCell celda1 = new PdfPCell(new Paragraph("ID", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda1.setColspan(1);
			// alineamos_la_celda_al_centro
			celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda1.setPadding(7.0f);
			// colocamos_un_color_de_fondo_celda
			celda1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda1);

			// 2daCelda
			PdfPCell celda2 = new PdfPCell(new Paragraph("DESCRIPCIÓN", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda2.setColspan(1);
			// alineamos_la_celda_al_centro
			celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda2.setPadding(7.0f);
			// colocamos_un_color_de_fondo_celda
			celda2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda2);

			if (rs.next()) {
				do {
					// Establecer_el_color_de_fondo_a_las_columnas
					tabla.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
					// centrar_las_columnas
					tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

					// listarFilas
					tabla.addCell(rs.getString(1));
					tabla.addCell(rs.getString(2));
				} while (rs.next());

				// visualizar_la_tabla_en_el_pdf
				documento.add(tabla);
			}

			// cerrar_documento
			documento.close();

			// msjReporte
			System.out.println("Reporte PDF generado: Materia");

		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	protected void administrador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// tipo_de_contenido
		response.setContentType("application/pdf");

		// visualizar_pdf
		ServletOutputStream out = response.getOutputStream();

		// conexionBD
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from administrador ";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			Document documento = new Document();

			PdfWriter.getInstance(documento, out);

			// abrir_documento
			documento.open();

			/*
			 * *********************
			 * contenidoDelDocumento
			 * *********************
			 */

			// titulo
			Paragraph unin = new Paragraph();
			unin.setFont(FontFactory.getFont("Helvetica", 13, Font.BOLD, BaseColor.BLUE));
			unin.setAlignment(Paragraph.ALIGN_CENTER);
			unin.add("UNIVERSIDAD NIKOLA TESLA");
			unin.add(new Phrase(Chunk.NEWLINE));
			
			// imgLogo
			Image logo = Image.getInstance("C:\\Users\\Usuario\\Desktop\\ggg\\Proyecto - LPII - Universidad Nikola Tesla\\src\\main\\webapp\\img\\tesla.png");
			// imgTamanio
			logo.scaleToFit(70, 70);
			// imgAlineacion
			logo.setAlignment(Chunk.ALIGN_JUSTIFIED_ALL);
			
			// correoUni
			Paragraph correo = new Paragraph();
			correo.add(new Phrase(Chunk.NEWLINE));
			correo.setAlignment(Paragraph.ALIGN_RIGHT);
			correo.add("institucion@nikolatesla.edu.pe");

			// fechaActual
			Paragraph fecha = new Paragraph();
			fecha.setAlignment(Paragraph.ALIGN_RIGHT);
			/*---obtenerFecha------*/
			DateFormat objFecha = new SimpleDateFormat("yyyy-MM-dd");
			String date = objFecha.format(new Date());
			fecha.add("Fecha: " + date);
			/*---------------------------*/

			// horaActual
			Paragraph hora = new Paragraph();
			hora.setAlignment(Paragraph.ALIGN_RIGHT);
			/*---obtenerHora------*/
			DateFormat objHora = new SimpleDateFormat("hh:mm:ss");
			String hour = objHora.format(new Date());
			hora.add("Hora: " + hour);
			hora.add(new Phrase(Chunk.NEWLINE));
			hora.add(new Phrase(Chunk.NEWLINE));
			/*---------------------------*/

			// line01
			Paragraph line01 = new Paragraph();
			line01.setAlignment(Paragraph.ALIGN_CENTER);
			line01.add("____________________________________________________________________________");
			
			// titulo
			Paragraph titulo = new Paragraph();
			titulo.setFont(FontFactory.getFont("Tahoma", 15, Font.BOLD, BaseColor.DARK_GRAY));
			titulo.setAlignment(Paragraph.ALIGN_CENTER);
			titulo.add(new Phrase(Chunk.NEWLINE));
			titulo.add("Reporte De Informe De Administradores");
			titulo.add(new Phrase(Chunk.NEWLINE));
			titulo.add(new Phrase(Chunk.NEWLINE));

			// visualizar_los_datos_en_el_pdf
			documento.add(unin);
			documento.add(logo);
			documento.add(correo);
			documento.add(fecha);
			documento.add(hora);
			documento.add(line01);
			documento.add(titulo);

			/*
			 * *****************
			 */

			// tablaColumnas
			PdfPTable tabla = new PdfPTable(10);
			// porcentajeTabla
			tabla.setWidthPercentage(110);

			// 1raCelda
			PdfPCell celda1 = new PdfPCell(new Paragraph("ID", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda1.setColspan(1);
			// alineamos_la_celda_al_centro
			celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda1.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda1);

			// 2daCelda
			PdfPCell celda2 = new PdfPCell(new Paragraph("NOMBRE", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda2.setColspan(1);
			// alineamos_la_celda_al_centro
			celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda2.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda2);

			// 4daCelda
			PdfPCell celda4 = new PdfPCell(new Paragraph("DNI", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda4.setColspan(1);
			// alineamos_la_celda_al_centro
			celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda4.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda4);

			// 5daCelda
			PdfPCell celda5 = new PdfPCell(new Paragraph("GENERO", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda5.setColspan(1);
			// alineamos_la_celda_al_centro
			celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda5.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda5);

			// 6daCelda
			PdfPCell celda6 = new PdfPCell(new Paragraph("NACIMIENTO", FontFactory.getFont("arial", 5, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda6.setColspan(1);
			// alineamos_la_celda_al_centro
			celda6.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda6.setPadding(10.0f);
			// colocamos_un_color_de_fondo_celda
			celda6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda6);

			// 7daCelda
			PdfPCell celda7 = new PdfPCell(new Paragraph("CORREO", FontFactory.getFont("arial", 7, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda7.setColspan(1);
			// alineamos_la_celda_al_centro
			celda7.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda7.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda7.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda7);

			// 8vaCelda
			PdfPCell celda8 = new PdfPCell(new Paragraph("TELEFONO", FontFactory.getFont("arial", 7, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda8.setColspan(1);
			// alineamos_la_celda_al_centro
			celda8.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda8.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda8.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda8);

			// 9naCelda
			PdfPCell celda9 = new PdfPCell(new Paragraph("DIRECCION", FontFactory.getFont("arial", 7, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda9.setColspan(1);
			// alineamos_la_celda_al_centro
			celda9.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda9.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda9.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda9);

			// 10maCelda
			PdfPCell celda10 = new PdfPCell(new Paragraph("USUARIO", FontFactory.getFont("arial", 7, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda10.setColspan(1);
			// alineamos_la_celda_al_centro
			celda10.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda10.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda10.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda10);

			// 11vaCelda
			PdfPCell celda11 = new PdfPCell(new Paragraph("CONTRASEÑA", FontFactory.getFont("arial", 5, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda11.setColspan(1);
			// alineamos_la_celda_al_centro
			celda11.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda11.setPadding(10.0f);
			// colocamos_un_color_de_fondo_celda
			celda11.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda11);

			if (rs.next()) {
				do {
					// Establecer_el_color_de_fondo_a_las_columnas
					tabla.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
					// centrar_las_columnas
					tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

					// listarFilas
					tabla.addCell(rs.getString(1));
					tabla.addCell(rs.getString(2) + " " + rs.getString(3));
					tabla.addCell(rs.getString(4).toLowerCase());
					tabla.addCell(rs.getString(5));
					tabla.addCell(rs.getString(6));
					tabla.addCell(rs.getString(7));
					tabla.addCell(rs.getString(8));
					tabla.addCell(rs.getString(9));
					tabla.addCell(rs.getString(10));
					tabla.addCell(rs.getString(11));
				} while (rs.next());

				// visualizar_la_tabla_en_el_pdf
				documento.add(tabla);
			}

			// cerrar_documento
			documento.close();

			// msjReporte
			System.out.println("Reporte PDF generado: Administrador");

		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}	
	
	protected void estudiante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// tipo_de_contenido
		response.setContentType("application/pdf");

		// visualizar_pdf
		ServletOutputStream out = response.getOutputStream();

		// conexionBD
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from estudiante ";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			Document documento = new Document();

			PdfWriter.getInstance(documento, out);

			// abrir_documento
			documento.open();

			/*
			 * ********************* 
			 * contenidoDelDocumento 
			 * *********************
			 */

			// titulo
			Paragraph uni = new Paragraph();
			uni.setFont(FontFactory.getFont("Helvetica", 13, Font.BOLD, BaseColor.BLUE));
			uni.setAlignment(Paragraph.ALIGN_CENTER);
			uni.add("UNIVERSIDAD NIKOLA TESLA");
			uni.add(new Phrase(Chunk.NEWLINE));

			// imgLogo
			Image logo = Image.getInstance("C:\\Users\\Usuario\\Desktop\\ggg\\Proyecto - LPII - Universidad Nikola Tesla\\src\\main\\webapp\\img\\tesla.png");
			// imgTamanio
			logo.scaleToFit(70, 70);
			// imgAlineacion
			logo.setAlignment(Chunk.ALIGN_JUSTIFIED_ALL);

			// correoUni
			Paragraph correo = new Paragraph();
			correo.add(new Phrase(Chunk.NEWLINE));
			correo.setAlignment(Paragraph.ALIGN_RIGHT);
			correo.add("institucion@nikolatesla.edu.pe");

			// fechaActual
			Paragraph fecha = new Paragraph();
			fecha.setAlignment(Paragraph.ALIGN_RIGHT);
			/*---obtenerFecha------*/
			DateFormat objFecha = new SimpleDateFormat("yyyy-MM-dd");
			String date = objFecha.format(new Date());
			fecha.add("Fecha: " + date);
			/*---------------------------*/

			// horaActual
			Paragraph hora = new Paragraph();
			hora.setAlignment(Paragraph.ALIGN_RIGHT);
			/*---obtenerHora------*/
			DateFormat objHora = new SimpleDateFormat("hh:mm:ss");
			String hour = objHora.format(new Date());
			hora.add("Hora: " + hour);
			hora.add(new Phrase(Chunk.NEWLINE));
			hora.add(new Phrase(Chunk.NEWLINE));
			/*---------------------------*/

			// line01
			Paragraph line01 = new Paragraph();
			line01.setAlignment(Paragraph.ALIGN_CENTER);
			line01.add("____________________________________________________________________________");

			// titulo
			Paragraph titulo = new Paragraph();
			titulo.setFont(FontFactory.getFont("Tahoma", 15, Font.BOLD, BaseColor.DARK_GRAY));
			titulo.setAlignment(Paragraph.ALIGN_CENTER);
			titulo.add(new Phrase(Chunk.NEWLINE));
			titulo.add("Reporte De Informe De Estudiantes");
			titulo.add(new Phrase(Chunk.NEWLINE));
			titulo.add(new Phrase(Chunk.NEWLINE));

			// visualizar_los_datos_en_el_pdf
			documento.add(uni);
			documento.add(logo);
			documento.add(correo);
			documento.add(fecha);
			documento.add(hora);
			documento.add(line01);
			documento.add(titulo);

			/*
			 * *****************
			 */

			// tablaColumnas
			PdfPTable tabla = new PdfPTable(9);
			// porcentajeTabla
			tabla.setWidthPercentage(110);

			// 1raCelda
			PdfPCell celda1 = new PdfPCell(new Paragraph("ID", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda1.setColspan(1);
			// alineamos_la_celda_al_centro
			celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda1.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda1);

			// 2daCelda
			PdfPCell celda2 = new PdfPCell(new Paragraph("NOMBRE", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda2.setColspan(1);
			// alineamos_la_celda_al_centro
			celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda2.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda2);
			
			// 2daCelda
			PdfPCell celda3 = new PdfPCell(new Paragraph("APELLIDO", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda3.setColspan(1);
			// alineamos_la_celda_al_centro
			celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda3.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda3);

			// 4daCelda
			PdfPCell celda4 = new PdfPCell(new Paragraph("DNI", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda4.setColspan(1);
			// alineamos_la_celda_al_centro
			celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda4.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda4);

			// 5daCelda
			PdfPCell celda5 = new PdfPCell(new Paragraph("GENERO", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda5.setColspan(1);
			// alineamos_la_celda_al_centro
			celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda5.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda5);

			// 6daCelda
			PdfPCell celda6 = new PdfPCell(new Paragraph("NACIMIENTO", FontFactory.getFont("arial", 6, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda6.setColspan(1);
			// alineamos_la_celda_al_centro
			celda6.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda6.setPadding(10.0f);
			// colocamos_un_color_de_fondo_celda
			celda6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda6);

			// 7daCelda
			PdfPCell celda7 = new PdfPCell(new Paragraph("CORREO", FontFactory.getFont("arial", 7, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda7.setColspan(1);
			// alineamos_la_celda_al_centro
			celda7.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda7.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda7.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda7);

			// 8vaCelda
			PdfPCell celda8 = new PdfPCell(new Paragraph("TELEFONO", FontFactory.getFont("arial", 7, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda8.setColspan(1);
			// alineamos_la_celda_al_centro
			celda8.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda8.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda8.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda8);

			// 9naCelda
			PdfPCell celda9 = new PdfPCell(new Paragraph("DIRECCION", FontFactory.getFont("arial", 7, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda9.setColspan(1);
			// alineamos_la_celda_al_centro
			celda9.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda9.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda9.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda9);

			if (rs.next()) {
				do {
					// Establecer_el_color_de_fondo_a_las_columnas
					tabla.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
					// centrar_las_columnas
					tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

					// listarFilas
					tabla.addCell(rs.getString(1));
					tabla.addCell(rs.getString(2));
					tabla.addCell(rs.getString(3));
					tabla.addCell(rs.getString(4));
					tabla.addCell(rs.getString(5));
					tabla.addCell(rs.getString(6));
					tabla.addCell(rs.getString(7));
					tabla.addCell(rs.getString(8));
					tabla.addCell(rs.getString(9));
				} while (rs.next());

				// visualizar_la_tabla_en_el_pdf
				documento.add(tabla);
			}

			// cerrar_documento
			documento.close();

			// msjReporte
			System.out.println("Reporte PDF generado: Estudiante");

		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	protected void profesor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// tipo_de_contenido
		response.setContentType("application/pdf");

		// visualizar_pdf
		ServletOutputStream out = response.getOutputStream();

		// conexionBD
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from profesor ";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			Document documento = new Document();

			PdfWriter.getInstance(documento, out);

			// abrir_documento
			documento.open();

			/*
			 * ********************* 
			 * contenidoDelDocumento 
			 * *********************
			 */

			// titulo
			Paragraph uni = new Paragraph();
			uni.setFont(FontFactory.getFont("Helvetica", 13, Font.BOLD, BaseColor.BLUE));
			uni.setAlignment(Paragraph.ALIGN_CENTER);
			uni.add("UNIVERSIDAD NIKOLA TESLA");
			uni.add(new Phrase(Chunk.NEWLINE));

			// imgLogo
			Image logo = Image.getInstance("C:\\Users\\Usuario\\Desktop\\ggg\\Proyecto - LPII - Universidad Nikola Tesla\\src\\main\\webapp\\img\\tesla.png");
			// imgTamanio
			logo.scaleToFit(70, 70);
			// imgAlineacion
			logo.setAlignment(Chunk.ALIGN_JUSTIFIED_ALL);

			// correoUni
			Paragraph correo = new Paragraph();
			correo.add(new Phrase(Chunk.NEWLINE));
			correo.setAlignment(Paragraph.ALIGN_RIGHT);
			correo.add("institucion@nikolatesla.edu.pe");

			// fechaActual
			Paragraph fecha = new Paragraph();
			fecha.setAlignment(Paragraph.ALIGN_RIGHT);
			/*---obtenerFecha------*/
			DateFormat objFecha = new SimpleDateFormat("yyyy-MM-dd");
			String date = objFecha.format(new Date());
			fecha.add("Fecha: " + date);
			/*---------------------------*/

			// horaActual
			Paragraph hora = new Paragraph();
			hora.setAlignment(Paragraph.ALIGN_RIGHT);
			/*---obtenerHora------*/
			DateFormat objHora = new SimpleDateFormat("hh:mm:ss");
			String hour = objHora.format(new Date());
			hora.add("Hora: " + hour);
			hora.add(new Phrase(Chunk.NEWLINE));
			hora.add(new Phrase(Chunk.NEWLINE));
			/*---------------------------*/

			// line01
			Paragraph line01 = new Paragraph();
			line01.setAlignment(Paragraph.ALIGN_CENTER);
			line01.add("____________________________________________________________________________");

			// titulo
			Paragraph titulo = new Paragraph();
			titulo.setFont(FontFactory.getFont("Tahoma", 15, Font.BOLD, BaseColor.DARK_GRAY));
			titulo.setAlignment(Paragraph.ALIGN_CENTER);
			titulo.add(new Phrase(Chunk.NEWLINE));
			titulo.add("Reporte De Informe De Profesores");
			titulo.add(new Phrase(Chunk.NEWLINE));
			titulo.add(new Phrase(Chunk.NEWLINE));

			// visualizar_los_datos_en_el_pdf
			documento.add(uni);
			documento.add(logo);
			documento.add(correo);
			documento.add(fecha);
			documento.add(hora);
			documento.add(line01);
			documento.add(titulo);

			/*
			 * *****************
			 */

			// tablaColumnas
			PdfPTable tabla = new PdfPTable(9);
			// porcentajeTabla
			tabla.setWidthPercentage(110);

			// 1raCelda
			PdfPCell celda1 = new PdfPCell(new Paragraph("ID", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda1.setColspan(1);
			// alineamos_la_celda_al_centro
			celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda1.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda1);

			// 2daCelda
			PdfPCell celda2 = new PdfPCell(new Paragraph("NOMBRE", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda2.setColspan(1);
			// alineamos_la_celda_al_centro
			celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda2.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda2);

			// 2daCelda
			PdfPCell celda3 = new PdfPCell(new Paragraph("APELLIDO", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda3.setColspan(1);
			// alineamos_la_celda_al_centro
			celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda3.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda3);

			// 4daCelda
			PdfPCell celda4 = new PdfPCell(new Paragraph("DNI", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda4.setColspan(1);
			// alineamos_la_celda_al_centro
			celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda4.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda4);

			// 5daCelda
			PdfPCell celda5 = new PdfPCell(new Paragraph("GENERO", FontFactory.getFont("arial", 8, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda5.setColspan(1);
			// alineamos_la_celda_al_centro
			celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda5.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda5);

			// 6daCelda
			PdfPCell celda6 = new PdfPCell(new Paragraph("NACIMIENTO", FontFactory.getFont("arial", 6, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda6.setColspan(1);
			// alineamos_la_celda_al_centro
			celda6.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda6.setPadding(10.0f);
			// colocamos_un_color_de_fondo_celda
			celda6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda6);

			// 7daCelda
			PdfPCell celda7 = new PdfPCell(new Paragraph("CORREO", FontFactory.getFont("arial", 7, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda7.setColspan(1);
			// alineamos_la_celda_al_centro
			celda7.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda7.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda7.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda7);

			// 8vaCelda
			PdfPCell celda8 = new PdfPCell(new Paragraph("TELEFONO", FontFactory.getFont("arial", 7, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda8.setColspan(1);
			// alineamos_la_celda_al_centro
			celda8.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda8.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda8.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda8);

			// 9naCelda
			PdfPCell celda9 = new PdfPCell(new Paragraph("DIRECCION", FontFactory.getFont("arial", 7, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda9.setColspan(1);
			// alineamos_la_celda_al_centro
			celda9.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda9.setPadding(8.0f);
			// colocamos_un_color_de_fondo_celda
			celda9.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda9);

			if (rs.next()) {
				do {
					// Establecer_el_color_de_fondo_a_las_columnas
					tabla.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
					// centrar_las_columnas
					tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

					// listarFilas
					tabla.addCell(rs.getString(1));
					tabla.addCell(rs.getString(2));
					tabla.addCell(rs.getString(3));
					tabla.addCell(rs.getString(4));
					tabla.addCell(rs.getString(5));
					tabla.addCell(rs.getString(6));
					tabla.addCell(rs.getString(7));
					tabla.addCell(rs.getString(8));
					tabla.addCell(rs.getString(9));
				} while (rs.next());

				// visualizar_la_tabla_en_el_pdf
				documento.add(tabla);
			}

			// cerrar_documento
			documento.close();

			// msjReporte
			System.out.println("Reporte PDF generado: Profesores");

		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	protected void carrera(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// tipo_de_contenido
		response.setContentType("application/pdf");

		// visualizar_pdf
		ServletOutputStream out = response.getOutputStream();

		// conexionBD
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			cn = MysqlDBConexion.getConexion();
			String sql = "select * from carrera ";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			Document documento = new Document();

			PdfWriter.getInstance(documento, out);

			// abrir_documento
			documento.open();

			/*
			 * ****************** contenidoDocumento ******************
			 */

			// nombreUni
			Paragraph uni = new Paragraph();
			uni.setFont(FontFactory.getFont("Helvetica", 13, Font.BOLD, BaseColor.BLUE));
			uni.setAlignment(Paragraph.ALIGN_CENTER);
			uni.add("UNIVERSIDAD NIKOLA TESLA");
			uni.add(new Phrase(Chunk.NEWLINE));

			// imgLogo
			Image logo = Image.getInstance("C:\\Users\\Usuario\\Desktop\\ggg\\Proyecto - LPII - Universidad Nikola Tesla\\src\\main\\webapp\\img\\tesla.png");
			logo.scaleToFit(70, 70);
			logo.setAlignment(Chunk.ALIGN_JUSTIFIED_ALL);

			// correoUni
			Paragraph correo = new Paragraph();
			correo.setAlignment(Paragraph.ALIGN_RIGHT);
			correo.add(new Phrase(Chunk.NEWLINE));
			correo.add("institucion@nikolatesla.edu.pe");

			// fecha
			Paragraph fecha = new Paragraph();
			fecha.setAlignment(Paragraph.ALIGN_RIGHT);
			/*---obtenerFechaActual------*/
			DateFormat objFecha = new SimpleDateFormat("yyyy-MM-dd");
			String date = objFecha.format(new Date());
			fecha.add("Fecha: " + date);
			/*---------------------------*/

			// hora
			Paragraph hora = new Paragraph();
			hora.setAlignment(Paragraph.ALIGN_RIGHT);
			/*---obtenerHoraActual------*/
			DateFormat objHora = new SimpleDateFormat("hh:mm:ss");
			String hour = objHora.format(new Date());
			hora.add("Hora: " + hour);
			/*---------------------------*/
			hora.add(new Phrase(Chunk.NEWLINE));
			hora.add(new Phrase(Chunk.NEWLINE));

			// line01
			Paragraph line01 = new Paragraph();
			line01.setAlignment(Paragraph.ALIGN_CENTER);
			line01.add("____________________________________________________________________________");

			// titulo
			Paragraph titulo = new Paragraph();
			titulo.setFont(FontFactory.getFont("Tahoma", 17, Font.BOLD, BaseColor.DARK_GRAY));
			titulo.setAlignment(Paragraph.ALIGN_CENTER);
			titulo.add(new Phrase(Chunk.NEWLINE));
			titulo.add("Reporte De Informe De Carreras");
			titulo.add(new Phrase(Chunk.NEWLINE));
			titulo.add(new Phrase(Chunk.NEWLINE));

			// visualizar_los_datos_en_el_pdf
			documento.add(uni);
			documento.add(logo);
			documento.add(correo);
			documento.add(fecha);
			documento.add(hora);
			documento.add(line01);
			documento.add(titulo);

			/*
			 * *****************
			 */

			// tablaColumnas
			PdfPTable tabla = new PdfPTable(2);
			// porcentajeTabla
			tabla.setWidthPercentage(90);

			// 1raCelda
			PdfPCell celda1 = new PdfPCell(new Paragraph("ID", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda1.setColspan(1);
			// alineamos_la_celda_al_centro
			celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda1.setPadding(7.0f);
			// colocamos_un_color_de_fondo_celda
			celda1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda1);

			// 2daCelda
			PdfPCell celda2 = new PdfPCell(new Paragraph("DESCRIPCIÓN", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));
			// unimos_esta_celda_con_otras
			celda2.setColspan(1);
			// alineamos_la_celda_al_centro
			celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
			// añadimos_un_espaciado
			celda2.setPadding(7.0f);
			// colocamos_un_color_de_fondo_celda
			celda2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			// se_añade_a_la_tabla
			tabla.addCell(celda2);

			if (rs.next()) {
				do {
					// Establecer_el_color_de_fondo_a_las_columnas
					tabla.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
					// centrar_las_columnas
					tabla.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

					// listarFilas
					tabla.addCell(rs.getString(1));
					tabla.addCell(rs.getString(2));
				} while (rs.next());

				// visualizar_la_tabla_en_el_pdf
				documento.add(tabla);
			}

			// cerrar_documento
			documento.close();

			// msjReporte
			System.out.println("Reporte PDF generado: Carrera");

		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (cn != null)
					cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	protected void contrato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
	protected void matricula(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
	}
}
