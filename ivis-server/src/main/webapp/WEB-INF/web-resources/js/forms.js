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
}