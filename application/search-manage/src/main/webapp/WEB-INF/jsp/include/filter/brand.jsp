<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty brand and results.status ne 'b'}">
<div class="brandlist">
	<dl>
		<table style="width:100% ">
			<tr>
				<td class="brand1">品牌：</td>
				<td class="brand2">
					<div class="brand2_con">
						<p>
							<c:forEach var="brand" items="${results.brands }" varStatus="status">
								<a href="${url }&brand=${brand.brandId}"> ${brand.brand }  <i class="icon icon-close"></i></a>
							</c:forEach>
						</p>
					</div>
				</td>
			</tr>
		</table>
	</dl>
</div>
</c:if>
