/**
 * 
 */

function initDropDownLists() {
            var categories = $("#Category").kendoDropDownList({
                optionLabel: "Select category...",
                dataTextField: "CategoryName",
                dataValueField: "CategoryID",
                dataSource: {
                    type: "odata",
                    serverFiltering: true,
                    transport: {
                        read: "https://demos.telerik.com/kendo-ui/service/Northwind.svc/Categories"
                    }
                }
            }).data("kendoDropDownList");

            var products = $("#Product").kendoDropDownList({
                autoBind: false,
                cascadeFrom: "Category",
                optionLabel: "Select product...",
                dataTextField: "ProductName",
                dataValueField: "ProductID",
                dataSource: {
                    type: "odata",
                    serverFiltering: true,
                    transport: {
                        read: "https://demos.telerik.com/kendo-ui/service/Northwind.svc/Products"
                    }
                }
            }).data("kendoDropDownList");

            var orders = $("#Order").kendoDropDownList({
                autoBind: false,
                cascadeFrom: "Product",
                optionLabel: "Select quantity...",
                dataTextField: "Quantity",
                dataValueField: "OrderID",
                dataSource: {
                    type: "odata",
                    serverFiltering: true,
                    transport: {
                        read: "https://demos.telerik.com/kendo-ui/service/Northwind.svc/Order_Details?$expand=Order"
                    }
                }
            }).data("kendoDropDownList");
    }