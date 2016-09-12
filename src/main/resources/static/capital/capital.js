/**
 * Created by Alan on 2016/9/7.
 */
/*----------------------------------------调仓详情弹窗---------------------------------------------*/
$("#adjustBtn").click(function () {
    /*detail-table加载完毕之后使用callback开始绘图*/
    $('#detail-table').fadeIn(300, function () {
        /* ChartJS
         * -------
         * Here we will create a few charts using ChartJS
         */

        //--------------
        //- AREA CHART -
        //--------------

        // Get context with jQuery - using jQuery's .get() method.
        var areaChartCanvas = $("#areaChart").get(0).getContext("2d");
        // This will get the first returned node in the jQuery collection.
        var areaChart = new Chart(areaChartCanvas);

        var areaChartData = {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [
                {
                    label: "Electronics",
                    fillColor: "rgba(210, 214, 222, 1)",
                    strokeColor: "rgba(210, 214, 222, 1)",
                    pointColor: "rgba(210, 214, 222, 1)",
                    pointStrokeColor: "#c1c7d1",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(220,220,220,1)",
                    data: [65, 59, 80, 81, 56, 55, 40]
                },
                {
                    label: "Digital Goods",
                    fillColor: "rgba(60,141,188,0.9)",
                    strokeColor: "rgba(60,141,188,0.8)",
                    pointColor: "#3b8bba",
                    pointStrokeColor: "rgba(60,141,188,1)",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(60,141,188,1)",
                    data: [28, 48, 40, 19, 86, 27, 90]
                }
            ]
        };

        var areaChartOptions = {
            //Boolean - If we should show the scale at all
            showScale: true,
            //Boolean - Whether grid lines are shown across the chart
            scaleShowGridLines: false,
            //String - Colour of the grid lines
            scaleGridLineColor: "rgba(0,0,0,.05)",
            //Number - Width of the grid lines
            scaleGridLineWidth: 1,
            //Boolean - Whether to show horizontal lines (except X axis)
            scaleShowHorizontalLines: true,
            //Boolean - Whether to show vertical lines (except Y axis)
            scaleShowVerticalLines: true,
            //Boolean - Whether the line is curved between points
            bezierCurve: true,
            //Number - Tension of the bezier curve between points
            bezierCurveTension: 0.3,
            //Boolean - Whether to show a dot for each point
            pointDot: false,
            //Number - Radius of each point dot in pixels
            pointDotRadius: 4,
            //Number - Pixel width of point dot stroke
            pointDotStrokeWidth: 1,
            //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
            pointHitDetectionRadius: 20,
            //Boolean - Whether to show a stroke for datasets
            datasetStroke: true,
            //Number - Pixel width of dataset stroke
            datasetStrokeWidth: 2,
            //Boolean - Whether to fill the dataset with a color
            datasetFill: true,
            //String - A legend template
            legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
            //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
            maintainAspectRatio: true,
            //Boolean - whether to make the chart responsive to window resizing
            responsive: true
        };

        //Create the line chart
        areaChart.Line(areaChartData, areaChartOptions);
    })
    $('#main-content').css('opacity', '0.3');

});

/*----------------------------------------数据详情弹窗---------------------------------------------*/
$('#close').click(function (e) {
    $('#detail-table').fadeOut(300);
    $('#main-content').css('opacity', '1');
});

/*----------------------------确认调仓结果响应事件--------------------------------------------------*/
$('#confirmAdjustBtn').click(function(e){
        $('#detail-table').fadeOut(300);
        $('#main-content').css('opacity', '1');
}

);

var futuresID = getParam(location.href, "futures_id");
var getHistoryUrl = "/api/capital";

//loadResource();

function loadResource() {
    $.ajax({
        url: window.host + getHistoryUrl,
        data: {
            futures_id: futuresID
        },
        timeout: 5000,
        success: function (res) {
            var data = response(res);
            if (data != null) {
                var cost = data.cost;
                var delta = data.delta;
                var safe=data.safe;
                var historyList = data.data;
                for (var i = 0; i < historyList.length; i++) {
                    var due_date = historyList[i].due_date;
                    var single_delta = historyList[i].delta;
                    var sell_price = historyList[i].sell_price;
                    var future_price= historyList[i].future_price;
                    var single_cost= historylist[i].cost;
                    loadHistory(date, price, historyQuantity);
                }
            }
        }
    });
}

var history_template =
    '<tr> ' +
    '<td>due_date</td> ' +
    '<td>single_delta</td> ' +
    '<td>sell_price</td> ' +
    '<td>future_price</td> ' +
    '<td>single_cost</td> ' +
    '<td>sell_price</td> ' +
    '<td><input type="checkbox" name="option_id_picker"></td>'+
    '</tr>';

/**
 * 加载历史信息
 * @param date
 * @param price
 * @param quantity
 */
function loadHistory(due_date, single_delta, se) {
    var tags = ['due_date', 'single_delta', 'sell_price','future_price','single_cost','sell_price','<input type="checkbox" name="option_id_picker">'];
    var data = [date, price, quantity];
    var template = replaceTemplate(history_template, tags, data);

    $('#data-table').append(template);
}

