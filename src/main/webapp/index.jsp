<%@ page import="java.sql.Connection"%> 
<%@ page import="utils.MysqlDBConexion"%> 
<%@ page import="java.sql.Statement"%> 
<%@ page import="java.sql.ResultSet"%> 
<%@page import="beans.AdministradorDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nikola Tesla - Inicio</title>

	<link rel="icon" href="img/rayo.png">
    <link href="css/styles.css" rel="stylesheet"/>
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"  crossorigin="anonymous"></script>

</head>
<body>
		<%	
			String adminNombre = (String) request.getAttribute("adminNombre");
			String adminApellido = (String) request.getAttribute("adminApellido");
			String adminDni = (String) request.getAttribute("adminDni");
			String adminGenero = (String) request.getAttribute("adminGenero");
			String adminFecha = (String) request.getAttribute("adminFecha");
			String adminEmail = (String) request.getAttribute("adminEmail");
			String adminTelefono = (String) request.getAttribute("adminTelefono");
			String adminDireccion = (String) request.getAttribute("adminDireccion");
		%>
	
	<!-- navegacionSuperior -->
	<nav id="index" class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- logoPagina -->
		<a class="navbar-brand text-center fw-bold" href="index.jsp"> 
			NIKOLA TESLA
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lightning-fill" viewBox="0 0 16 16">
  			<path d="M5.52.359A.5.5 0 0 1 6 0h4a.5.5 0 0 1 .474.658L8.694 6H12.5a.5.5 0 0 1 .395.807l-7 9a.5.5 0 0 1-.873-.454L6.823 9.5H3.5a.5.5 0 0 1-.48-.641l2.5-8.5z"/>
			</svg>
		</a>
		
		<!-- btnMenu-->
		<button class="btn btn-link btn-xl order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
		
		<!-- barraNavegacionBusqueda-->
		<form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
			<div class="input-group">
				<input class="form-control" type="text" placeholder="Busqueda..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
				<button class="btn btn-secondary" id="btnNavbarSearch" type="button"> 
					<i class="fas fa-search"></i>
				</button>
			</div>
		</form>
		
		<!-- mostrarNombreAdministrador-->
		<div class="m-1"> 
			 <a class="text-decoration-none text-white">Bienvenido: </a>
			 <a class="text-decoration-none text-white">
				<!-- obtenerNombreAdministrador -->			 	
			 	<label> <%= adminNombre + " " + adminApellido %> </label>		 	
			</a>
		</div>
		
		<!-- perfilAdministrador-->
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
				role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
					class="fas fa-user fa-fw"></i></a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown">
					<li>
						<a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#exampleModal" style="cursor:pointer">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
  								<path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
							</svg>
						Administrador 
						</a>
					</li>
					<li><hr class="dropdown-divider"/></li>
					<li class="">
						<a class="dropdown-item" href="login.jsp" style="cursor:pointer">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
  								<path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
  								<path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
							</svg>
						Cerrar Sesion 
						</a>
					</li>
				</ul>
			</li>
		</ul>
	</nav>

	<div id="layoutSidenav">
		<!-- navegacionVertical -->
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                        	<!-- tituloBienvenido -->
                            <div class="sb-sidenav-menu-heading">Bienvenido</div>
                            
                        	<!-- menuPrincipal -->                            
                            <a class="nav-link" href="index.jsp">
                                <div class="sb-nav-link-icon">
                                	<svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" fill="currentColor" class="bi bi-microsoft" viewBox="0 0 18 18">
  									<path d="M7.462 0H0v7.19h7.462V0zM16 0H8.538v7.19H16V0zM7.462 8.211H0V16h7.462V8.211zm8.538 0H8.538V16H16V8.211z"/>
									</svg>
                                </div>
                                Inicio
                            </a>
                            
                        	<!-- tituloMantenimiento -->
                            <div class="sb-sidenav-menu-heading">Mantenimiento</div>
                            
                        	<!-- listadosTitulo -->
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                	Listados
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            
                        	<!-- enlacesListados -->
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="ServletAdministrador?tipo=listar">Administradores</a>
                                    <a class="nav-link" href="ServletEstudiante?tipo=listar">Estudiantes</a>
                                    <a class="nav-link" href="ServletProfesor?tipo=listar">Profesores</a>
                                    <a class="nav-link" href="ServletCarrera?tipo=listar">Carreras</a>
                                    <a class="nav-link" href="ServletMateria?tipo=listar">Materias</a>
                                    <a class="nav-link" href="ServletMatricula?tipo=listar">Matriculas</a>
                                    <a class="nav-link" href="ServletContrato?tipo=listar">Contratos</a>
                                </nav>
                            </div>
                            
                        	<!-- agregarTitulo -->
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                	Agregar
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            
                        	<!-- enlacesAgregar -->
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                	<a class="nav-link" href="registrarAdministrador.jsp">Administrador</a>
                                	<a class="nav-link" href="registrarEstudiante.jsp">Estudiante</a>
                                	<a class="nav-link" href="registrarProfesor.jsp">Profesor</a>
                                	<a class="nav-link" href="registrarCarrera.jsp">Carrera</a>
                                	<a class="nav-link" href="ServletMateria?tipo=llenar">Materia</a>
                                	<a class="nav-link" href="registrarMatricula.jsp">Matricula</a>
                                	<a class="nav-link" href="registrarContrato.jsp">Contrato</a>
                                </nav>
                            </div>
                            
                        	<!-- tituloMantenimiento -->
                            <div class="sb-sidenav-menu-heading">Reportes</div>
                            
                        	<!-- tituloReporte -->
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayoutsReportes" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                	Reporte
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            
                        	<!-- enlacesReportes -->
                            <div class="collapse" id="collapseLayoutsReportes" aria-labelledby="headingThree" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="ServletReporte?tipo=administrador" target="_blank">Administradores</a>
                                    <a class="nav-link" href="ServletReporte?tipo=estudiante" target="_blank">Estudiantes</a>
                                    <a class="nav-link" href="ServletReporte?tipo=profesor" target="_blank">Profesores</a>
                                    <a class="nav-link" href="ServletReporte?tipo=carrera" target="_blank">Carreras</a>
                                    <a class="nav-link" href="ServletReporte?tipo=materia" target="_blank">Materias</a>
                                    <a class="nav-link" href="ServletReporte?tipo=matricula" target="_blank">Matricula</a>
                                    <a class="nav-link" href="ServletReporte?tipo=contrato" target="_blank">Contrato</a>
                                </nav>
                            </div>
                                                
                        </div>
                    </div>
                    
                    <div class="sb-sidenav-footer mx-2 my-2" style="padding-top: 0.87rem;">

					<div class="small">Copyright © Universidad Nikola Tesla 2022</div>

				</div>                 
                </nav>
            </div>
            
            <!-- contenidoMenu -->
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-0">
                        <div class="my-2 mx-2">
                        
				            <!-- menuPrincipal --> 
				            <div class="d-flex align-items-start justify-content-center">
				            	<div class="position-absolute text-center">
				            		<!-- logoUniversidad -->
									<img class="mt-3" src="img/tesla.png" style="height:175px">
				            		<!-- line -->
	                             	<hr class="fw-bold" style="color:#13325b">
				            		<!-- tituloMenuPrincipal -->
	                             	<h1 class="fw-bold mt-2 mb-5 fs-5" style="color:#13325b;">
	                             		UNIVERSIDAD PARA UN FUTURO MEJOR ILUMINADO
	                             	</h1>
	                             	<br>
	                             	<br>

				            	<!-- ========================================================================================= -->
				            	<!-- contadorRegistros -->
								<div class="container-fluid px-5">
									<div class="row">
																		
										<div class="col-xl-3 col-md-6">
											<div class="card text-white mb-4" style="background: linear-gradient(45deg, #3949AB, #3a6efd);">									
											<%                        
                                            try {
                                            	Connection cn = MysqlDBConexion.getConexion();
                                           	 	Statement stm = cn.createStatement();
												ResultSet rs = stm.executeQuery("select COUNT(*) AS cantidad FROM administrador");											
												if  (!rs.next()) {
													System.out.println("No se encuentran datos disponibles!");
												} else  {
													int canAdmin =  Integer.parseInt(rs.getString("cantidad"));
											%>
												<!-- mostrarCantidad -->
												<div class="card-body fw-bold fs-5"> <%= canAdmin %> </div>
											<%
												}  											
												rs.close();
												cn.close();	
												}  catch (Exception e) {
													out.println("Error En La Conexion!!");
												}
											%>										
												<div class="card-footer d-flex align-items-center justify-content-center">										
													<a class="small text-white text-decoration-none fw-bold">
														Administradores
													</a>
												</div>
											</div>
										</div>
										
										<div class="col-xl-3 col-md-6">									
											<div class="card text-white mb-4" style="background: linear-gradient(45deg, #F57C00, #FFB64D);">
											<%                        
                                            try {
                                            	Connection cn = MysqlDBConexion.getConexion();
                                           	 	Statement stm = cn.createStatement();
												ResultSet rs = stm.executeQuery("select COUNT(*) AS cantidad FROM estudiante");											
												if  (!rs.next()) {
													System.out.println("No se encuentran datos disponibles!");
												} else  {
													int canEstudiante =  Integer.parseInt(rs.getString("cantidad"));
											%>
												<!-- mostrarCantidad -->
												<div class="card-body fw-bold fs-5"> <%= canEstudiante %> </div>
											<%
												}  											
												rs.close();
												cn.close();	
												}  catch (Exception e) {
													out.println("Error En La Conexion!!");
												}
											%>	
												<div class="card-footer d-flex align-items-center justify-content-center">
													<a class="small text-white text-decoration-none fw-bold">
														Estudiantes
													</a>
												</div>
											</div>											
										</div>
										
										<div class="col-xl-3 col-md-6">										
											<div class="card text-white mb-4" style="background: linear-gradient(45deg, #2ca961, #2ed88a);">
											<%                        
                                            try {
                                            	Connection cn = MysqlDBConexion.getConexion();
                                           	 	Statement stm = cn.createStatement();		
												ResultSet rs = stm.executeQuery("select COUNT(*) AS cantidad FROM profesor");											
												if  (!rs.next()) {
													System.out.println("No se encuentran datos disponibles!");
												} else  {
													int canProfe =  Integer.parseInt(rs.getString("cantidad"));
											%>
												<!-- mostrarCantidad -->
												<div class="card-body fw-bold fs-5"> <%= canProfe %> </div>
											<%
												}  											
												rs.close();
												cn.close();	
												}  catch (Exception e) {
													out.println("Error En La Conexion!!");
												}
											%>	
												<div class="card-footer d-flex align-items-center justify-content-center">
													<a class="small text-white text-decoration-none fw-bold">
														Profesores
													</a>
												</div>
											</div>										
										</div>																		
										
										<div class="col-xl-3 col-md-6">				
											<div class="card text-white mb-4" style=" background: linear-gradient(45deg, #494f53, #929a9b);">
											<%                        
                                            try {
                                            	Connection cn = MysqlDBConexion.getConexion();
                                           	 	Statement stm = cn.createStatement();		
												ResultSet rs = stm.executeQuery("select COUNT(*) AS cantidad FROM materia");											
												if  (!rs.next()) {
													System.out.println("No se encuentran datos disponibles!");
												} else  {
													int canMateria =  Integer.parseInt(rs.getString("cantidad"));
											%>
												<!-- mostrarCantidad -->
												<div class="card-body fw-bold fs-5"> <%= canMateria %> </div>
											<%
												}  											
												rs.close();
												cn.close();	
												}  catch (Exception e) {
													out.println("Error En La Conexion!!");
												}
											%>
												<div class="card-footer d-flex align-items-center justify-content-center">
													<a class="small text-white text-decoration-none fw-bold">
														Materias
													</a>
												</div>										
											</div>							
										</div>
										
										<div class="col-xl-4 col-md-6">				
											<div class="card text-white mb-4" style="background: linear-gradient(45deg, #e52d27, #db4b50);">
											<%                        
                                            try {
                                            	Connection cn = MysqlDBConexion.getConexion();
                                           	 	Statement stm = cn.createStatement();		
												ResultSet rs = stm.executeQuery("select COUNT(*) AS cantidad FROM carrera");											
												if  (!rs.next()) {
													System.out.println("No se encuentran datos disponibles!");
												} else  {
													int canCarrera =  Integer.parseInt(rs.getString("cantidad"));
											%>
												<!-- mostrarCantidad -->
												<div class="card-body fw-bold fs-5"> <%= canCarrera %> </div>
											<%
												}  											
												rs.close();
												cn.close();	
												}  catch (Exception e) {
													out.println("Error En La Conexion!!");
												}
											%>
												<div class="card-footer d-flex align-items-center justify-content-center">
													<a class="small text-white text-decoration-none fw-bold">
														Carreras
													</a>
												</div>										
											</div>							
										</div>
										
										<div class="col-xl-4 col-md-6">				
											<div class="card text-white mb-4" style="background: linear-gradient(45deg, #697ffd, #b4c5f3);">
											
												<%                        
	                                            try {
	                                            	Connection cn = MysqlDBConexion.getConexion();
	                                           	 	Statement stm = cn.createStatement();		
													ResultSet rs = stm.executeQuery("select COUNT(*) AS cantidad FROM contrato");											
													if  (!rs.next()) {
														System.out.println("No se encuentran datos disponibles!");
													} else  {
														int canContrato =  Integer.parseInt(rs.getString("cantidad"));
												%>
													<!-- mostrarCantidad -->
													<div class="card-body fw-bold fs-5"> <%= canContrato %> </div>
												<%
													}  											
													rs.close();
													cn.close();	
													}  catch (Exception e) {
														out.println("Error En La Conexion!!");
													}
												%>
												
												<div class="card-footer d-flex align-items-center justify-content-center">
													<a class="small text-white text-decoration-none fw-bold">
														Contratos
													</a>
												</div>										
											</div>							
										</div>
										
										<div class="col-xl-4 col-md-6">				
											<div class="card text-white mb-4" style="background: linear-gradient(45deg, #56b0fa, #98dde9);">
											
												<%                        
	                                            try {
	                                            	Connection cn = MysqlDBConexion.getConexion();
	                                           	 	Statement stm = cn.createStatement();		
													ResultSet rs = stm.executeQuery("select COUNT(*) AS cantidad FROM matricula");											
													if  (!rs.next()) {
														System.out.println("No se encuentran datos disponibles!");
													} else  {
														int canMatricula =  Integer.parseInt(rs.getString("cantidad"));
												%>
													<!-- mostrarCantidad -->
													<div class="card-body fw-bold fs-5"> <%= canMatricula %> </div>
												<%
													}  											
													rs.close();
													cn.close();	
													}  catch (Exception e) {
														out.println("Error En La Conexion!!");
													}
												%>
												
												<div class="card-footer d-flex align-items-center justify-content-center">
													<a class="small text-white text-decoration-none fw-bold">
														Matriculas
													</a>
												</div>										
											</div>							
										</div>
										
									</div>
								</div>
				            	<!-- =============================================================================================== -->					
							</div>
							
				            <!-- imgMenuPrincipal -->
					        <img class="img-fluid" alt="" src="img/principal.jpg">    
					                  
				            </div>
				            				            
				            <!-- ================================================================================================== -->
							<!-- Modal -->
							<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
							  <div class="modal-dialog modal-xl modal-dialog-centered">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h1 class="modal-title fs-5" id="exampleModalLabel">Administrador</h1>
							        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							      </div>
							      <div class="modal-body">
									       
								  <!-- perfilAdministrador -->
						          <div id="adminInfo" class="d-flex align-items-start justify-content-center">
							            <!-- sobreponerLetrasDelanteDelBanner -->
						            	<div class="position-absolute">
			                             	
							            <!-- seccionAdministrador -->
										<section class="section profile p-1 mt-3">
											<div class="row">
												<div class="col-xl-4">
													<div class="card">
														<div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
							            					<!-- imgPerfilAdmin -->
															<img src="img/adminPerfil.jpg" alt="Profile" class="rounded-circle mb-1" height="100px">
															<hr>
							            					<!-- nombreAdmin -->
															<h2 class="text-primary text-center"><%= adminNombre + " " + adminApellido %></h2>
							            					<!-- estado -->
															<h3>Administrador</h3>
							            					<!-- enlacesRedesSociales -->
															<div class="social-links mt-2 d-flex align-items-center">
																<a href="#" class="twitter text-dark m-2">
																	<svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" fill="currentColor" class="bi bi-twitter" viewBox="0 0 16 16">
		  															<path d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"/>
																	</svg>
																</a>
																<a href="#" class="facebook text-dark m-2">
																	<svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" fill="currentColor" class="bi bi-facebook" viewBox="0 0 16 16">
		  															<path d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z"/>
																	</svg>
																	</a>
																<a href="#" class="instagram text-dark m-2">
																	<svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" fill="currentColor" class="bi bi-instagram" viewBox="0 0 16 16">
		  															<path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"/>
																	</svg>
																</a> 
																<a href="#" class="linkedin text-dark m-2">
																	<svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" fill="currentColor" class="bi bi-linkedin" viewBox="0 0 16 16">
		  															<path d="M0 1.146C0 .513.526 0 1.175 0h13.65C15.474 0 16 .513 16 1.146v13.708c0 .633-.526 1.146-1.175 1.146H1.175C.526 16 0 15.487 0 14.854V1.146zm4.943 12.248V6.169H2.542v7.225h2.401zm-1.2-8.212c.837 0 1.358-.554 1.358-1.248-.015-.709-.52-1.248-1.342-1.248-.822 0-1.359.54-1.359 1.248 0 .694.521 1.248 1.327 1.248h.016zm4.908 8.212V9.359c0-.216.016-.432.08-.586.173-.431.568-.878 1.232-.878.869 0 1.216.662 1.216 1.634v3.865h2.401V9.25c0-2.22-1.184-3.252-2.764-3.252-1.274 0-1.845.7-2.165 1.193v.025h-.016a5.54 5.54 0 0 1 .016-.025V6.169h-2.4c.03.678 0 7.225 0 7.225h2.4z"/>
																	</svg>
																</a>
															</div>
														</div>
													</div>
												</div>
		
												<!-- detalleAdministradorInfo -->
												<div class="col-xl-8">
													<div class="card"  style="background-color:transparent">
														<div class="card-body pt-3">
															<ul class="nav nav-tabs nav-tabs-bordered">
																<!-- subTitulo -->
																<li class="nav-item">
																	<button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">Datos Generales</button>
																</li>
															</ul>														
															<!-- detalleAdmin -->
															<div class="tab-content pt-2">
																<div class="tab-pane fade show active profile-overview" id="profile-overview">
																	<h5 class="card-title">Descripción</h5>
																	<hr>
																	<p class="text-secondary">
																		Buenas colaborador esta es una sección para visualizar tu información de tus datos.   
																	</p>
		
																	<h5 class="card-title">Detalles del perfil administrador</h5>
																	<hr>
		
																	<!-- datosAdmin -->
																	<div class="row mb-3">
																		<div class="col-lg-3 col-md-4 fw-bold">Nombre</div>
																		<div class="col-lg-9 col-md-8">: <%= adminNombre %></div>
																	</div>
		
																	<div class="row mb-3">
																		<div class="col-lg-3 col-md-4 fw-bold">Apellido</div>
																		<div class="col-lg-9 col-md-8">: <%= adminApellido %></div>
																	</div>
		
																	<div class="row mb-3">
																		<div class="col-lg-3 col-md-4 fw-bold">Dni</div>
																		<div class="col-lg-9 col-md-8">: <%= adminDni %></div>
																	</div>
		
																	<div class="row mb-3">
																		<div class="col-lg-3 col-md-4 fw-bold">Genero</div>
																		<div class="col-lg-9 col-md-8">: <%= adminGenero %></div>
																	</div>
		
																	<div class="row mb-3">
																		<div class="col-lg-3 col-md-4 fw-bold">F. Nacimiento</div>
																		<div class="col-lg-9 col-md-8">: <%= adminFecha %></div>
																	</div>
		
																	<div class="row mb-3">
																		<div class="col-lg-3 col-md-4 fw-bold">Email</div>
																		<div class="col-lg-9 col-md-8">: <%= adminEmail %></div>
																	</div>
														
																	<div class="row mb-3">
																		<div class="col-lg-3 col-md-4 fw-bold">Telefono</div>
																		<div class="col-lg-9 col-md-8">: <%= adminTelefono %></div>
																	</div>
																	
																	<div class="row mb-3">
																		<div class="col-lg-3 col-md-4 fw-bold">Direccion</div>
																		<div class="col-lg-9 col-md-8">: <%= adminDireccion %></div>
																	</div>
																</div>
														</div>
													</div>
												</div>
											</div>
										</section>
										<!-- perfilAdminFin -->
						            	</div>
						            	<!-- imgFondoPerfil -->
							            <img class="img-fluid" alt="" src="img/modalfondo1.jpg">
							            <br />
							            <br />          
						            </div> 
							      </div>
							    </div>
							  </div>
							</div>
                        </div>
                    </div>
                </main>
                
   				<!-- footer -->             
                <footer class="py-4 bg-dark mt-auto my-2 mx-2 bg-radius">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Todos Los Derechos Reservados - Universidad Nikola Tesla 2022</div>
                            <div>
                                <a class="m-4 text-decoration-none" href="#">Politica de privacidad</a>              
                                <a class="text-decoration-none" href="#">Terminos y Condiciones</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
     
</body>
</html>