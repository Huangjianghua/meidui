<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty cat and results.status ne 'c'}">
<div class="brandlist">
	<dl>
		<table style="width:100% ">
			<tr>
				<td class="brand1">分类：</td>
				<td class="brand2">
					<div class="brand2_con">
						<p>
							<c:forEach var="cat" items="${results.cats}" varStatus="status">
								<a href="${url}&cid3=${cat.catId}"> ${cat.catName}  <i class="icon icon-close"></i></a>
							</c:forEach>
						</p>
					</div>
				</td>
			</tr>
		</table>
	</dl>
</div>
</c:if>
