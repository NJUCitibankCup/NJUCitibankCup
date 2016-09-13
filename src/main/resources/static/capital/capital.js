/**
 * Created by Alan on 2016/9/7.
 */
/*----------------------------------------调仓图表弹窗---------------------------------------------*/
var selectOptionsUrl = "/api/selectOptions";
var xData, yData;
var chart;
var gammaList = {};
var gamma;

$('#close').click(function (e) {
    $('#detail-table').fadeOut(300);
    $('#main-content').css('opacity', '1');
});

$("#adjustBtn").click(function () {
    $('#chart-div').show();
    $('#table-div').hide();
    $('#detail-table').fadeIn(300);
    var idList = [];
    $("input[name='option_id_picker']").each(function () {
        if ($(this).is(':checked')) {
            idList.push($(this).parent().prev('input').val());
        }
    });
    xData = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    yData = [10, 12, 13, 16, 9, 8, 7, 14, 2];
    var dom = document.getElementById("chart");
    chart = echarts.init(dom);
    chart.showLoading();
    loadFunction(chart, xData, yData);

    $.ajax({
        url: window.host + selectOptionsUrl,
        data: {
            option_list: idList
        },
        timeout: 5000,
        success: function (res) {
            var data = response(res);
            if (data != null) {
                xData = data.x_data;
                yData = data.y_data;
                for (var i = 0; i < xData.length && i < yData.length; i++) {
                    gammaList[xData[i]] = yData[i];
                }
                /* ION SLIDER */
                $("#range_1").ionRangeSlider({
                    min: 1,
                    max: 1501,
                    from: 1,
                    type: 'single',
                    step: 50,
                    prefix: "Gamma ",
                    prettify: false,
                    hasGrid: true,
                    onStart: function () {
                        var currentGamma = data[1];
                        $('#var').text(currentGamma);
                        gamma = currentGamma;
                    },
                    onChange: function (res) {
                        var currentGamma = data[res.from];
                        $('#var').text(currentGamma);
                        gamma = currentGamma;
                    }
                });
                loadFunction(chart, xData, yData);
            }
        }
    });
});

/**
 * 加载函数
 * @param chart
 * @param xData
 * @param yData
 */
function loadFunction(chart, xData, yData) {
    var option = {
        title: {
            text: 'VAR Selector'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['Gamma']
        },
        grid: {
            left: '5%',
            right: '5%',
            bottom: '3%',
            nameGap: 30,
            containLabel: true
        },
        xAxis: [
            {
                name: 'Gamma',
                nameLocation: 'middle',
                type: 'category',
                boundaryGap: false,
                data: xData
            }
        ],
        yAxis: [
            {
                name: 'Var',
                type: 'value'
            }
        ],
        series: [
            {
                name: 'VAR',
                type: 'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data: yData
            }
        ]
    };
    chart.setOption(option, true);
    chart.hideLoading();
}

function resizeChart() {
    chart.resize();
}

var predictResUrl = "/api/predictResult";

/**
 * 进入预览界面
 */
function preview() {
    $.ajax({
        url: window.host + predictResUrl,
        data: {
            lower_gamma: gamma
        },
        timeout: 5000,
        success: function (res) {
            var data = response(res);
            if (data != null) {
                $('#name').text(data.futures_name);
                $('#number').text(data.number);
                $('#origin-cost').text(data.origin_cost);
                $('#origin-number').text(data.origin_number);
                $('#current-number').text(data.current_number);
                $('#origin-delta').text(data.origin_delta);
                $('#current-delta').text(data.current_delta);
                $('#current-var').text(data.var);
                if (data.safe == "warning") {
                    $('#predict-safety').removeClass('label-success').addClass('label-warning').text("warning");
                }
                $('#chart-div').hide(300);
                $('#table-div').show(300);
            }
        }
    });
}

/*----------------------------------------数据详情弹窗---------------------------------------------*/

function reAdjust() {
    $('#chart-div').show(300);
    $('#table-div').hide(300);
}

function confirmAdjust() {
    $.ajax({
        url: window.host + predictResUrl,
        data: {
            futures_id: futuresID,
            lower_gamma: gamma
        },
        timeout: 5000,
        success: function (res) {
            var data = response(res);
            if (data != null) {
                //TODO 成功反馈
            }
        }
    })
}

/*-----------------------------------------数据加载---------------------------------------------------*/
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
                var safe = data.safe;
                $('#cost').text(cost);
                $('#delta').text(delta);
                switch (safe) {
                    case "warning":
                        $('#safe-block').removeClass('bg-green').addClass('bg-red');
                        $('#safe-div').removeClass('text-green').addClass('text-red');
                        $('#safe-condition').text(safe);
                        break;
                }
                var historyList = data.data;
                for (var i = 0; i < historyList.length; i++) {
                    var due_date = historyList[i].due_date;
                    var single_delta = historyList[i].delta;
                    var sell_price = historyList[i].sell_price;
                    var future_price = historyList[i].futures_price;
                    var single_cost = historylist[i].cost;
                    var type = historyList[i].type;
                    loadHistory(due_date, single_delta, sell_price, future_price, single_cost, type);
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
    '<td>type</td>' +
    '<input type="hidden" value="">' +
    '<td><input type="checkbox" name="option_id_picker"></td>' +
    '</tr>';

/**
 * 加载历史信息
 * @param due_date
 * @param single_delta
 * @param sell_price
 * @param future_price
 * @param single_cost
 * @param type
 */
function loadHistory(due_date, single_delta, sell_price, future_price, single_cost, type) {
    var tags = ['due_date', 'single_delta', 'sell_price', 'future_price', 'single_cost', 'type'];
    var data = [due_date, single_delta, sell_price, future_price, single_cost, type];
    var template = replaceTemplate(history_template, tags, data);

    $('#data-table').append(template);
}
