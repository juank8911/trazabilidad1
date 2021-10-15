<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>sdstrp - Actualizar Empresa </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/png" href="assets/images/icon/favicon.ico">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/themify-icons.css">
        <link rel="stylesheet" href="assets/css/metisMenu.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/css/slicknav.min.css">
        <link rel="stylesheet" href="assets/css/coloresInst.css">
        <!-- amchart css -->
        <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
        <!-- others css -->
        <link rel="stylesheet" href="assets/css/typography.css">
        <link rel="stylesheet" href="assets/css/default-css.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
        <!-- modernizr css -->
        <script src="assets/js/vendor/modernizr-2.8.3.min.js"></script>
    </head>

    <body>
        <!--[if lt IE 8]>
                <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
            <![endif]-->
        <!-- preloader area start -->
        <div id="preloader">
            <div class="loader"></div>
        </div>
        <!-- preloader area end -->
        <div class="logo">
            <img src="assets/images/logo_sdstrp.png" alt="logo"></a>
            <img src="assets/images/logo_siac.jpg" alt="logo" ></a>
            <img src="assets/images/logo_mads.png" alt="logo"></a> 
            <img src="assets/images/logo_ideam.png" alt="logo"></a> 

        </div>
        <!-- header area start -->
        <div class="header-area">
            <div class="row align-items-center">
                <!-- nav and search button -->
                <div class="col-md-6 col-sm-8 clearfix">
                    <div class="nav-btn pull-left">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>

                </div>
                <!-- profile info & task notification -->
                <div class="col-md-6 col-sm-4 clearfix">
                    <ul class="notification-area pull-right">


                        <li class="dropdown">
                            <i class="ti-bell ">
                                <span>2</span>
                            </i>

                        </li>
                        <li class="dropdown">
                            <i class="ti-flag-alt"><span>3</span></i>

                        </li>
                        <li>
                            <div class="dropdown col-lg-6 col-md-4 col-sm-6">
                                <button class="btn btn-success dropdown-toggle " type="button" data-toggle="dropdown">
                                    Nombre usuario
                                </button>
                                <div class="dropdown-menu" style="background: transparent !important;">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" style="text-align: center !important">Nombre Usuario</h5>
                                            <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Corporación para el Desarrollo Sostenible del Archipiélago de San Andrés, Providencia y Santa Catali</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-outline-success mb-3" data-dismiss="modal"> Perfil</button>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <button type="button" class="btn btn-outline-success mb-3">Cerrar Sesion</button>
                                        </div>
                                    </div>
                                </div>
                            </div></li>
                        <li class="dropdown">
                            <i class="ti-help" data-toggle="dropdown"></i>

                        </li>

                    </ul>
                </div>
            </div>
        </div>
        <!-- header area end -->
        <!-- page container area start -->
        <div class="page-container">
            <!-- sidebar menu area start -->
            <div class="sidebar-menu">

                <div class="main-menu">
                    <div class="menu-inner">
                        <nav>
                            <ul class="metismenu" id="menu" >
                                <li><a href="control?action=autoridad"><i class="ti-dashboard"></i> <span>Inicio</span></a></li>
                                <li class="active">
                                    <a aria-expanded="true"><i class="ti-angle-down"></i><span>Información</span></a>
                                    <ul class="collapse">
                                        <li><a href="control?action=autoridadLeer" aria-expanded="true"><i class="ti-eye"></i><span>Autoridad</span></a></li>
                                        <li><a href="control?action=perfilLee" aria-expanded="true"><i class="fa fa-male"></i><span>Perfil</span></a></li>
                                        <li><a  href="control?action=cambioClave" aria-expanded="true"><i class="ti-key"></i><span>Contraseña</span></a></li>
                                    </ul>
                                </li>
                                <li class="active">
                                    <a aria-expanded="true"><i class="ti-angle-down"></i><span>Declaraciones</span></a>
                                    <ul class="collapse">
                                        <li><a method="post" href="control?action=empresaLeer" aria-expanded="true"><i class="fa fa-university"></i><span>Empresa</span></a></li>
                                        <li><a href="control?action=sedeLee" aria-expanded="true"><i class="fa fa-building"></i><span>Establecimiento</span></a></li>
                                        <li><a href="control?action=usuarioLista" aria-expanded="true"><i class="ti-user"></i><span>Usuarios</span></a></li>
                                    </ul>
                                </li>
                                <li class="active">
                                    <a  aria-expanded="true"><i class="ti-angle-down"></i><span>Consultas</span></a>
                                    <ul class="collapse">
                                        <li><a href="control?action=declaraLista" aria-expanded="true"><i class="ti-files"></i><span>Historico</span></a></li>
                                        <li><a href="control?action=indiLista" aria-expanded="true"><i class="fa fa-pie-chart"></i><span>Indicadores</span></a></li>
                                        <li><a href="control?action=salidaList" aria-expanded="true"><i class="ti-menu-alt"></i><span>Salidas</span></a></li>
                                    </ul>
                                </li>
                                <li><a href="control?action=autoridad"><i class="ti-shift-right"></i> <span>Cerrar Sesion</span></a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <!-- sidebar menu area end -->
            <!-- main content area start -->
            <div class="main-content">

                <!-- page title area start -->
                <div>
                    <div class="row align-items-center">
                        <div class="col-sm-7">
                            <div class="breadcrumbs-area">
                                <h4 class="page-title pull-left">Crear de empresas</h4>
                                <ul class="breadcrumbs pull-left">
                                    <li><span> inscritas en el sistema de información</span></li>
                                </ul>
                            </div>
                        </div>
                        <!-- FIN DE MENU  -->
                    </div>
                </div>
                <!-- page title area end -->
                <!-- main content area inicio -->
                <div class="main-content-inner">
                    <div class="row">
                        <div class="col-lg-6 col-ml-12">
                            <div class="row">


                                <!-- Server side start -->
                                <div class="col-12">
                                    <div class="card mt-4">
                                        <div class="card-body">

                                            <form method="post" action="control?action=empresaRegistro" class="needs-validation" novalidate="">
   <table class="table text-left">

                                                    <tr>
                                                        <td> <label for="validationCustom01"> Razon Social </label></td>
                                                        <td> <input type="text" name="" class="form-control" id="validationCustom02" placeholder="" value="" required=""> </td>
                                                        <td> <label for="validationCustom01">Nombre Comercial</label></td>
                                                        <td> <input type="text" name=""class="form-control" id="validationCustom02" placeholder="" value="" required=""></td>

                                                    </tr>
                                                    <tr>
                                                        <td> <label for="validationCustom01">NIT </label></td>
                                                        <td> <input type="text" name="cargoUsu" class="form-control" id="validationCustom02"  value="" required=""></td>
                                                        <td> <label for="example-text-input" class="col-form-label">CIUU </label></td>
                                                        <td> <select class="form-control" name="tipDocu">
                                                                <option>Select</option>
                                                                <option>Large select</option>
                                                                <option>Small select</option>
                                                            </select></td>

                                                    </tr>
                                                    <tr>
                                                        <td> <label for="validationCustom01">Departamento </label></td>
                                                        <td> <select class="form-control" name="tipDocu">
                                                                <option>Select</option>
                                                                <option>Large select</option>
                                                                <option>Small select</option>
                                                            </select></td>
                                                        <td> <label for="example-text-input" class="col-form-label">Municipio </label></td>
                                                        <td> <select class="form-control" name="tipDocu">
                                                                <option>Select</option>
                                                                <option>Large select</option>
                                                                <option>Small select</option>
                                                            </select></td>

                                                    </tr>
                                                    <tr>
                                                        <td> <label for="validationCustom01">Direccion </label></td>
                                                        <td> <input type="text" name="cargoUsu" class="form-control" id="validationCustom02"  value="" required=""></td>
                                                        <td> <label for="example-text-input" class="col-form-label">Telefono </label></td>
                                                        <td> <input type="text" name="telUsu" class="form-control" id="validationCustom02"  value="" required=""> </td>
                                                        <td> <label for="example-text-input" class="col-form-label">Ext </label></td>
                                                        <td> <input type="text" name="telUsu" class="form-control" id="validationCustom02"  value="" required=""></td>
                                                    </tr>
                                                    <tr>
                                                        <td> <label for="validationCustom01">CC Representante </label></td>
                                                        <td> <input type="text" name="cargoUsu" class="form-control" id="validationCustom02"  value="" required=""></td>
                                                        <td> <label for="example-text-input" class="col-form-label">Email Representante </label></td>
                                                        <td> <input type="text" name="telUsu" class="form-control" id="validationCustom02"  value="" required=""></td>

                                                    </tr>
                                                    <tr>
                                                        <td> <label for="validationCustom01">Nombre Representante</label></td>
                                                        <td> <input type="text" name="" class="form-control" id="validationCustom02" placeholder="" value="" required=""></td>
                                                        <td> </td>
                                                        <td> </td>

                                                    </tr>
                                                    <tr>
                                                        <td> <button class="btn btn-success mb-3" type="submit">Actualizar</button></td>
                                                        <td> <button class="btn btn-success mb-3" type="submit"><a href="control?action=residuoLeeGenera" class="text-white">Cancelar</a></button></td>

                                                    </tr>


                                                </table>
                                            
                                            </form>
                                            
                                        </div>
                                    </div>
                                </div>
                                <!-- Server side end -->

                            </div>
                        </div>
                    </div>
                    <!-- main content area end -->
                </div>
                <!-- footer area start-->
                <footer>
                    <div class="footer-area">
                        <p>Copyright ® 2020 <a href="http://www.ideam.gov.co/">IDEAM.</a>Todos los derechos reservados.</p>
                    </div>
                </footer>
                <!-- footer area end-->
            </div>
            <!-- page container area end -->
            <!-- offset area start -->
            <div class="offset-area">
                <div class="offset-close"><i class="ti-close"></i></div>
                <ul class="nav offset-menu-tab">
                    <li><a class="active" data-toggle="tab" href="#activity">Activity</a></li>
                    <li><a data-toggle="tab" href="#settings">Settings</a></li>
                </ul>
                <div class="offset-content tab-content">
                    <div id="activity" class="tab-pane fade in show active">
                        <div class="recent-activity">
                            <div class="timeline-task">
                                <div class="icon bg1">
                                    <i class="fa fa-envelope"></i>
                                </div>
                                <div class="tm-title">
                                    <h4>Rashed sent you an email</h4>
                                    <span class="time"><i class="ti-time"></i>09:35</span>
                                </div>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse distinctio itaque at.
                                </p>
                            </div>
                            <div class="timeline-task">
                                <div class="icon bg2">
                                    <i class="fa fa-check"></i>
                                </div>
                                <div class="tm-title">
                                    <h4>Added</h4>
                                    <span class="time"><i class="ti-time"></i>7 Minutes Ago</span>
                                </div>
                                <p>Lorem ipsum dolor sit amet consectetur.
                                </p>
                            </div>
                            <div class="timeline-task">
                                <div class="icon bg2">
                                    <i class="fa fa-exclamation-triangle"></i>
                                </div>
                                <div class="tm-title">
                                    <h4>You missed you Password!</h4>
                                    <span class="time"><i class="ti-time"></i>09:20 Am</span>
                                </div>
                            </div>
                            <div class="timeline-task">
                                <div class="icon bg3">
                                    <i class="fa fa-bomb"></i>
                                </div>
                                <div class="tm-title">
                                    <h4>Member waiting for you Attention</h4>
                                    <span class="time"><i class="ti-time"></i>09:35</span>
                                </div>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse distinctio itaque at.
                                </p>
                            </div>
                            <div class="timeline-task">
                                <div class="icon bg3">
                                    <i class="ti-signal"></i>
                                </div>
                                <div class="tm-title">
                                    <h4>You Added Kaji Patha few minutes ago</h4>
                                    <span class="time"><i class="ti-time"></i>01 minutes ago</span>
                                </div>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse distinctio itaque at.
                                </p>
                            </div>
                            <div class="timeline-task">
                                <div class="icon bg1">
                                    <i class="fa fa-envelope"></i>
                                </div>
                                <div class="tm-title">
                                    <h4>Ratul Hamba sent you an email</h4>
                                    <span class="time"><i class="ti-time"></i>09:35</span>
                                </div>
                                <p>Hello sir , where are you, i am egerly waiting for you.
                                </p>
                            </div>
                            <div class="timeline-task">
                                <div class="icon bg2">
                                    <i class="fa fa-exclamation-triangle"></i>
                                </div>
                                <div class="tm-title">
                                    <h4>Rashed sent you an email</h4>
                                    <span class="time"><i class="ti-time"></i>09:35</span>
                                </div>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse distinctio itaque at.
                                </p>
                            </div>
                            <div class="timeline-task">
                                <div class="icon bg2">
                                    <i class="fa fa-exclamation-triangle"></i>
                                </div>
                                <div class="tm-title">
                                    <h4>Rashed sent you an email</h4>
                                    <span class="time"><i class="ti-time"></i>09:35</span>
                                </div>
                            </div>
                            <div class="timeline-task">
                                <div class="icon bg3">
                                    <i class="fa fa-bomb"></i>
                                </div>
                                <div class="tm-title">
                                    <h4>Rashed sent you an email</h4>
                                    <span class="time"><i class="ti-time"></i>09:35</span>
                                </div>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse distinctio itaque at.
                                </p>
                            </div>
                            <div class="timeline-task">
                                <div class="icon bg3">
                                    <i class="ti-signal"></i>
                                </div>
                                <div class="tm-title">
                                    <h4>Rashed sent you an email</h4>
                                    <span class="time"><i class="ti-time"></i>09:35</span>
                                </div>
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Esse distinctio itaque at.
                                </p>
                            </div>
                        </div>
                    </div>
                    <div id="settings" class="tab-pane fade">
                        <div class="offset-settings">
                            <h4>General Settings</h4>
                            <div class="settings-list">
                                <div class="s-settings">
                                    <div class="s-sw-title">
                                        <h5>Notifications</h5>
                                        <div class="s-swtich">
                                            <input type="checkbox" id="switch1" />
                                            <label for="switch1">Toggle</label>
                                        </div>
                                    </div>
                                    <p>Keep it 'On' When you want to get all the notification.</p>
                                </div>
                                <div class="s-settings">
                                    <div class="s-sw-title">
                                        <h5>Show recent activity</h5>
                                        <div class="s-swtich">
                                            <input type="checkbox" id="switch2" />
                                            <label for="switch2">Toggle</label>
                                        </div>
                                    </div>
                                    <p>The for attribute is necessary to bind our custom checkbox with the input.</p>
                                </div>
                                <div class="s-settings">
                                    <div class="s-sw-title">
                                        <h5>Show your emails</h5>
                                        <div class="s-swtich">
                                            <input type="checkbox" id="switch3" />
                                            <label for="switch3">Toggle</label>
                                        </div>
                                    </div>
                                    <p>Show email so that easily find you.</p>
                                </div>
                                <div class="s-settings">
                                    <div class="s-sw-title">
                                        <h5>Show Task statistics</h5>
                                        <div class="s-swtich">
                                            <input type="checkbox" id="switch4" />
                                            <label for="switch4">Toggle</label>
                                        </div>
                                    </div>
                                    <p>The for attribute is necessary to bind our custom checkbox with the input.</p>
                                </div>
                                <div class="s-settings">
                                    <div class="s-sw-title">
                                        <h5>Notifications</h5>
                                        <div class="s-swtich">
                                            <input type="checkbox" id="switch5" />
                                            <label for="switch5">Toggle</label>
                                        </div>
                                    </div>
                                    <p>Use checkboxes when looking for yes or no answers.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- offset area end -->
            <!-- jquery latest version -->
            <script src="assets/js/vendor/jquery-2.2.4.min.js"></script>
            <!-- bootstrap 4 js -->
            <script src="assets/js/popper.min.js"></script>
            <script src="assets/js/bootstrap.min.js"></script>
            <script src="assets/js/owl.carousel.min.js"></script>
            <script src="assets/js/metisMenu.min.js"></script>
            <script src="assets/js/jquery.slimscroll.min.js"></script>
            <script src="assets/js/jquery.slicknav.min.js"></script>

            <!-- start chart js -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
            <!-- start highcharts js -->
            <script src="https://code.highcharts.com/highcharts.js"></script>
            <!-- start zingchart js -->
            <script src="https://cdn.zingchart.com/zingchart.min.js"></script>
            <script>
                zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
                ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9", "ee6b7db5b51705a13dc2339db3edaf6d"];
            </script>
            <!-- all line chart activation -->
            <script src="assets/js/line-chart.js"></script>
            <!-- all pie chart -->
            <script src="assets/js/pie-chart.js"></script>
            <!-- others plugins -->
            <script src="assets/js/plugins.js"></script>
            <script src="assets/js/scripts.js"></script>
    </body>

</html>
