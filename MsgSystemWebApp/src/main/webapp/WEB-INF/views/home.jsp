<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="homeActive" value="active" scope="request" />

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="content">
		<div>	
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
