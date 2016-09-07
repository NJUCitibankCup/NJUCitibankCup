/**
 * Created by Alan on 2016/9/7.
 */

/*------------------------------------主数据加载----------------------------------------------*/
preLoad();

/**
 * 加载页面资源
 */
function preLoad() {
    $.ajax({})
}


/**
 * 加载数据弹窗
 */
function showMore() {
    $('#detail-table').fadeIn(300);
    $('#main-content').css('opacity', '0.3');
}

var more_template =
    '<div onclick="showMore(\'capital-id\')" class="text-primary text-center" style="padding: 10px;cursor: pointer;">' +
    '查看更多 <i class="fa fa-arrow-circle-right"></i> ' +
    '</div>';

/**
 * 加载DOM，展示“更多”按钮
 * @param divID
 * @param capitalID
 */
function loadMoreTag(divID, capitalID) {
    var template = more_template.replace('capital-id', capitalID);
    $('#' + divID).append(template);
}

/*----------------------------------------数据详情弹窗---------------------------------------------*/
$('#close').click(function (e) {
    $('#detail-table').fadeOut(300);
    $('#main-content').css('opacity', '1');
});

/**
 * 加载原始数据
 */
function loadResource() {
    $.ajax({});
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
    '<button onclick="comb(\'capital-id\')" class="btn btn-block btn-default btn-xs">资产组合</button>' +
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
    var tags = ['type-name', 'type-num', 'type-delta', 'success', 'capital-id'];
    var data = [name, num, delta, deltaCon, capitalID];
    var template = replaceTemplate(data_template, tags, data);

    $('#data-table').append(template);
}
