<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<script--%><%--src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>--%>
<script>
    $(document).ready(function() {
        //add more file components if Add is clicked
        $('#addFile').click(function() {
            var fileIndex = $('#fileTable tr').children().length;
            $('#fileTable').append(
                    '<tr><td>'+
                    '   <input name="files['+ fileIndex +']" type="file" />'+
                    '</td></tr>');
        });

    });
</script>
<h1>CSV File Upload</h1>

<form:form method="post" action="csv/step2" modelAttribute="uploadForm" enctype="multipart/form-data">

    <p>Select files to upload. Press Add button to add more file inputs.</p>

    <input id="addFile" type="button" value="Add File" />
    <table id="fileTable">
        <tr>
            <td><input name="files[0]" type="file" /></td>
        </tr>
        <%--<tr>--%>
            <%--<td><input name="files[1]" type="file" /></td>--%>
        <%--</tr>--%>
    </table>
    <br/><input type="submit" value="Next>>" />
</form:form>
