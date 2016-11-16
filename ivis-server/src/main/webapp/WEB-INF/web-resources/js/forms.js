
$('table').ready(function () {
    var colCount = 0;
    $('tr:nth-child(1) td').each(function () {
        if ($(this).attr('colspan')) {
            colCount += +$(this).attr('colspan');
        } else {
            colCount++;
        }
    });
    var $th = $('th');
    var $td = $('td');
    $th.attr("width", 100/colCount + "%");
    $td.attr("width", 100/colCount + "%");
});

function deleteElement(url, id){
    if(confirm("do you want to delete?")){
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

function permissionOnClick (input) {
    var entityDivId = input.parentElement.parentElement.parentElement.id;
    var state = calcState(entityDivId);
    setState(entityDivId, state);
}


function calcState(entityDivId) {
    var $checkboxGroup = $("#" + entityDivId + " :input");
    var $checkboxGroupChecked = $("#" + entityDivId + " :input:checked");
    if ($checkboxGroupChecked.length > 0 && $checkboxGroupChecked.length != $checkboxGroup.length) {
        return null;
    } else {
        return $checkboxGroupChecked.length == $checkboxGroup.length;
    }

}

function setState(entityDivId, state) {
    $("input[for='" + entityDivId + "']").tristate("state", state);
}

function tristateOnChange(state, value) {
    if (state != null) {
        var entityDivId = this[0].getAttribute("for");
        $('#' + entityDivId).find("input[type='checkbox']").prop("checked", state);
    }
}
