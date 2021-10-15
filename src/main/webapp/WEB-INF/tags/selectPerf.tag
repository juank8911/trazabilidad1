
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<div class="col-md-5 col-sm-8 clearfix">		
			<select id="verde" class="col-md-auto"
				style="border: none !important;" name="selPerfil">
				<option value="${selPerfil.id_perfil}" selected="selected" disabled="disabled">${selPerfil.perfil}</option>
				<c:forEach items="${inSelPerfiles}" var="per">
					<option value="${per.id_perfil }">${per.perfil}</option>
				</c:forEach>
			</select>
		</div>