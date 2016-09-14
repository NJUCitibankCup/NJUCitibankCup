/**
 * Created by Alan on 2016/9/7.
 */

var optionStateUrl = "/api/optionState";

/*------------------------------------主数据加载----------------------------------------------*/
$(function () {
    preLoad();
    loadNews();
});


/**
 * 加载页面资源
 */
function preLoad() {
    $.ajax({
        url: window.host + optionStateUrl,
        timeout: 5000,
        success: function (res) {
            var data = response(res);
            if (data != null) {
                $('#main-loader').hide();
                for (var i = 0; i < data.length; i++) {
                    var type = data[i].type;
                    var detailDataList = data[i].data;
                    var detail = "";
                    var moreTag = "";
                    for (var j = 0; j < detailDataList.length; j++) {
                        if (j > 4) {
                            moreTag = loadMoreTag(type);
                            break;
                        } else {
                            var futuresID = detailDataList[j].futures_id;
                            var futuresName = detailDataList[j].futures_name;
                            var number = detailDataList[j].number;
                            var delta = detailDataList[j].delta;
                            var safe = detailDataList[j].safe;
                            detail += loadFuturesContent(futuresID, futuresName, number, delta, safe);
                        }
                    }
                    loadFutures(type, detail, moreTag);
                }
            } else {
                $('#no-data').show(300);
            }
        }
    })
}

var futures_template =
    '<div class="col-md-6"> ' +
    '<div class="box box-primary"> ' +
    '<div class="box-header"> ' +
    '<h3 class="box-title default-font">futures-name</h3> ' +
    '</div> ' +
    '<div class="box-body table-responsive no-padding"> ' +
    '<table class="table table-hover"> ' +
    '<tr>' +
    '<th>期权</th> ' +
    '<th>数量</th> ' +
    '<th>Delta</th> ' +
    '<th>操作</th> ' +
    '</tr> ' +
    'futures-content' +
    '</table>' +
    'futures-more' +
    '</div></div> </div>';

var futures_content_template =
    '<tr>' +
    '<td>futures-name</td>' +
    '<td>futures-num</td>' +
    '<td><span class="label label-futures-safe">futures-delta</span></td>' +
    '<td>' +
    '<button onclick="gotoCapital(\'futures-id\')" class="btn btn-default btn-xs" style="width: 45%;">' +
    '<i class="fa fa-th"></i>  资产组合</button>' +
    '<button onclick="gotoDetail(\'futures-id\')" class="btn btn-default btn-xs" style="width: 45%;margin-left: 10px">' +
    '<i class="fa fa-cny"></i>  交易详情</button>' +
    '</td>' +
    '</tr>';

/**
 * 加载期权信息
 * @param type
 * @param detail
 * @param moreTag
 */
function loadFutures(type, detail, moreTag) {
    var template = futures_template.replace('futures-name', type + "资产组合");
    template = template.replace('futures-content', detail);
    template = template.replace('futures-more', moreTag);
    $('#option-list').append(template);
}

/**
 * 加载期权详情
 * @param futuresID
 * @param futuresName
 * @param number
 * @param delta
 * @param safe
 */
function loadFuturesContent(futuresID, futuresName, number, delta, safe) {
    var template = futures_content_template.replace("futures-name", futuresName);
    template = template.replace(/futures-id/g, futuresID);
    if (safe == "safe") {
        template = template.replace('futures-safe', "success");
    } else {
        template = template.replace('futures-safe', "warning");
    }
    template = template.replace('futures-num', number);
    template = template.replace('futures-delta', delta.toFixed(4));
    return template;
}

var more_template =
    '<div onclick="showMore(\'capital-id\')" class="text-primary text-center" style="padding: 10px;cursor: pointer;">' +
    '查看更多 <i class="fa fa-arrow-circle-right"></i> ' +
    '</div>';

/**
 * 加载DOM，展示“更多”按钮
 * @param capitalID
 */
function loadMoreTag(capitalID) {
    return more_template.replace('capital-id', capitalID);
}

/*----------------------------------------数据详情弹窗---------------------------------------------*/

/**
 * 加载数据弹窗
 */
function showMore(capitalID) {
    $('#data-table').html("");
    $('#detail-table').fadeIn(300);
    $('#main-content').fadeTo(300, 0.3);
    loadResource(capitalID);
}

$('#close').click(function (e) {
    $('#detail-table').fadeOut(300);
    $('#main-content').fadeTo(300, 1);
});

/**
 * 加载原始数据
 */
function loadResource(originCapitalID) {
    $.ajax({
        url: window.host + optionStateUrl,
        timeout: 5000,
        success: function (res) {
            var data = response(res);
            if (data != null) {
                $('#type-name').text(originCapitalID);
                for (var i = 0; i < data.length; i++) {
                    var type = data[i].type;
                    if (type == originCapitalID) {
                        var detailDataList = data[i].data;
                        var detail = "";
                        var moreTag = "";
                        for (var j = 0; j < detailDataList.length; j++) {
                            var futuresID = detailDataList[j].futures_id;
                            var number = detailDataList[j].number;
                            var delta = detailDataList[j].delta;
                            var safe = detailDataList[j].safe;
                            loadData(futuresID, number, delta, safe);
                        }
                    }
                }
            }
        }
    });
}

/**
 * 数据html模版
 * @type {string}
 */
var data_template =
    '<tr>' +
    '<td width="250">type-name</td>' +
    '<td width="200">type-num</td>' +
    '<td width="150"><span class="label label-success">type-delta</span></td>' +
    '<td>' +
    '<button onclick="gotoCapital(\'futures-id\')" class="btn btn-default btn-xs" style="width: 45%;">组合详情</button>' +
    '<button onclick="gotoDetail(\'futures-id\')" class="btn btn-default btn-xs" style="width: 45%;margin-left: 10px">' +
    '对冲详情' +
    '</button>' +
    '</td>' +
    '</tr>';

/**
 * 加载Dom
 * @param name
 * @param num
 * @param delta
 * @param deltaCon
 * @param capitalID
 */
function loadData(name, num, delta, deltaCon, capitalID) {
    var tags = ['type-name', 'type-num', 'type-delta', 'success', /futures-id/g];
    var data = [name, num, delta, deltaCon, capitalID];
    var template = replaceTemplate(data_template, tags, data);

    $('#data-table').append(template);
}

/*------------------------------------------加载新闻---------------------------------------------------*/
function loadNews() {
    var newsUrl = "/api/news";
    var news_template =
        '<div class="box-body">' +
        '<a href="news-link" class="feedback text-black" target="_Blank">news-content ' +
        '<i class="fa fa-arrow-circle-right text-green"></i></a> ' +
        '</div>';

    $.ajax({
        url: window.host + newsUrl,
        data: {
            key_word: "期权"
        },
        timeout: 10000,
        success: function (res) {
            $('#news-loader').hide();
            var data = response(res);
            if (data != null) {
                for (var i = 0; i < data.length; i++) {
                    var tags = ['news-link', 'news-content'];
                    var currentData = [data[i].link, data[i].title];
                    $('#news-list').append(replaceTemplate(news_template, tags, currentData));
                }
            }
        }
    })
}

/*------------------------------------------跳转---------------------------------------------------*/
function gotoCapital(futuresID) {
    location.href = addParams(window.capitalUrl, "futures_id", futuresID);
}

function gotoDetail(futuresID) {
    location.href = addParams(window.logUrl, "futures_id", futuresID);
}