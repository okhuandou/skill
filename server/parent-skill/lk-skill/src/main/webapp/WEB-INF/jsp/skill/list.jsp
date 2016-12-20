<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <title>秒杀商品列表</title>
     <%@include file="../common/head.jsp" %>
   <%--   <jsp:include page=""></jsp:include> --%>
   </head>
   <body>
   <div class="container">
      <div class="row clearfix">
         <div class="col-md-12 column">
            <div class="carousel slide" id="carousel-451249">
               <ol class="carousel-indicators">
                  <li class="active" data-slide-to="0" data-target="#carousel-451249">
                  </li>
                  <li data-slide-to="1" data-target="#carousel-451249">
                  </li>
                  <li data-slide-to="2" data-target="#carousel-451249">
                  </li>
               </ol>
               <div class="carousel-inner">
                  <div class="item active">
                     <img alt="" src="v3/default.jpg" />
                     <div class="carousel-caption">
                        <h4>
                           First Thumbnail label
                        </h4>
                        <p>
                           Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                        </p>
                     </div>
                  </div>
                  <div class="item">
                     <img alt="" src="v3/default1.jpg" />
                     <div class="carousel-caption">
                        <h4>
                           Second Thumbnail label
                        </h4>
                        <p>
                           Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                        </p>
                     </div>
                  </div>
                  <div class="item">
                     <img alt="" src="v3/default2.jpg" />
                     <div class="carousel-caption">
                        <h4>
                           Third Thumbnail label
                        </h4>
                        <p>
                           Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                        </p>
                     </div>
                  </div>
               </div> <a class="left carousel-control" href="#carousel-451249" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-451249" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
            <div class="panel-group" id="panel-283774">
               <div class="panel panel-default">
                  <div class="panel-heading">
                     <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-283774" href="#panel-element-400311">Collapsible Group Item #1</a>
                  </div>
                  <div id="panel-element-400311" class="panel-collapse collapse">
                     <div class="panel-body">
                        Anim pariatur cliche...
                     </div>
                  </div>
               </div>
               <div class="panel panel-default">
                  <div class="panel-heading">
                     <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-283774" href="#panel-element-449910">Collapsible Group Item #2</a>
                  </div>
                  <div id="panel-element-449910" class="panel-collapse collapse">
                     <div class="panel-body">
                        Anim pariatur cliche...
                     </div>
                  </div>
               </div>
            </div>
            <nav class="navbar navbar-default" role="navigation">
               <div class="navbar-header">
                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#">Brand</a>
               </div>
               <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                  <ul class="nav navbar-nav">
                     <li class="active">
                        <a href="#">Link</a>
                     </li>
                     <li>
                        <a href="#">Link</a>
                     </li>
                     <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
                        <ul class="dropdown-menu">
                           <li>
                              <a href="#">Action</a>
                           </li>
                           <li>
                              <a href="#">Another action</a>
                           </li>
                           <li>
                              <a href="#">Something else here</a>
                           </li>
                           <li class="divider">
                           </li>
                           <li>
                              <a href="#">Separated link</a>
                           </li>
                           <li class="divider">
                           </li>
                           <li>
                              <a href="#">One more separated link</a>
                           </li>
                        </ul>
                     </li>
                  </ul>
                  <form class="navbar-form navbar-left" role="search">
                     <div class="form-group">
                        <input type="text" class="form-control" />
                     </div> <button type="submit" class="btn btn-default">Submit</button>
                  </form>
                  <ul class="nav navbar-nav navbar-right">
                     <li>
                        <a href="#">Link</a>
                     </li>
                     <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
                        <ul class="dropdown-menu">
                           <li>
                              <a href="#">Action</a>
                           </li>
                           <li>
                              <a href="#">Another action</a>
                           </li>
                           <li>
                              <a href="#">Something else here</a>
                           </li>
                           <li class="divider">
                           </li>
                           <li>
                              <a href="#">Separated link</a>
                           </li>
                        </ul>
                     </li>
                  </ul>
               </div>
            </nav>
            <div class="alert alert-dismissable alert-warning">
               <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
               <h4>
                  注意!
               </h4> <strong>Warning!</strong> Best check yo self, you're not looking too good. <a href="#" class="alert-link">alert link</a>
            </div>
            <div class="jumbotron">
               <h1>
                  Hello, world!
               </h1>
               <p>
                  This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.
               </p>
               <p>
                  <a class="btn btn-primary btn-large" href="#">Learn more</a>
               </p>
            </div>
         </div>
      </div>
      <div class="row clearfix">
         <div class="col-md-4 column">
            <h2>
               Heading
            </h2>
            <p>
               Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
            </p>
            <p>
               <a class="btn" href="#">View details »</a>
            </p>
         </div>
         <div class="col-md-4 column">
            <h2>
               Heading
            </h2>
            <p>
               Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
            </p>
            <p>
               <a class="btn" href="#">View details »</a>
            </p>
         </div>
         <div class="col-md-4 column">
            <h2>
               Heading
            </h2>
            <p>
               Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.
            </p>
            <p>
               <a class="btn" href="#">View details »</a>
            </p>
         </div>
      </div>
   </div>
    
   </body>
   
   
      <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</html>