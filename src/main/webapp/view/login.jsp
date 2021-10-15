<%--
    JBoss, Home of Professional Open Source
    Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
    contributors by the @authors tag. See the copyright.txt in the
    distribution for a full listing of individual contributors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Ingreso - sdstrp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Here we include the css file  -->
<link rel="shortcut icon" type="image/png" href="assets/images/icon/favicon.ico">
<link rel="stylesheet" type="text/css" href="resources/css/screen.css" />
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/themify-icons.css">
    <link rel="stylesheet" href="assets/css/metisMenu.css">
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/css/slicknav.min.css">
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <!-- others css -->
    <link rel="stylesheet" href="assets/css/typography.css">
    <link rel="stylesheet" href="assets/css/default-css.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" href="assets/css/responsive.css">
    <!-- modernizr css -->
    <script src="resources/js/vendor/modernizr-2.8.3.min.js"></script>
</head>
<body>
<!--     <div id="preloader">
        <div class="loader"></div>
    </div> -->
     <div class="logo">
         <img src="assets/images/logo_sdstrp.png" alt="logo"></a>
         <img src="assets/images/logo_siac.jpg" alt="logo" ></a>
         <img src="assets/images/logo_mads.png" alt="logo"></a> 
         <img src="assets/images/logo_ideam.png" alt="logo"></a> 
         
     </div>
         <!-- login area start -->
    <div class="login-area">
        <br />
        <h5 class="titulo"> Sistema de declaración y trazabilidad de residuos peligrosos</h5>
        <br />
        <div class="container">
            <div class="login-box ptb--40">
                
                <form method="post" action="control?action=ingresar" style="border-radius: 2em">
                    <div class="login-form-head">
                        <b>Acceso al Sistema de Información</b>
                    </div>
                    <div class="login-form-body">
                         ${msjUsuario}
                        <div class="form-gp">
                            <label for="exampleInputEmail1"> </label>
                            <input type="text" name="txtUser" id="exampleInputEmail1" placeholder="Usuario"/>
                            
                            <i class="ti-user"></i>
                        </div>
                         ${msjClave}
                        <div class="form-gp">
                            <label for="exampleInputPassword1"> </label>
                            <input type="password" name="txtPass" id="exampleInputPassword1" placeholder="Contraseña">
                            <i class="ti-lock"></i>
                        </div>
                      
                        <div class="submit-btn-area">
                            
                            <button  id="form_logi" type="submit">Ingresar <i class="ti-arrow-right"></i></button>
                            
                        </div>
                        
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- login area end -->
		<!-- jquery latest version -->
    <script src="assets/js/vendor/jquery-2.2.4.min.js"></script>
    <!-- bootstrap 4 js -->
    <script src="assets/js/popper.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/owl.carousel.min.js"></script>
    <script src="assets/js/metisMenu.min.js"></script>
    <script src="assets/js/jquery.slimscroll.min.js"></script>
    <script src="assets/js/jquery.slicknav.min.js"></script>
    
    <!-- others plugins -->
    <script src="assets/js/plugins.js"></script>
    <script src="assets/js/scripts.js"></script>
</body>
</html>