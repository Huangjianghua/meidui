<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="search_keywordlist">
	<ul>
<%-- 		<li>
			<dl>
				<dt>
				    价格：
				</dt>
				<dd>
					<p>
						<a href="${url}&price=0-209">0-209</a>
						<a href="${url}&price=210-409" >210-409</a>
						<a href="${url}&price=410-609" class="">410-609</a>
						<a href="${url}&price=610-809">610-809</a>
						<a href="${url}&price=810-999">810-999</a>
						<a href="${url}&price=1000gt">1000以上</a>
					</p>
					<div class="priceinfo">
						<form action="">
						<input type="text" class="inputstyle" /><span>-</span>
						<input type="text" class="inputstyle" />
						<input type="submit" class="inputbtn" value="确定" onclick="appendPrice();" />
						</form>
					</div>
				</dd>
			</dl>
		</li> --%>
		<c:forEach var="property" items="${results.properties}" varStatus="status">
		<c:choose>
			<c:when test="${status.index < 3}">
				<li>
					<dl>
						<dt>${property.propName}：</dt>
						<dd>
							<p>
								<c:forEach var="propValue" items="${property.propertiesDetail }">
									<c:if test="${empty prop}">
										<a href="${url}&prop=${property.propId}_${propValue.propValueId}"> ${propValue.propValue} <i class="icon icon-close"></i></a>
									</c:if>
									<c:if test="${!empty prop}">
										<c:set var="p" value="${property.propId}_${propValue.propValueId}"></c:set>
										<c:if test="${fn:contains(prop,p)}">
											<a href="${url}&p=${property.propId}_${propValue.propValueId}&opt=rm" class="on"> ${propValue.propValue}  <i class="icon icon-close"></i></a>
										</c:if>
										<c:if test="${!fn:contains(prop,p)}">
											<a href="${url},${property.propId}_${propValue.propValueId}"> ${propValue.propValue}  <i class="icon icon-close"></i></a>
										</c:if>
									</c:if>
								</c:forEach>
							</p>
						</dd>
					</dl>
				</li>
			</c:when>
		</c:choose>
		</c:forEach>
	</ul>
</div>
