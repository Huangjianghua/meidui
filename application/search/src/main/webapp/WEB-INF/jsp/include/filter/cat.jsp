<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${results.status ne 'c'}">
<div class="brandlist">
	<dl>
		<table style="width:100% ">
			<tr>
				<td class="brand1">分类：</td>
				<td class="brand2">
					<div class="brand2_con">
						<p>
							<c:forEach var="ct" items="${results.cats}" varStatus="status">
								<c:if test="${empty cat}">
									<a href="${url}&cid3=${ct.catId}"> ${ct.catName}  <i class="icon icon-close"></i></a>
								</c:if>
								<c:if test="${!empty cat and cat.catId == ct.catId}">
									<a href="${url}" class="on"> ${ct.catName}  <i class="icon icon-close"></i></a>
								</c:if>
							</c:forEach>
						</p>
					</div>
				</td>
			</tr>
		</table>
	</dl>
</div>
</c:if>
