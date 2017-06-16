<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${results.status ne 'b'}">
<div class="brandlist">
	<dl>
		<table style="width:100% ">
			<tr>
				<td class="brand1">品牌：</td>
				<td class="brand2">
					<div class="brand2_con">
						<p>
							<c:forEach var="bd" items="${results.brands}" varStatus="status">
								<c:if test="${empty chkbrand}">
									<a href="${url}&b=${bd.brandId}"> ${bd.brand}  <i class="icon icon-close"></i></a>
								</c:if>
								<c:if test="${!empty chkbrand}">
									<c:forEach var="chd" items="${chkbrand}">
										<c:if test="${chd == bd.brandId}">
											<a href="${url}&b=${bd.brandId}" class="on" />
										</c:if>
										<c:if test="${chd != bd.brandId}">
											<a href="${url}&b=${bd.brandId}" />
										</c:if>
									</c:forEach>
									  ${bd.brand}  <i class="icon icon-close"></i></a>
								</c:if>
							</c:forEach>
						</p>
					</div>
				</td>
			</tr>
		</table>
	</dl>
</div>
<script>
	$(function() {
		var _a = $(".brand2_con>p>a");
		for(var i=0; i<_a.length; i++) {
			if($(_a).eq(i).attr('class') == 'on') {
				$(_a).eq(i).attr('href', $(_a).eq(i).attr('href') + "&opt=rm");
			} else {
				$(_a).eq(i).attr('href', $(_a).eq(i).attr('href') + "&opt=add");
			}
		}
		
	});
</script>
</c:if>
