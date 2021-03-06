<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
		<title>+++  MetelSOS  +++</title>
		<%@ include file="/common/include/include-header.jsp" %>
		<script src="/metelSOS/resources/js/common.js" charset="utf-8"></script>
		<script>
			//세션없으면 로그인 페이지로 이동
			var sessionId = '<%=session.getAttribute("SESSION_LOGIN_USER_ID")%>';
			if(sessionId == 'null'){
				document.location.href="/metelSOS/login.jsp";
			}
			
			//다이얼로그 title에 html을 적용하기 위한 코드
			 $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
					_title : function(title) {
						if (!this.options.title) {
							title.html("&#160;");
						} else {
							title.html(this.options.title);
						}
					}
			}));
			
			$(document).ready(function(){
				$('#support_response_empty_dialog').dialog({
					autoOpen : false,
					width: 300,
					height:200,
					resizable: false,
					modal : true,
					title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;지원 상태 업데이트 실패</h4></div>",
					buttons:[{
						html:"<i class='fa fa-check'></i>&nbsp; 확인",
						"class": "btn btn-default",
						click:function(){
							$('#support_response_empty_dialog').dialog("close");
							$("textarea[name=support_response]").focus();
						}
					}]
				});
				
				$('#support_engineer_comment_empty_dialog').dialog({
					autoOpen : false,
					width: 300,
					height:200,
					resizable: false,
					modal : true,
					title:"<div class='widget-header'><h4><i class='fa fa-warning'></i>&nbsp;지원 상태 업데이트 실패</h4></div>",
					buttons:[{
						html:"<i class='fa fa-check'></i>&nbsp; 확인",
						"class": "btn btn-default",
						click:function(){
							$('#support_engineer_comment_empty_dialog').dialog("close");
							$("textarea[name=support_engineer_comment]").focus();
						}
					}]
				});
			});
		</script>
</head>
<body class="">
		<jsp:include page = "/common/header/HeaderPanel.jsp" flush="false"/>
		<jsp:include page = "/common/left/engineer/EngineerLeftPanel.jsp" flush="false"/>
		<div id="main" role="main">

			<div id="ribbon">
				<ol class="breadcrumb">
					<li>Home</li>
					<c:forEach var="item" items="${breadcrumbList}" varStatus="status">
						<li>${item}</li>
					</c:forEach>
				</ol>
			</div>

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
						<article class="col-sm-12 col-md-12 col-lg-12">
							<div class="jarviswidget" id="supportInfo" data-widget-editbutton="false" data-widget-custombutton="false" data-widget-deletebutton="false">
								<header>
									<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
									<h2>지원 요청 보기</h2>
								</header>
								<div>
									<div class="jarviswidget-editbox">
									</div>
									<div class="widget-body">
										<form id="supportInfoForm" class="smart-form">
											<fieldset>
												<section>
													<label class="label"><strong>제목</strong></label>
													<label class="input">
														<input type="text" name="support_title" value="${supportInfo.SUPPORT_TITLE }" readonly="readonly">
													</label>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<label class="label"><strong>고객사</strong></label>
													<label class="input">
														<input type="text" name="company_name" value="${supportInfo.COMPANY_NAME }" readonly="readonly">
													</label>
												</section>
												<section>
													<label class="label"><strong>담당자</strong></label>
													<label class="input">
														<input type="text" name="customer_name" value="${supportInfo.CUSTOMER_NAME } ${supportInfo.CUSTOMER_POSITION}" readonly="readonly">
													</label>
												</section>
												<section>
													<label class="label"><strong>담당자 연락처</strong></label>
													<label class="input">
														<input type="text" name="customer_phone" value="${supportInfo.CUSTOMER_PHONE }" readonly="readonly">
													</label>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<div class="col-sm-2">
													<label class="label"><strong>희망 지원일</strong></label>
													<label class="input">
														<input type="text" name="hope_support_date" data-dateformat="yy/mm/dd" value="${supportInfo.HOPE_SUPPORT_DATE }" readonly="readonly">
													</label>
													</div>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<label class="label"><strong>요청사항</strong></label>
													<label class="textarea textarea-resizable">
														<textarea rows="10" name="support_request" readonly="readonly"></textarea>
													</label>
												</section>
											</fieldset>
										</form>
									</div>
									<div class="widget-body">
										<p>첨부파일</p>
										
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th style="width:10%;">번호</th>
														<th style="width:25%;">파일명</th>
														<th style="width:20%;">파일 크기</th>
														<th style="width:25%;">업로드 날짜</th>
														<th style="width:20%; text-align:center;">다운로드</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${fileList}" varStatus="status">
														<tr>
															<td>${status.count }</td>
															<td>${item.original_file_name }</td>
															<td>${item.file_size }kb</td>
															<td>${item.crea_dtm }</td>
															<td style="text-align:center;"><a href="javascript:downloadFile('${item.file_num }')" class="supportFile"><i class="fa fa-file fa-lg" aria-hidden="true"></i></a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<%-- <form class="smart-form">
											<input type="hidden" name="userId" value="${userId }">
											<input type="hidden" name="supportNum" value="${supportInfo.SUPPORT_NUM}"> --%>
											<form class="smart-form">
											<fieldset>
												<div class="col-sm-2">
												<section>
														<label class="label"><strong>최종 지원일</strong></label>
														<label class="input">
															<input type="text" name="support_date" readonly="readonly" data-dateformat="yy/mm/dd" value="${supportInfo.SUPPORT_DATE }">
														</label>
												</section>
												<section>
														<label class="label"><strong>지원 방식</strong></label>
														<label class="select">
														<select name="support_way" id="support_way" disabled="disabled">
															<option value="원격">원격</option>
															<option value="방문">방문</option>
														</select> <i></i> </label>
												</section>
												</div>
											</fieldset>
											<fieldset id="purposeFieldset" style="display:none;">
												<section>
													<label class="label"><strong>방문 목적</strong></label>
													<label class="textarea textarea-resizable">
														<textarea rows="5" name="purpose_of_visit" readonly="readonly"></textarea>
													</label>
												</section>
											</fieldset>
											</form>
											<form id="updateSupportStateForm" class="smart-form">
												<input type="hidden" name="userId" value="${userId }">
												<input type="hidden" name="supportNum" value="${supportInfo.SUPPORT_NUM}">
											<fieldset>
												<section>
													<label class="label"><strong>작업 내용 및 조치 사항</strong></label>
													<label class="textarea textarea-resizable"><i class="icon-append fa fa-question-circle"></i>
														<textarea rows="5" name="support_response"></textarea>
														<b class="tooltip tooltip-top-right"> 
															<i class="fa fa-warning txt-color-teal"></i> 
															구체적으로 입력해주세요.</b>
													</label>
												</section>
											</fieldset>
											<fieldset>
												<section>
													<label class="label"><strong>Engineer Comment</strong></label>
													<label class="textarea textarea-resizable"><i class="icon-append fa fa-question-circle"></i>
														<textarea rows="5" name="support_engineer_comment"></textarea>
														<b class="tooltip tooltip-top-right"> 
															<i class="fa fa-warning txt-color-teal"></i> 
															구체적으로 입력해주세요.</b>
													</label>
												</section>
											</fieldset>
										</form>
									</div>
								</div>
							</div>
						</article>
					</div>
				</section>
				<section id="widget-grid" class="">
					<button id="update" class="btn btn-success">
						업데이트
					</button>
					<button id="cancel" class="btn btn-primary">
						취소
					</button>
				</section>
			</div>	
		</div>
		
		<jsp:include page = "/common/bottom/BottomPanel.jsp" flush="false"/>
		<jsp:include page="/common/form/commonForm.jsp" flush="false" />
		<div id="support_response_empty_dialog" title="empty dialog title">
			<p>
				<span>작업 내용 및 조치 사항을 입력해 주세요.</span>
			</p>
		</div>
		<div id="support_engineer_comment_empty_dialog" title="date error dialog">
			<p>
				<span>Engineer Comment를 입력해 주세요.</span>
			</p>
		</div>
		<%@ include file="/common/include/include-body.jsp" %>
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
		  		pageSetUp();
		  		$('#left-panel nav ul li:nth-child(2) .collapse-sign').trigger('click');
		  		$("#left-panel nav ul li:nth-child(2) ul li:nth-child(1)").addClass("active");
		  		
		  		var content = "${supportInfo.SUPPORT_REQUEST }";
		  		//로딩 후 textarea에 notice_content 값 세팅
		  		$("textarea[name=support_request]").val(content.replace(/<br\s?\/?>/g,"\n"));
		  		
		  		var purpose_of_visit = "${supportInfo.PURPOSE_OF_VISIT}";
		  		if(purpose_of_visit != ''){
		  			$("#purposeFieldset").css('display','');
		  			$("textarea[name=purpose_of_visit]").val(purpose_of_visit.replace(/<br\s?\/?>/g,"\n"));
		  		}
		  		
		  		 $("#support_way").find("option").each(function(){
		  			if(this.value == '${supportInfo.SUPPORT_WAY}'){
		  				$(this).attr("selected", "selected");
		  			}
		  		}); 
		  		
		 	 });
		  	
		  	$("#cancel").click(function(e){
		  		e.preventDefault();
		  		document.location.href="/metelSOS/leftMenuPageMove.do?userType=engineer&menuTitle=SupportList&menuIcon=fa fa-lg fa-fw fa-wrench";
		  	});
		  	
		  	$("#update").click(function(e){
		  		e.preventDefault();
		  		if($("textarea[name=support_response]").val() == ''){
		  			$('#support_response_empty_dialog').dialog("open");
		  			return false;
		  		}else if($("textarea[name=support_engineer_comment]").val() == ''){
		  			$('#support_engineer_comment_empty_dialog').dialog("open");
		  			return false;
		  		}else{
		  			$.ajax({
						url:'/metelSOS/updateSupportingState.do',
						type:'POST',
						dataType:'json',
						data : $("#updateSupportStateForm").serialize(),
						success:function(msg){
							if(msg.resultMsg == 'SUCCESS'){
								document.location.href="/metelSOS/leftMenuPageMove.do?userType=engineer&menuTitle=SupportList&menuIcon=fa fa-lg fa-fw fa-wrench";	
							}
						},
						error:function(request,status,error){
					        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					    }
						
					});
		  		}
		  	});
		
		</script>

	</body>
</html>