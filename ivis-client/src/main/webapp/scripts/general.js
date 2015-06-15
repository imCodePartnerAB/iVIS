var ivis = null;

function initialize() {
    ivis = IVis;
    ivis.tabs = new IVis.Tabs();
    ivis.ui = new IVis.UI();
}

/* Global namespaces */
var IVis = {};

/* Tabs class */
IVis.Tabs = function () {
    this.initialize();
};

IVis.Tabs.prototype =
{
    initialize: function () {
        $(document).on("click", ".tabs > .tab",
            function () {
                $(".tabs > .tab").removeClass("active");
                $(".tab-page").hide();
                $(this).addClass("active");
                $("#" + $(this).data("tabPageId")).show();
            }
        );

        $(".tabs > .tab").first().click();
    }
};

/* UI class */
IVis.UI = function () {
    this.initialize();
};

IVis.UI.prototype =
{
    initialize: function () {
    },

    removeContainer: function (containerId) {
        $("#" + containerId).remove();
    },


    addField: function (owner, itemId, itemPrefix, name, labelText) {
        if (labelText != null) {
            $("<label>")
                .attr("for", itemPrefix + itemId + "." + name)
                .html(labelText)
                .appendTo(owner);
        }

        $("<input>")
            .attr("id", itemPrefix + itemId + "." + name)
            .attr("name", itemPrefix + "[" + itemId + "]." + name)
            .attr("type", "text")
            .appendTo(owner);
    },

    addSelect: function (owner, itemId, itemPrefix, name, items, labelText) {
        if (labelText != null) {
            $("<label>")
                .attr("for", itemPrefix + itemId + "." + name)
                .html(labelText)
                .appendTo(owner);
        }

        var select = $("<select>")
            .attr("id", itemPrefix + itemId + "." + name)
            .attr("name", itemPrefix + "[" + itemId + "]." + name)
            .appendTo(owner);

        for (var i = 0; i < items.length; i++) {
            $("<option>")
                .attr("value", items[i].name)
                .html(items[i].description)
                .appendTo(select);
        }
    },

    addButton: function () {

    },

    getNewItemIndex: function (tag) {
        var value = 0;
        $("#" + tag + " .field[data-index]").each(function (index, element) {
            if (parseInt($(element).data("index")) > value)
                value = parseInt($(element).data("index"));
        });

        return value + 1;
    },

    addPhone: function () {
        var subContainerName = "phones";
        var itemIndex = this.getNewItemIndex(subContainerName);
        var conteinerName = "person." + subContainerName;
        var conteinerId = subContainerName + itemIndex + "Field";

        var container = $("<div>")
            .addClass("field")
            .attr("id", conteinerId)
            .attr("data-index", itemIndex)
            .insertBefore($("#" + subContainerName + " .positive"));

        this.addSelect(container, itemIndex, conteinerName, "communicationType", communicationTypeEnum);

        var $this = this;
        $("<button>")
            .addClass("negative")
            .attr("type", "button")
            .html("Remove")
            .attr("onClick", "ivis.ui.removeContainer('" + conteinerId + "');")
            //.click(function () {
            //    $this.removeContainer(conteinerId)
            //})
            .appendTo(container);

        this.addField(container, itemIndex, conteinerName, "number", "Phone");
    },

addEmail: function () {
        var subContainerName = "emails";
        var itemIndex = this.getNewItemIndex(subContainerName);
        var conteinerName = "person." + subContainerName;
        var conteinerId = subContainerName + itemIndex + "Field";

        var container = $("<div>")
            .addClass("field")
            .attr("id", conteinerId)
            .attr("data-index", itemIndex)
            .insertBefore($("#" + subContainerName + " .positive"));

        this.addSelect(container, itemIndex, conteinerName, "communicationType", communicationTypeEnum);

        var $this = this;
        $("<button>")
            .addClass("negative")
            .attr("type", "button")
            .html("Remove")
            .attr("onClick", "ivis.ui.removeContainer('" + conteinerId + "');")
            //.click(function () {
            //    $this.removeContainer(conteinerId)
            //})
            .appendTo(container);

        this.addField(container, itemIndex, conteinerName, "address", "Email");
    },

addAddress: function () {
        var subContainerName = "addresses";
        var itemIndex = this.getNewItemIndex(subContainerName);
        var conteinerName = "person." + subContainerName;
        var conteinerId = subContainerName + itemIndex + "Field";

        var container = $("<div>")
            .addClass("field")
            .attr("id", conteinerId)
            .attr("data-index", itemIndex)
            .insertBefore($("#" + subContainerName + " .positive"));

        this.addSelect(container, itemIndex, conteinerName, "addressType", addressTypeEnum);

        var $this = this;
        $("<button>")
            .addClass("negative")
            .attr("type", "button")
            .html("Remove")
            .attr("onClick", "ivis.ui.removeContainer('" + conteinerId + "');")
            //.click(function () {
            //    $this.removeContainer(conteinerId)
            //})
            .appendTo(container);

        this.addField(container, itemIndex, conteinerName, "careOf", "c/o");
        this.addField(container, itemIndex, conteinerName, "street", "Street");
        this.addField(container, itemIndex, conteinerName, "postalCode", "Postal code");
        this.addField(container, itemIndex, conteinerName, "city", "City");
        this.addField(container, itemIndex, conteinerName, "municipalityCode", "Municipality code");
    }

};

var wnd;
function ivisOAuth(authUrl) {
    //var authUrl = "<%=IvisOAuth2Utils.getOAuth2AuthirizationUrl(client, Imcms.getServerProperties().getProperty("AuthorizationCodeHandlerUri"))%>";
    var width = 600;
    var height = 350;
    var left = (screen.width / 2) - (width / 2);
    var top = (screen.height / 2) - (height / 2);
    wnd = window.open(authUrl, "newwinow", "left=" + left + ", top=" + top + ", width=" + width + ", height=" + height + ", menubar=0, status=0, resizable=0, scrollbars=0");
}
function authComplete() {
    wnd.close();
    location.reload();
}