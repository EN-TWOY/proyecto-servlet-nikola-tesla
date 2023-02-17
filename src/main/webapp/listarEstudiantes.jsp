<%@page import="beans.EstudianteDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nikola Tesla - Estudiantes</title>

		<!-- MiniLogoDeLaPagina -->
	<link rel="icon" href="img/rayo.png">
	<!-- DataTablesCss -->
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.css"/>
	 <!--EstilosCss -->
    <link href="css/styles.css" rel="stylesheet"/>
    <!-- Icons -->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

</head>
<body>

	<!-- listadoAdmiistradores -->
	<%
		List<EstudianteDTO> data = (List<EstudianteDTO>) request.getAttribute("data");

		String tipoMensaje = (String) request.getAttribute("tipoMensaje");
		String mensaje = (String) request.getAttribute("mensaje");
	%>
	<!-- Navegacion_Superior -->
	
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- LogoDeLaPagina -->
		<a class="navbar-brand text-center fw-bold" href="index.jsp"> 
			NIKOLA TESLA
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lightning-fill" viewBox="0 0 16 16">
  			<path d="M5.52.359A.5.5 0 0 1 6 0h4a.5.5 0 0 1 .474.658L8.694 6H12.5a.5.5 0 0 1 .395.807l-7 9a.5.5 0 0 1-.873-.454L6.823 9.5H3.5a.5.5 0 0 1-.48-.641l2.5-8.5z"/>
			</svg>
		</a>
		
		<!-- BtnMenu-->
		<button class="btn btn-link btn-xl order-1 order-lg-0 me-4 me-lg-0"
			id="sidebarToggle" href="#!">
			<i class="fas fa-bars"></i>
		</button>
		
		<!-- Barra de navegacion de busqueda-->
		<form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
			<div class="input-group">
				<input class="form-control" type="text" placeholder="Busqueda..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
				<button class="btn btn-secondary" id="btnNavbarSearch" type="button"> 
					<i class="fas fa-search"></i>
				</button>
			</div>
		</form>
		
		<!-- UsuarioLogueado-->
		<div class="m-1"> 
			 <a class="text-decoration-none text-white">Bienvenido: </a>
			 
			 <a class="text-decoration-none text-white">
			 	<label> Andrés </label>
			 	<label> Fuster Arregui</label>
			 </a>
		</div>
		
		<!-- OpcionesDelUsuario-->
		<ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
				role="button" data-bs-toggle="dropdown" aria-expanded="false"><i
					class="fas fa-user fa-fw"></i></a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown">
					<li>
						<a class="dropdown-item" href="#">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
  								<path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
							</svg>
						Administrador 
						</a>
					</li>
					<li><hr class="dropdown-divider"/></li>
					<li class="">
						<a class="dropdown-item" href="login.jsp">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
  								<path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
  								<path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
							</svg>
						Cerrar Sesion 
						</a>
					</li>
				</ul></li>
		</ul>
	</nav>

	<div id="layoutSidenav">
		<!-- Navegacion_Vertical -->

            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                        	<!-- tituloBienvenido -->
                            <div class="sb-sidenav-menu-heading">Bienvenido</div>
                            
                        	<!-- inicioMenuVertical -->                            
                            <a class="nav-link" href="index.jsp">
                                <div class="sb-nav-link-icon">
                                	<svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" fill="currentColor" class="bi bi-microsoft" viewBox="0 0 18 18">
  									<path d="M7.462 0H0v7.19h7.462V0zM16 0H8.538v7.19H16V0zM7.462 8.211H0V16h7.462V8.211zm8.538 0H8.538V16H16V8.211z"/>
									</svg>
                                </div>
                                Inicio
                            </a>
                            
                        	<!-- tituloMantenimiento -->
                            <div class="sb-sidenav-menu-heading">Mantenimientos</div>
                            
                        	<!-- listadosMenuVertical -->
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                	Listados
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            
                        	<!-- listadosSubMenuVertical -->
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
                            
                        	<!-- agregarMenuVertical -->
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                	Agregar
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            
                        	<!-- agregarSubMenuVertical -->
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                	<a class="nav-link" href="registrarAdministrador.jsp">Administrador</a>
                                	<a class="nav-link" href="registrarEstudiante.jsp">Estudiante</a>
                                	<a class="nav-link" href="registrarProfesor.jsp">Profesor</a>
                                	<a class="nav-link" href="registrarCarrera.jsp">Carrera</a>
                                	<a class="nav-link" href="registrarMateria.jsp">Materia</a>
                                	<a class="nav-link" href="registrarMatricula.jsp">Matricula</a>
                                	<a class="nav-link" href="registrarContrato.jsp">Contrato</a>
                                </nav>
                            </div>
                            
                        	<!-- tituloMantenimiento -->
                            <div class="sb-sidenav-menu-heading">Reportes</div>
                            
                        	<!-- listadosMenuVertical -->
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayoutsReportes" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                	Reporte
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            
                        	<!-- listadosSubMenuVertical -->
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
                            
                        	<!-- matriculaSubMenuVertical -->                                       
                            <div class="sb-sidenav-menu-heading">Matriculas</div>
                            
                        	<!-- matriculaAlumno -->                                                                   
                            <a class="nav-link" href="registrarEstudiante.jsp">
                                <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                                Nuevo Estudiante
                            </a>
                            
                        </div>
                    </div>
                    
                    <div class="sb-sidenav-footer mx-2 my-2"  style="padding-top: 0.87rem;">

					<div class="small">Copyright © Universidad Nikola Tesla 2022</div>

				</div>
                    
                </nav>
            </div>
            
            <!-- Contenido_De_Menu -->
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-0">
                        <div class="card my-2 mx-2">
                        
                   			<!-- ===================================================================================================== -->
				            <!-- startTables -->
                        	
				           	<!-- titleTabla -->
				            <div class="card-header fw-bold d-flex align-items-center">
				           		<!-- iconoTabla -->
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-table " viewBox="0 0 16 16">
 								<path d="M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2zm15 2h-4v3h4V4zm0 4h-4v3h4V8zm0 4h-4v3h3a1 1 0 0 0 1-1v-2zm-5 3v-3H6v3h4zm-5 0v-3H1v2a1 1 0 0 0 1 1h3zm-4-4h4V8H1v3zm0-4h4V4H1v3zm5-3v3h4V4H6zm4 4H6v3h4V8z"/>
								</svg>
								
                                
                                <a>&nbsp;LISTADO DE ESTUDIANTES</a>
                            </div>


							<div class="card-body">
						
							<!-- alertaMensajes -->
							<%
								if(tipoMensaje != null){
				
							%>	
							<!-- alertaBootstrap -->
							<div class="alert alert-<%= tipoMensaje %> alert-dismissible fade show" role="alert">
								<!-- msjError -->
								<%= mensaje %>
  								<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
							</div>
							<%
								}
							%>
						
							<!-- TablaAlumnos -->
								<table id="dataTablesES"
									class="table table-light table-striped container-sm p-0 mb-4">
	
									<thead class="table-primary">
										<tr>
											<th class="text-center">ID</th>
											<th class="text-center">NOMBRE</th>
											<th class="text-center">APELLIDO</th>
											<th class="text-center">DNI</th>
											<th class="text-center">GENERO</th>
											<th class="text-center">NACIMIENTO</th>
											<th class="text-center">EMAIL</th>
											<th class="text-center">TELEFONO</th>
											<th class="text-center">DIRECCION</th>
											<th class="text-center">OPCIONES</th>
										</tr>
									</thead>
	
									<tbody>
										<%
										if (data != null) {
											for (EstudianteDTO obj : data) {
										%>
										<tr>
											<td class="text-center col-1"><%=obj.getIdeEst()%></td>
											<td class="text-center col-1"><%=obj.getNomEst()%></td>
											<td class="text-center col-1"><%=obj.getApeEst()%></td>
											<td class="text-center col-1"><%=obj.getDniEst()%></td>
											<td class="text-center col-1"><%=obj.getGenEst()%></td>
											<td class="text-center col-1"><%=obj.getFecEst()%></td>
											<td class="text-center col-1"><%=obj.getEmaEst()%></td>
											<td class="text-center col-1"><%=obj.getTelEst()%></td>
											<td class="text-center col-3"><%=obj.getDirEst()%></td>
											<td class="text-center">
												<!-- btnActualizar --> 
												<a href="ServletEstudiante?tipo=buscar&cod=<%= obj.getIdeEst() %>" style="text-decoration:none;">
													<button type="button" class="btn btn-primary btn-sm">
														<svg xmlns="http://www.w3.org/2000/svg" width="16" height="19" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 18">
	  													<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
	  													<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
														</svg>
													</button>
												</a> 
												<!-- btnEliminar --> 
												<a href="ServletEstudiante?tipo=eliminar&cod=<%=obj.getIdeEst()%>" style="text-decoration:none;">
													<button type="button" class="btn btn-danger btn-sm">
														<svg xmlns="http://www.w3.org/2000/svg" width="16" height="19" fill="currentColor" 
														class="bi bi-trash3" viewBox="0 0 16 18"><path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
														</svg>
													</button>
												</a>
											</td>
										</tr>
										<%
											}
										}
										%>
									</tbody>
								</table>
							</div>
						 <!-- endTables -->
						 <!-- ===================================================================================================== -->
	 
                             
                        </div>
                    </div>
                </main>
                
   				<!-- Pie_De_Pagina -->             
                <footer class="py-4 bg-dark mt-auto mt-auto my-2 mx-2 bg-radius">
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
	
	  <!-- BootstrapJS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
	<!-- JsDeLaPagina -->
    <script src="js/scripts.js"></script>     
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <!-- Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    
	<!-- DatatablesJS -->
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.js"></script>
        
    <!-- Llamar_dataTables_ESP -->
    <script src="js/dataTables.js"></script>
    <!-- 
    <script src="js/chart-area-demo.js"></script>
    <script src="js/chart-bar-demo.js"></script>
     -->   
     
</body>
</html>