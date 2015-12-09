<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:url value="/resources/js/csv.upload.js" var="jsCsvUploadUrl"/>

<h1>CSV File Upload</h1>
<form:form method="post" action="step3" modelAttribute="fileUploadOptionsForm">
    <script type="text/javascript" src="${jsCsvUploadUrl}"></script>
    <c:forEach items="${fileUploadOptionsForm.fileOptionList}" var="fileOption" varStatus="fileOptionStatus">
        <c:set var="fieldNamePrefix" value="fileOptionList[${fileOptionStatus.index}]"/>
        <div id="fileContainer${fileOptionStatus.index}">
            <h2>${fileOption.originalFileName}</h2>
            <form:hidden path="${fieldNamePrefix}.fileId"/>
                <%--<td>--%>
            <div class="field">
                <c:set var="fieldName" value="${fieldNamePrefix}.type"/>
                <form:label path="${fieldName}">
                    Type
                </form:label>
                <form:select path="${fieldName}" cssErrorClass="error" id="file${fileOptionStatus.index}"
                             onchange="onChangeType(this)">
                    <form:option value="null" label=""/>
                    <form:options items="${typeMap.keySet()}" itemValue="name" itemLabel="simpleName"/>
                </form:select>
                <form:errors path="${fieldName}" cssClass="error-description"/>
            </div>
            <div class="field">
                <c:set var="fieldName" value="${fieldNamePrefix}.keyColumn"/>
                <form:label path="${fieldName}">
                    Key column
                </form:label>
                <form:select path="${fieldName}" cssErrorClass="error">
                    <form:option value="null" label=""/>
                    <c:forEach begin="0" end="${fileOption.columnCount - 1}" step="1" varStatus="status">
                        <form:option value="${status.index}" label="Column #${status.index}"/>
                    </c:forEach>
                </form:select>
                <form:errors path="${fieldName}" cssClass="error-description"/>
            </div>

            <%--<div class="field">--%>
                <%--<c:set var="fieldName" value="${fieldNamePrefix}.firstRowSkip"/>--%>
                <%--<form:checkbox path="${fieldName}" label="First row skip" cssClass="check-box"/>--%>
                <%--<form:errors path="${fieldName}" cssClass="error-description"/>--%>
            <%--</div>--%>

            <div class="field">
                <c:set var="fieldName" value="${fieldNamePrefix}.skipRows"/>
                <form:label path="${fieldName}">
                    Skip first rows
                </form:label>
                <form:input path="${fieldName}" />
                <form:errors path="${fieldName}" cssClass="error"/>
            </div>


            <h3>Summary</h3>
            <table id="file${fileOptionStatus.index}Options" cellpadding="0" cellspacing="0" class="field">
                <thead>
                <tr>
                    <th></th>
                    <c:forEach begin="0" end="${fileOption.columnCount - 1}" step="1" varStatus="status">
                        <th>
                            <form:select path="${fieldNamePrefix}.columnNameList[${status.index}]">
                                <form:option value="${status.index}" label="${status.index}"/>
                            </form:select>
                            <%--<select>--%>
                                <%--<option value="null">${status.index}</option>--%>
                            <%--</select>--%>
                        </th>
                    </c:forEach>
                </tr>
                </thead>
                <c:forEach items="${fileOption.firstRows}" var="row" varStatus="rowStatus">
                    <tr>
                        <td>${rowStatus.index}</td>
                        <c:forEach items="${row}" var="cell">
                            <td>${cell}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:forEach>
    <br/><input type="submit" value="Next>>"/>
    <script>
        <%--var t = ${typeMapJson};--%>
        var typeMap = {
            <c:forEach items="${typeMap.entrySet()}" var="entry">
            "${fn:replace(entry.key.name, ".", "")}": [
                <c:forEach items="${entry.value}" var="value">
                "${value}",
                </c:forEach>],
            </c:forEach>
        };
    </script>
</form:form>