<!DOCTYPE html>
<%@ page import="com.controller.*" %>
<html lang="en">
  <head>
  <title>Mixed Generative</title>
    <!-- Required meta tags always come first -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet">
    <link rel="stylesheet" href="css/ionicons.min.css">
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/owl.theme.css">
    <link rel="stylesheet" href="css/style.css">
    <style type="text/css">
    #main-content{
    
    width:900px;
    margin:0 auto;
    height:100%;
    padding:20px;
    
    }
    p{
    font-size:14px;
    line-height:2em;
    margin-bottom:32px;
    }
    </style>
  </head>
  <body>
  
    <header id="home" class="gradient-violat">
      <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span style="background-color:grey;" class="logo-wraper logo-white">
                <img src="images/Logo.png" alt="">Mixed Generative
              </span></a>
          </div>

          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul style="background-color:grey;" class="nav navbar-nav  navbar-right">
              <li class="active"><a href="Home.jsp">Home <span class="sr-only">(current)</span></a></li>
              <li style="foreground-color:black;"><a href="Register.jsp">Registration</a></li>
              <li><a href="UserLogin.jsp">User</a></li>
              <li><a href="AdminLogin.jsp">Admin</a></li>
               
            </ul>
          </div><!-- /.navbar-collapse -->
          <hr class="navbar-divider">
        </div><!-- /.container-fluid -->
      </nav>
    </header>
    
    <section id="newsletter" class="padding-top-bottom-120 newsletter">
    
      <div class="container">
        <div class="row">
          <div class="">
            <div class="row">
              <div class="col-md-8 col-md-offset-1 padding-top-bottom-90">
                
                <div class="subscription-wraper text-center" id="main-content">
                  <h4 class="text-upper">Abstract</h4>
               
                <p>Hashing methods have proven to be useful for a variety of 
                tasks and have attracted extensive attention in recent years. 
                Various hashing approaches have been proposed to capture similarities
                 between textual, visual, and cross-media information. However,
                  most of the existing works use a bag-of-words method to represent 
                  textual information. Since words with different forms may have 
                  similar meaning, semantic level text similarities cannot be 
                  well processed in these methods. To address these challenges, 
                  in this paper, we propose a novel method called semantic cross-media
                   hashing (SCMH), which uses continuous word representations to
                    capture the textual similarity at the semantic level and use 
                    a deep belief network (DBN) to construct the correlation
                     between different modalities. To demonstrate the effectiveness 
                     of the proposed method, we evaluate the proposed method on 
                     three commonly used cross-media data sets are used in this work.
                      Experimental results show that the proposed method achieves 
                      significantly better performance than state-of-the-art approaches.
                       Moreover, the efficiency of the proposed method is comparable to 
                       or better than that of some other hashing methods.</p>
                 <br>
              <h4 class="text-upper">System Architecture</h4>  <br>
                 <img alt="" src="images/a.png">
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
   
   
   
   
          <hr class="footer-divider">
          <div class="copyright-cta">
            <p class="text-uppercase">All rights Reserved </p>
          </div>
        </div>
      </div>
      <div class="footer-end-line"></div>
    </footer>
    <div id="scroll-top-div" class="scroll-top-div">
      <div class="scroll-top-icon-container">
        <i class="ion-ios-arrow-thin-up"></i>
      </div>
    </div>
    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/script.js"></script>
  </body>
</html>
