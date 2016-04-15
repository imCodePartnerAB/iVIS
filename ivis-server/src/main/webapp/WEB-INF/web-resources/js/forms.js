    function deleteElement(url, id){
    if(confirm("do you want to delete application?")){
       deleteElementRest(url, id,
           function(){
               $("tr[data-object-id='" + id + "']").remove();});
    };
};

function deleteElementRest(url, id, callback) {
    $.ajax(
        {
            url: url + "/" + id,
            success: callback,
            method: "DELETE",
            error: function (result, arg1) {
                alert(resul)
            }
        }
    )
};



function validate() {

    var isNaturalNumber = function (n) {
        return !isNaN(n) && n.indexOf('-') === -1 && n.indexOf('.') === -1 && isFinite(n)                ;
    };

    if (!$('#roles1').is(':checked') && !$('#roles2').is(':checked')) {
        $('.validation-error-roles').show();
        return false;
    }

    var selected = false;
    $($('#authorizedGrantTypes').children()).each(function(){if($(this).prop('selected')) {

        selected = true;
    }});

    if (!selected) {
        $('.validation-error-authorized').show();
        return selected;
    }


    if (!isNaturalNumber($('#accessTokenValiditySeconds').val())) {
        $('.validation-error-access-token').show();
        return false;

    }



    if (!isNaturalNumber($('#refreshTokenValiditySeconds').val())) {
        $('.validation-error-refresh-token').show();
        return false;

    }

    return true;

};
