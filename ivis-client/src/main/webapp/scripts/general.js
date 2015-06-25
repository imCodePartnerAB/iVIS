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
                $.cookie("data-tab-page-id", $(this).data("tabPageId"), {path: '/'});
            }
        );

        var tabId = $.cookie("data-tab-page-id");

        if (tabId == null) {
            $(".tabs > .tab").first()
                //.next()
                .click();
        } else {
            $(".tabs > .tab[data-tab-page-id='" + tabId + "']").click();
        }

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
        $("#" + this.escapeDots(containerId)).remove();
    },

    escapeDots: function (str) {
        return str.replace(/\./g, "\\.");
    },

    escapeBrackets: function (str) {
        return str.replace(/[\[\]]/g, "");
    },

    getNewItemIndex: function (tag) {
        //var elementId = tag.replace(".", "\\.");
        var value = 0;
        var elements = $("#" + this.escapeDots(this.escapeBrackets(tag)) + " .field[data-index]");

        if (elements.length > 0) {
            elements.each(function (index, element) {
                if (parseInt($(element).data("index")) > value)
                    value = parseInt($(element).data("index"));
            });
            return value + 1;
        } else
            return 0;
    },

    //addPhone: function () {
    //    var subContainerName = "phones";
    //    var itemIndex = this.getNewItemIndex(subContainerName);
    //    var conteinerName = "person." + subContainerName;
    //    var conteinerId = subContainerName + itemIndex + "Field";
    //
    //    var container = $("<div>")
    //        .addClass("field")
    //        .attr("id", conteinerId)
    //        .attr("data-index", itemIndex)
    //        .insertBefore($("#" + subContainerName + " .positive"));
    //
    //    this.addSelect(container, itemIndex, conteinerName, "communicationType", communicationTypeEnum);
    //
    //    var $this = this;
    //    $("<button>")
    //        .addClass("negative")
    //        .attr("type", "button")
    //        .html("Remove")
    //        .attr("onClick", "ivis.ui.removeContainer('" + conteinerId + "');")
    //        //.click(function () {
    //        //    $this.removeContainer(conteinerId)
    //        //})
    //        .appendTo(container);
    //
    //    this.addField(container, itemIndex, conteinerName, "number", "Phone");
    //}
    addPhone: function (subConteinerId) {
        var itemIndex = this.getNewItemIndex(subConteinerId);
        var conteinerId = this.escapeBrackets(subConteinerId) + itemIndex + "Field";
        var container = $("<div>")
            .addClass("field")
            .attr("id", conteinerId)
            .attr("data-index", itemIndex)
            .insertBefore($("#" + this.escapeDots(this.escapeBrackets(subConteinerId)) + " .positive"));

        this.addSelect(container, itemIndex, subConteinerId, "communicationType", communicationTypeEnum);

        var $this = this;
        $("<button>")
            .addClass("negative")
            .attr("type", "button")
            .html("Remove")
            .attr("onClick", "ivis.ui.removeContainer('" + conteinerId + "');")
            .appendTo(container);

        this.addField(container, itemIndex, subConteinerId, "number", "Phone");
    },

    addEmail: function (subConteinerId) {
        var itemIndex = this.getNewItemIndex(subConteinerId);
        var conteinerId = this.escapeBrackets(subConteinerId) + itemIndex + "Field";
        var container = $("<div>")
            .addClass("field")
            .attr("id", conteinerId)
            .attr("data-index", itemIndex)
            .insertBefore($("#" + this.escapeDots(this.escapeBrackets(subConteinerId)) + " .positive"));

        this.addSelect(container, itemIndex, subConteinerId, "communicationType", communicationTypeEnum);

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

        this.addField(container, itemIndex, subConteinerId, "address", "Email");
    },

    addAddress: function (subConteinerId) {
        var itemIndex = this.getNewItemIndex(subConteinerId);
        var conteinerId = this.escapeBrackets(subConteinerId) + itemIndex + "Field";
        var container = $("<div>")
            .addClass("field")
            .attr("id", conteinerId)
            .attr("data-index", itemIndex)
            .insertBefore($("#" + this.escapeDots(this.escapeBrackets(subConteinerId)) + " .positive"));

        this.addSelect(container, itemIndex, subConteinerId, "addressType", addressTypeEnum);

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

        this.addField(container, itemIndex, subConteinerId, "careOf", "c/o");
        this.addField(container, itemIndex, subConteinerId, "street", "Street");
        this.addField(container, itemIndex, subConteinerId, "postalCode", "Postal code");
        this.addField(container, itemIndex, subConteinerId, "city", "City");
        this.addField(container, itemIndex, subConteinerId, "municipalityCode", "Municipality code");
    },

    addField: function (owner, itemId, itemPrefix, name, labelText) {
        var itemIdPrefix = this.escapeBrackets(itemPrefix);
        if (labelText != null) {
            $("<label>")
                .attr("for", itemIdPrefix + itemId + "." + name)
                .html(labelText)
                .appendTo(owner);
        }

        $("<input>")
            .attr("id", itemIdPrefix + itemId + "." + name)
            .attr("name", itemPrefix + "[" + itemId + "]." + name)
            .attr("type", "text")
            .appendTo(owner);
    },

    addSelect: function (owner, itemId, itemPrefix, name, items, labelText) {
        var itemIdPrefix = this.escapeBrackets(itemPrefix);
        if (labelText != null) {
            $("<label>")
                .attr("for", itemIdPrefix + itemId + "." + name)
                .html(labelText)
                .appendTo(owner);
        }

        var select = $("<select>")
            .attr("id", itemIdPrefix + itemId + "." + name)
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

    onSchoolChange: function (value) {
        $.getJSON("/client/api/content/rest/School/" + value,
            function (result) {
                $("#schoolIdLabel").html(result.schoolId);
                var schoolClasses = result.schoolClasses;
                var schoolClassesSelect = $("#schoolClass");
                schoolClassesSelect.html("");

                for (var i = 0; i < schoolClasses.length; i++) {
                    $("<option>")
                        .attr("value", schoolClasses[i].id)
                        .html(schoolClasses[i].name)
                        .appendTo(schoolClassesSelect);
                }
            });
    },

    clearSerchText: function (paramName) {
        var url = this.getBaseUrl();
        var urlParams = this.getUrlParams();
        var first = true;
        var i = 1;

        for (var key in urlParams) {
            if (key != paramName) {
                if (first) {
                    url = url + "?";
                    first = false;
                }
                url = url + key + "=" + urlParams[key] + "&";
            }

            i++;
        }

        location.href = url;
    },

    getBaseUrl: function () {
        return location.protocol + '//' + location.host + location.pathname;
    },

    getUrlParams: function () {
        var urlParams = {};
        var match,
            pl = /\+/g,
            search = /([^&=]+)=?([^&]*)/g,
            decode = function (s) {
                return decodeURIComponent(s.replace(pl, " "));
            },
            query = window.location.search.substring(1);

        while (match = search.exec(query)) {
            urlParams[decode(match[1])] = decode(match[2]);
        }

        return urlParams;
    },

    toggleDiv: function (elementId) {
        var escapedElementId = this.escapeDots(elementId);
        var element = $("#" + escapedElementId);

        if (element.is(":visible")) {
            element.slideUp("slow");
        } else {
            element.slideDown("slow");
        }
    },

    disableContactPerson: function (checkboxId, divId, slide) {
        slide != null || slide ? true : false;
        var checkbox = $("#" + this.escapeDots(checkboxId));
        var escapedElementId = this.escapeDots(divId);
        var element = $("#" + escapedElementId);

        if (checkbox[0].checked) {
            element.attr("disable", null);
            $("#" + escapedElementId + " :input").removeAttr('disabled');
            if (slide) {
                element.slideDown("slow");
            }
            if(element.html() == null) {
                alert("contactPerson is empty!")
            }
        } else {
            element.attr("disable", "disable");
            $("#" + escapedElementId + " :input").attr('disabled', true);
            if (slide) {
                element.slideUp("slow");
            }
        }
    },

    disableSoloGuardian: function (checkboxId, divId, parentDivId) {
        var checkbox = $("#" + this.escapeDots(checkboxId));
        var escapedDivtId = this.escapeDots(divId);
        var escapedparentDivtId = this.escapeDots(parentDivId);
        var checkboxes = $("#" + escapedparentDivtId + " :input[type=checkbox]");
        checkboxes.not(checkbox).prop("checked", false);
        var currentInputs = $("#" + escapedDivtId + " :input");
        currentInputs.removeAttr('disabled');
        $("#" + escapedparentDivtId + " :input").not(currentInputs).attr('disabled', true);
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