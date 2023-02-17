<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Universidad Nikola Tesla - Log In</title>

<link rel="icon" href="img/rayo.png">
<link href="css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

</head>

<body>

	<%
		String tipoMensaje = (String) request.getAttribute("tipoMensaje");
		String mensajeError = (String) request.getAttribute("msj");
	%>
	
	<!-- login -->
	<div id="layoutAuthentication">
		<div id="layoutAuthentication_content">
			<main>
				<!-- centradoPantalla -->
				<div class="container position-absolute top-50 start-50 translate-middle">
					<div class="row d-flex justify-content-center">
						<!-- contenedorLogueo -->
						<div class="col-lg-5 align-items-center justify-content-center">
							<div class="card shadow-lg border-0 mt-1">
								<!-- tituloTarjetaLogueo -->
								<div class="card-header" style="background-color: #fff">
								
									<h3 class="text-center my-4">
										<!-- logoInstituto -->
										<img src="img/tesla.png" style="height: 150px;">
									</h3>
									<h3 class="text-center my-4 font-opensans">Iniciar Sesión</h3>															
									
								</div>
								<div class="card-body">
									<!-- FormularioDeLogin -->
									<form action="ServletLogueo" name="fmrSesion" method="post" name="tipo">
									
										<!-- ingresarUsuario -->
										<div class="form-floating mb-3">
											<input name="txtLogin" class="form-control rounded-2 font-monospace" id="" type="text" placeholder="" required/> 
											<label for="txtLogin">Usuario</label>
										</div>
										
										<!-- ingresarContraseña -->
										<div class="form-floating mb-3">
											<input class="form-control rounded-2 font-monospace" id="password" type="password" placeholder="" name="txtPass" required /> 
											<label for="txtPass">Contraseña</label>
										</div>
										<div class="form-check mb-3 d-flex align-items-center justify-content-between">
										
											<!-- recordarContraseña --> 
											<div>
												<input class="form-check-input" id="inputRememberPassword" type="checkbox" value="" /> 
												<label class="form-check-label" for="inputRememberPassword">Recordar Contraseña</label>
											</div>
												
											<!-- funcionMostrarContraseña --> 
											<div>
												<input onclick="mostrarContrasena()" class="form-check-input" id="showPassword" type="checkbox" value="" /> 
												<label class="form-check-label" for="showPassword">Mostrar Contraseña</label>
											</div>
													
										</div>
										<div class="d-flex align-items-center justify-content-center mt-4 mb-0">
											<!-- BtnIngresar --> 
											<td colspan="2" align="center"> 
												<input class="btn btn-primary btn-lg font-opensans" type="submit" value="Ingresar">
											</td>							
										</div>
									</form>
								</div>

								<%
								if (tipoMensaje != null) {
								%>
								<h6 align="center" class="alert alert-<%=tipoMensaje%> alert-dismissible fade show" role="alert">
									<%=mensajeError%>
									<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
								</h6>
								<%
								}
								%>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
	
	<!-- funcionMostrarContraseña -->
	<script>
		function mostrarContrasena() {
			
			var tipo = document.getElementById("password");
			
			if (tipo.type == "password") {
				tipo.type = "text";
			} else {
				tipo.type = "password";
			}
		}
	</script>
	
	<!-- BootstrapJS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
	<!-- JS -->
	<script src="js/scripts.js"></script>
	
</body>
</html>