var clientAddress;
var ivis = null;
var onOpen = null;

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
        currentTab = $(".tabs > .tab[data-tab-page-id='" + tabId + "']");

        if (currentTab.length == 0) {
            $(".tabs > .tab").first().click();
            //.next()

        } else {
            currentTab.click();
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

    getNewItemIndex: function (tag, recursivelly) {
        //var elementId = tag.replace(".", "\\.");
        //recursivelly = recursivelly != null ? recursivelly : true;
        var value = 0;
        var elements = $("#" + this.escapeDots(this.escapeBrackets(tag)) + " > [data-index]");

        if (elements.length > 0) {
            elements.each(function (index, element) {
                if (parseInt($(element).data("index")) > value)
                    value = parseInt($(element).data("index"));
            });
            return value + 1;
        } else
            return 0;
    },

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

        this.addField(container, itemIndex, subConteinerId, "number", "Phone")
            .attr("data-rule-pattern", "\\s*((\\+([\\s-]*\\d[\\s-]*){2}|([\\s-]*\\d[\\s-]*)?)?((\\(([\\s-]*\\d[\\s-]*){3}\\))|([\\s-]*\\d[\\s-]*){3}))?([\\s-]*\\d[\\s-]*){7}");
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

        this.addField(container, itemIndex, subConteinerId, "address", "Email")
            .attr("data-rule-email", "true");
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
        this.addField(container, itemIndex, subConteinerId, "postalCode", "Postal code")
            .attr("data-rule-digits", "true")
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

        return $("<input>")
            .attr("id", itemIdPrefix + itemId + "." + name)
            .attr("name", itemPrefix + "[" + itemId + "]." + name)
            .attr("type", "text")
            .appendTo(owner);
    },

    addSingleField: function (owner, value, name, labelText) {
        var itemId = this.escapeBrackets(name);
        var container = $("<div>")
            .addClass("field")
            .appendTo(owner);

        if (labelText != null) {
            $("<label>")
                .attr("for", itemId)
                .html(labelText)
                .appendTo(container);
        }

        return $("<input>")
            .attr("id", itemId)
            .attr("name", name)
            .attr("type", "text")
            .attr("value", value)
            .appendTo(container);
    },

    //addSingleCheckbox: function (owner, value, name, labelText) {
    //    var itemId = this.escapeBrackets(name);
    //    var container = $("<div>")
    //        .addClass("checkbox")
    //        .appendTo(owner);
    //
    //    if (labelText != null) {
    //        $("<label>")
    //            .attr("for", itemId)
    //            .html(labelText)
    //            .appendTo(container);
    //    }
    //
    //    $("<input>")
    //        .attr("id", itemId)
    //        .attr("name", name)
    //        .attr("type", "checkbox")
    //        .attr("value", value)
    //        .appendTo(container);
    //},

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

    addSingleSelect: function (owner, value, name, items, labelText) {
        var itemId = this.escapeBrackets(name);
        if (labelText != null) {
            $("<label>")
                .attr("for", itemId)
                .html(labelText)
                .appendTo(owner);
        }

        var select = $("<select>")
            .attr("id", itemId)
            .attr("name", name)
            .appendTo(owner);

        for (var i = 0; i < items.length; i++) {
            var item = $("<option>")
                .attr("value", items[i].name)
                .html(items[i].description)
                .appendTo(select);

            if (value == items[i].name) {
                item.attr("selected", "selected");
            }
        }
    },

    addButton: function () {

    },

    onSchoolChange: function (value) {
        $.getJSON(clientAddress + "/api/content/rest/School/" + value,
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

                var afterSchoolCenterSections = result.afterSchoolCenterSections;
                var afterSchoolCenterSection = $("#afterSchoolCenterSection");
                afterSchoolCenterSection.html("");

                for (var i = 0; i < afterSchoolCenterSections.length; i++) {
                    $("<option>")
                        .attr("value", afterSchoolCenterSections[i].id)
                        .html(afterSchoolCenterSections[i].name)
                        .appendTo(afterSchoolCenterSection);
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
            if (element.html().trim() == "") {
                ivisOAuth(clientAddress + '/persons_list.jsp');
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

        if (checkbox[0].checked) {
            $("#" + escapedparentDivtId + " :input").not(currentInputs).not(checkboxes).attr('disabled', true);
        } else {
            $("#" + escapedparentDivtId + " :input").removeAttr('disabled');
        }
    },

    selectRow: function (id) {
        $("tr").removeClass("selected");
        $("tr[data-application-id='" + id + "']").addClass("selected");
    },

    selectItem: function (itemName) {
        var row = $("tr.selected");
        if (row.length == 0) {
            return;
        }

        window.opener.onSelectedItem(itemName, row.data("applicationId"));
        window.close();
    },

    displayPersonInfo: function (person, owner, itemNamePrefix, context) {
        var itemIdPrefix = this.escapeBrackets(itemNamePrefix);
        $("<input>").attr("id", itemIdPrefix + ".id")
            .attr("name", itemNamePrefix + ".id")
            .attr("type", "hidden")
            .attr("value", person.id)
            .appendTo(owner);

        context.addSingleField(owner, person.personalId, itemNamePrefix + ".personalId", "Personal Id");
        context.addSingleField(owner, person.firstName, itemNamePrefix + ".firstName", "First name");
        context.addSingleField(owner, person.lastName, itemNamePrefix + ".lastName", "Last name");

        var subConteinerId = itemIdPrefix + ".addresses";
        var subConteinerName = itemNamePrefix + ".addresses";

        $("<H2>").html("Addresses:").appendTo(owner);
        //var container0 = $("<div>").attr("id", itemNamePrefix + ".addreses").appendTo(owner);
        var collectionCcontainer = $("<div>").attr("id", subConteinerId).appendTo(owner);
        var collection = person.addresses;
        for (i = 0; i < collection.length; i++) {
            var conteinerId = context.escapeBrackets(subConteinerId) + i + "Field";
            var container = $("<div>")
                .addClass("field")
                .attr("id", conteinerId)
                .attr("data-index", i)
                .appendTo(collectionCcontainer);

            context.addSingleSelect(container, collection[i].addressType, subConteinerName + "[" + i + "].addressType", addressTypeEnum);

            $("<button>")
                .addClass("negative")
                .attr("type", "button")
                .html("Remove")
                .attr("onClick", "ivis.ui.removeContainer('" + conteinerId + "');")
                .appendTo(container);

            context.addSingleField(container, collection[i].careOf, subConteinerName + "[" + i + "].careOf", "c/o");
            context.addSingleField(container, collection[i].street, subConteinerName + "[" + i + "].street", "Street");
            context.addSingleField(container, collection[i].postalCode, subConteinerName + "[" + i + "].postalCode", "Postal code")
                .attr("data-rule-digits", "true");
            context.addSingleField(container, collection[i].city, subConteinerName + "[" + i + "].city", "City");
            context.addSingleField(container, collection[i].municipalityCode, subConteinerName + "[" + i + "].municipalityCode", "Municipality code");

        }
        $("<button>")
            .addClass("positive")
            .attr("type", "button")
            .html("Add")
            .attr("onClick", "ivis.ui.addAddress('" + subConteinerName + "');")
            .appendTo(collectionCcontainer);


        //    Phones:
        var subConteinerId = itemIdPrefix + ".phones";
        var subConteinerName = itemNamePrefix + ".phones";

        $("<H2>").html("Phones:").appendTo(owner);
        var collectionCcontainer = $("<div>").attr("id", subConteinerId).appendTo(owner);
        var collection = person.phones;
        for (i = 0; i < collection.length; i++) {
            var conteinerId = context.escapeBrackets(subConteinerId) + i + "Field";
            var container = $("<div>")
                .addClass("field")
                .attr("id", conteinerId)
                .attr("data-index", i)
                .appendTo(collectionCcontainer);

            context.addSingleSelect(container, collection[i].communicationType, subConteinerName + "[" + i + "].communicationType", communicationTypeEnum);

            $("<button>")
                .addClass("negative")
                .attr("type", "button")
                .html("Remove")
                .attr("onClick", "ivis.ui.removeContainer('" + conteinerId + "');")
                .appendTo(container);

            context.addSingleField(container, collection[i].number, subConteinerName + "[" + i + "].number", "Phone")
                .attr("data-rule-pattern", "\\s*((\\+([\\s-]*\\d[\\s-]*){2}|([\\s-]*\\d[\\s-]*)?)?((\\(([\\s-]*\\d[\\s-]*){3}\\))|([\\s-]*\\d[\\s-]*){3}))?([\\s-]*\\d[\\s-]*){7}");
        }
        $("<button>")
            .addClass("positive")
            .attr("type", "button")
            .html("Add")
            .attr("onClick", "ivis.ui.addPhone('" + subConteinerName + "');")
            .appendTo(collectionCcontainer);

        //    Emails:
        var subConteinerId = itemIdPrefix + ".emails";
        var subConteinerName = itemNamePrefix + ".emails";

        $("<H2>").html("Emails:").appendTo(owner);
        var collectionCcontainer = $("<div>").attr("id", subConteinerId).appendTo(owner);
        var collection = person.emails;
        for (i = 0; i < collection.length; i++) {
            var conteinerId = context.escapeBrackets(subConteinerId) + i + "Field";
            var container = $("<div>")
                .addClass("field")
                .attr("id", conteinerId)
                .attr("data-index", i)
                .appendTo(collectionCcontainer);

            context.addSingleSelect(container, collection[i].communicationType, subConteinerName + "[" + i + "].communicationType", communicationTypeEnum);

            $("<button>")
                .addClass("negative")
                .attr("type", "button")
                .html("Remove")
                .attr("onClick", "ivis.ui.removeContainer('" + conteinerId + "');")
                .appendTo(container);

            context.addSingleField(container, collection[i].address, subConteinerName + "[" + i + "].address", "Email")
                .attr("data-rule-email", "true");
        }
        $("<button>")
            .addClass("positive")
            .attr("type", "button")
            .html("Add")
            .attr("onClick", "ivis.ui.addEmail('" + subConteinerName + "');")
            .appendTo(collectionCcontainer);
    },

    fillContactPerson: function (id) {
        var $this = this;
        var owner = $("#pupil\\.contactPersonField");
        var itemPrefix = "contactPerson";
        $.getJSON(clientAddress + "/api/content/rest/Person/" + id,
            function (result) {
                $this.displayPersonInfo(result, owner, itemPrefix, $this);
            });
    },

    addGuardian: function (id) {
        var $this = this;
        //var owner = $("#guardians0\\.personField");
        //var itemPrefix = "guardians0.person";
        $.getJSON(clientAddress + "/api/content/rest/Guardian/" + id,
            function (result) {
                var mainContainer = $("#guardians");
                var index = $this.getNewItemIndex("guardians");
                var containerId = "guardians" + index;
                var containerName = "guardians[" + index + "]";
                $("<H2>")
                    .html("Guardian " + (index + 1))
                    //.appendTo(mainContainer)
                    .attr("onClick", "ivis.ui.toggleDiv('" + containerId + "');")
                    .insertBefore($("#addGuardianButton"));

                var container = $("<div>")
                    .attr("id", containerId)
                    .attr("data-index", index)
                    //.appendTo(mainContainer);
                    .insertBefore($("#addGuardianButton"));

                var checkbox = $("<div>")
                    .addClass("checkbox")
                    .appendTo(container);
                //.insertBefore($("#addGuardianButton .positive"));

                $("<input>")
                    .attr("id", containerId + ".solo")
                    .attr("type", "checkbox")
                    .appendTo(checkbox);


                $("<label>")
                    .attr("for", containerId + ".solo")
                    .html("Solo guardian")
                    .appendTo(checkbox);
                //.insertBefore($("#addGuardianButton .positive"));

                $("<input>").attr("id", "guardianList" + index + ".id")
                    .attr("name", "guardianList[" + index + "].id")
                    .attr("type", "hidden")
                    .attr("value", result.id)
                    .appendTo(container);
                //.insertBefore($("#addGuardianButton .positive"));

                var owner = $("<div>")
                    .attr("id", containerId + ".personField")
                    .appendTo(container);
                //.insertBefore($("#addGuardianButton .positive"));


                $this.displayPersonInfo(result.person, owner, containerName + ".person", $this);
            });
    },

    redirectOnFirstTabByCookie: function () {
        $.cookie("data-tab-page-id", "applicationTabPage", {path: '/'});
    },

    hideInfoAndShowBtnsChangeDecision: function () {
        $('.decision-info').hide();
        $('.decision-change').show();
    },

    addCommentOnClick: function () {
        $('.comment-link').hide();
        $('.comment-area').show();
    }

    //enableFormValidation: function(formName) {
    //    $('#' + formName).validate();
    //}


};
// function browserDetectNav(chrAfterPoint) {
//     var
//         UA = window.navigator.userAgent,
//
//         OperaB = /Opera[ \/]+\w+\.\w+/i,
//         OperaV = /Version[ \/]+\w+\.\w+/i,
//         FirefoxB = /Firefox\/\w+\.\w+/i,
//         ChromeB = /Chrome\/\w+\.\w+/i,
//         SafariB = /Version\/\w+\.\w+/i,
//         IEB = /MSIE *\d+\.\w+/i,
//         SafariV = /Safari\/\w+\.\w+/i,
//
//         browser = new Array(),
//         browserSplit = /[ \/\.]/i,
//         OperaV = UA.match(OperaV),
//         Firefox = UA.match(FirefoxB),
//         Chrome = UA.match(ChromeB),
//         Safari = UA.match(SafariB),
//         SafariV = UA.match(SafariV),
//         IE = UA.match(IEB),
//         Opera = UA.match(OperaB);
//
//
//     if ((!Opera == "") & (!OperaV == "")) browser[0] = OperaV[0].replace(/Version/, "Opera");
//     else if (!Opera == "")    browser[0] = Opera[0];
//     else if (!IE == "") browser[0] = IE[0];
//     else if (!Firefox == "") browser[0] = Firefox[0];
//     else if (!Chrome == "") browser[0] = Chrome[0];
//     else if ((!Safari == "") && (!SafariV == "")) browser[0] = Safari[0].replace("Version", "Safari");
//
//
//     var outputData;
//
//     if (browser[0] != null) outputData = browser[0].split(browserSplit);
//     if ((chrAfterPoint == null) && (outputData != null)) {
//         chrAfterPoint = outputData[2].length;
//         outputData[2] = outputData[2].substring(0, chrAfterPoint);
//         return (outputData);
//     }
//     else return (false);
// }

var wnd;
function ivisOAuth(authUrl) {
    //var authUrl = "<%=IvisOAuth2Utils.getOAuth2AuthirizationUrl(client, Imcms.getServerProperties().getProperty("AuthorizationCodeHandlerUri"))%>";
    // var browser = browserDetectNav();
    // if (browser[0] === 'Safari') {
    //     location.href = authUrl;
    // } else {

    var width = 600;
    var height = 350;
    var left = (screen.width / 2) - (width / 2);
    var top = (screen.height / 2) - (height / 2);
    var $button = $("<button/>");
    $("body").append($button);
    $($button).click(function () {
        wnd = window.open(authUrl, "newWindow", "left=" + left + ", top=" + top + ", width=" + width + ", height=" + height + ", menubar=0, status=0, resizable=0, scrollbars=0");
    });
    $($button).click();
    $button.hide();
    // }

}

//function openGuardian() {
//    var width = 600;
//    var height = 350;
//    var left = (screen.width / 2) - (width / 2);
//    var top = (screen.height / 2) - (height / 2);
//    wnd = window.open(authUrl, "newwinow", "left=" + left + ", top=" + top + ", width=" + width + ", height=" + height + ", menubar=0, status=0, resizable=0, scrollbars=0");
//}


function authComplete() {
    // var browser = browserDetectNav();
    // if (browser[0] !== 'Safari') {
    wnd.close();
    location.reload();
    // } else {
    //     location.href = '/';
    // }

}

function onSelectedItem(itemName, id) {
    if (itemName == "person") {
        ivis.ui.fillContactPerson(id);
    } else {
        ivis.ui.addGuardian(id);
        //alert(itemName + ": " + id);

    }
}

