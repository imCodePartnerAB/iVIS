/**
 * Created by vitaly on 04.12.15.
 */
function onChangeType(element) {
    element = $(element);
    var value = element.val().replace(new RegExp("\\.", "g"), "");
    var elementId = element.attr("id");
    var values = typeMap[value];
    var tableId = elementId + "Options";
    var dropdowns = $("#" + tableId + " select");
    dropdowns.each(function (index, dropdown) {
        dropdown = $(dropdown);
        dropdown.html("");
        $("<option>").text(index).attr("value", index).appendTo(dropdown);
        for (var i = 0; i < values.length; i++) {
            $("<option>").text(values[i]).attr("value", values[i]).appendTo(dropdown);
        }

    });
}

function onChangeSkipFirstRow(element) {
    element = $(element);
    var value = element.val().replace(new RegExp("\\.", "g"), "");
    var elementId = element.attr("id");
    var values = typeMap[value];
    var tableId = elementId + "Options";
    var dropdowns = $("#" + tableId + " select");
    dropdowns.each(function (index, dropdown) {
        dropdown = $(dropdown);
        dropdown.html("");
        $("<option>").text(index).appendTo(dropdown);
        for (var i = 0; i < values.length; i++) {
            $("<option>").text(values[i]).attr("value", values[i]).appendTo(dropdown);
        }

    });
}

function addFileChooser() {
    //add more file components if Add is clicked
    var fileIndex = $('#fileTable tr').children().length;
    $('#fileTable').append(
        '<tr><td>'+
        '   <input name="files['+ fileIndex +']" type="file" />'+
        '</td></tr>');        

}


