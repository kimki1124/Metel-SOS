<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>+++  MetelSOS  +++</title>
		<meta name="description" content="">
		<meta name="author" content="">
			
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		
		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/font-awesome.min.css">

		<!-- SmartAdmin Styles : Caution! DO NOT change the order -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-production-plugins.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-production.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-skins.min.css">

		<!-- SmartAdmin RTL Support  -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/smartadmin-rtl.min.css">

		<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
		<link rel="stylesheet" type="text/css" media="screen" href="/metelSOS/resources/css/demo.min.css">
		
		<!-- FAVICONS -->
		<link rel="shortcut icon" href="/metelSOS/resources/img/favicon/favicon.ico" type="image/x-icon">
		<link rel="icon" href="/metelSOS/resources/img/favicon/favicon.ico" type="image/x-icon">

		<!-- #GOOGLE FONT -->
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">
		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script>
			if (!window.jQuery) {
				document.write('<script src="/metelSOS/resources/js/libs/jquery-2.1.1.min.js"><\/script>');
			}
		</script>

		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script>
			if (!window.jQuery.ui) {
				document.write('<script src="/metelSOS/resources/js/libs/jquery-ui-1.10.3.min.js"><\/script>');
			}
		</script>
		<script src="/metelSOS/resources/js/common.js" charset="utf-8"></script>
		<script>
			//뒤로가기 막기
			history.pushState(null, null, location.href); 
			window.onpopstate = function(event) { 
				history.go(1); 
			}
			
			//세션없으면 로그인 페이지로 이동
			var sessionId = '<%=session.getAttribute("SESSION_LOGIN_USER_ID")%>';
			if(sessionId == 'null'){
				document.location.href="/metelSOS/login.jsp";
			}
		</script>
</head>
<body class="">
		<jsp:include page = "/common/header/HeaderPanel.jsp" flush="false"/>
		<jsp:include page = "/common/left/engineer/EngineerLeftPanel.jsp" flush="false"/>
		<form name="noticePageParamForm">
			<input type="hidden" name="userType">
			<input type="hidden" name="menuTitle">
			<input type="hidden" name="menuIcon">
		</form>
		<!-- #MAIN PANEL -->
		<div id="main" role="main">

			<!-- RIBBON -->
			<div id="ribbon">

				<!-- breadcrumb -->
				<ol class="breadcrumb">
					<li>Home</li>
					<c:forEach var="item" items="${breadcrumbList}" varStatus="status">
						<li>${item}</li>
					</c:forEach>
					<!-- <li>메인화면</li> -->
				</ol>
				<!-- end breadcrumb -->
			</div>
			<!-- END RIBBON -->

			<!-- #MAIN CONTENT -->
			<div id="content">
				
				<div class="row">
					<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
						<h3 class="page-title txt-color-blueDark">
							<i class="${menuIcon}"></i> 
								${menuTitle} 
						</h3>
					</div>
				</div>
				
				<section id="widget-grid" class="">
					<div class="row">
						<article class="col-sm-12">
							<div class="jarviswidget jarviswidget-color-darken" id="notice" data-widget-editbutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>공지사항 관리</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th style="width:10%;">번호</th>
														<th style="width:25%;">제목</th>
														<th style="width:5%; text-align:center;">파일</th>
														<th style="width:25%;">작성자</th>
														<th style="width:25%;">작성일</th>
														<th style="width:10%;">조회수</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${noticeList}" varStatus="status">
														<tr>
															<td>${status.count }</td>
															<td><a href="javascript:moveNoticeDetailPage('${item.notice_num }');">${item.notice_title }</a></td>
															<td style="text-align:center;"><c:if test="${item.has_file == 'Y' }">
																	<i class="fa fa-file fa-lg" aria-hidden="true"></i>
																</c:if></td>
															<td>${item.notice_author }</td>
															<td>${item.notice_date }</td>
															<td>${item.notice_hit }</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class="widget-body" style="min-height:40px;">
										<button id="moveWriteNoticePage" class="btn btn-success">새 공지 작성</button>
										<button id="moveMain" class="btn btn-success" style="float:right;">메인으로</button>
									</div>
								</div>
							</div>
						</article>
					</div>
				</section>
			</div>	
		</div>
		<!-- END #MAIN PANEL -->

		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />

		<!--================================================== -->

		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)
		<script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>-->
		

		<!-- IMPORTANT: APP CONFIG -->
		<script src="/metelSOS/resources/js/app.config.js"></script>

		<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
		<script src="/metelSOS/resources/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> 

		<!-- BOOTSTRAP JS -->
		<script src="/metelSOS/resources/js/bootstrap/bootstrap.min.js"></script>

		<!-- CUSTOM NOTIFICATION -->
		<script src="/metelSOS/resources/js/notification/SmartNotification.min.js"></script>

		<!-- JARVIS WIDGETS -->
		<script src="/metelSOS/resources/js/smartwidgets/jarvis.widget.min.js"></script>

		<!-- EASY PIE CHARTS -->
		<script src="/metelSOS/resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

		<!-- SPARKLINES -->
		<script src="/metelSOS/resources/js/plugin/sparkline/jquery.sparkline.min.js"></script>

		<!-- JQUERY VALIDATE -->
		<script src="/metelSOS/resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

		<!-- JQUERY MASKED INPUT -->
		<script src="/metelSOS/resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

		<!-- JQUERY SELECT2 INPUT -->
		<script src="/metelSOS/resources/js/plugin/select2/select2.min.js"></script>

		<!-- JQUERY UI + Bootstrap Slider -->
		<script src="/metelSOS/resources/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

		<!-- browser msie issue fix -->
		<script src="/metelSOS/resources/js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

		<!-- FastClick: For mobile devices -->
		<script src="/metelSOS/resources/js/plugin/fastclick/fastclick.min.js"></script>

		<!-- MAIN APP JS FILE -->
		<script src="/metelSOS/resources/js/app.min.js"></script>

		<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
		<!-- Voice command : plugin -->
		<script src="/metelSOS/resources/js/speech/voicecommand.min.js"></script>

		<!-- SmartChat UI : plugin -->
		<script src="/metelSOS/resources/js/smart-chat-ui/smart.chat.ui.min.js"></script>
		<script src="/metelSOS/resources/js/smart-chat-ui/smart.chat.manager.min.js"></script>
		
		<!-- PAGE RELATED PLUGIN(S) -->
		
		<!-- Flot Chart Plugin: Flot Engine, Flot Resizer, Flot Tooltip -->
		<script src="/metelSOS/resources/js/plugin/flot/jquery.flot.cust.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/flot/jquery.flot.resize.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/flot/jquery.flot.time.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/flot/jquery.flot.tooltip.min.js"></script>
		
		<!-- Vector Maps Plugin: Vectormap engine, Vectormap language -->
		<script src="/metelSOS/resources/js/plugin/vectormap/jquery-jvectormap-1.2.2.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/vectormap/jquery-jvectormap-world-mill-en.js"></script>
		
		<!-- Full Calendar -->
		<script src="/metelSOS/resources/js/plugin/moment/moment.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/fullcalendar/jquery.fullcalendar.min.js"></script>
		
		<!-- Morris Chart Dependencies -->
		<script src="/metelSOS/resources/js/plugin/morris/raphael.min.js"></script>
		<script src="/metelSOS/resources/js/plugin/morris/morris.min.js"></script>
		
		<!-- Your GOOGLE ANALYTICS CODE Below -->
		<script type="text/javascript">
		 	 var _gaq = _gaq || [];
		 	 _gaq.push(['_setAccount', 'UA-XXXXXXXX-X']);
		 	 _gaq.push(['_trackPageview']);
		
		  	(function() {
		   		 var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		   		 ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		   	 	var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  	})();
		  	
		  	$(document).ready(function(){
		  		$(document).keydown(function(e){   
	    			if(e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA"){       
	        			if(e.keyCode === 8){   
	        				return false;
	        			}
	    			}
				});
		  		
		  		pageSetUp();
		  		
		  		$("#moveMain").click(function(e){
		  			e.preventDefault();
		  			document.location.href="/metelSOS/leftMenuPageMove.do?userType=engineer&menuTitle=EngineerMain&menuIcon=fa fa-lg fa-fw fa-info";
		  		});
		  		
		  		$("#moveWriteNoticePage").click(function(e){
		  			e.preventDefault();
		  			var comSubmit = new ComSubmit();
		  			comSubmit.setUrl("/metelSOS/writeNoticePageMove.do");
		  			comSubmit.addParam('userName', encodeURI('<%=session.getAttribute("SESSION_LOGIN_USER_NAME") %>'));
		  			comSubmit.addParam('userType', 'engineer');
		  			comSubmit.addParam('menuTitle', encodeURI('공지사항 작성'));
		  			comSubmit.addParam('menuIcon', 'fa fa-lg fa-fw fa-pencil-square-o');
		  			comSubmit.getSubmit();
		  		});
		 	 });
		  	
		  	function moveNoticeDetailPage(notice_num){
		  		document.location.href="/metelSOS/moveNoticeDetailPage.do?notice_num="+notice_num;
		  	}
		
		</script>

	</body>
</html>