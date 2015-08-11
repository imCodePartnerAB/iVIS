<%@ page pageEncoding="UTF-8" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

</div>
</div>
</div>
<script>
    $(document).ready(
            function () {
                initialize();
                if(onOpen != null)
                    onOpen.call();
            }
    );
    clientAddress = "${clientAddress}";
</script>
</body>
</html>