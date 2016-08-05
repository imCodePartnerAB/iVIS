    function deleteElement(url, id){
    if(confirm("do you want to delete application?")){
       deleteElementRest(url, id,
           function(){
               $("tr[data-object-id='" + id + "']").remove();
       });
    };
};

function deleteElementRest(url, id, callback) {
    $.ajax(
        {
            url: url + "/" + id,
            success: callback(),
            method: "DELETE",
            error: function (result, arg1) {
                alert(resul)
            }
        }
    )
};

function showOrHideElementByLabel(spanArrow) {
    var label = spanArrow.parentElement;
    var $element = $("#" + label.getAttribute("for"));
    if($element.is(":visible")) {
        $element.hide();
        $(label).find(">:first-child").css('transform', 'rotate(45deg)');
    } else {
        $element.show();
        $(label).find(">:first-child").css('transform', 'rotate(135deg)');
    }
};