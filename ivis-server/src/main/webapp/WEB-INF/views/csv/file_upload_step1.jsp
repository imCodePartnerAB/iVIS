<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/resources/js/csv.upload.js" var="jsCsvUpload"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src=${jsCsvUpload} ></script>

<h1>CSV File Upload</h1>

<form:form method="post" action="/csv/step2" modelAttribute="uploadForm" enctype="multipart/form-data">

    <p>Select files to upload. Press Add button to add more file inputs.</p>

    <div class="buttons">
        <input id="addFile" class="button positive" type="button" value="Add File" onclick="addFileChooser();"/>
        <table id="fileTable">
            <tr>
                <td><input name="files[0]" type="file" /></td>
            </tr>
                <%--<tr>--%>
                <%--<td><input name="files[1]" type="file" /></td>--%>
                <%--</tr>--%>
        </table>
        <br/><input class="button positive" type="submit" value="Next" />
    </div>

</form:form>
