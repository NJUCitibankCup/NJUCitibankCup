/**
 * Created by PolarisChen on 2016/9/14.
 */
$(function () {
    getFuturesType(function (types) {
        // console.log(types);
        var $type = $('#futures_type');
        $type.html('');
        types.map(function (type, index) {
            $type.append('<option value="' + type + '">' + type + '</option>');
        });
        var currentType = $type.val();
        getFuturesId(currentType, updateFuturesId);
        loadNews(currentType);
    });
});

$('#futures_type').change(function () {
    var type = $(this).val();
    getFuturesId(type, updateFuturesId);
});

$('#option_type').change(function () {
    var $group = $('#block_level_group');
    var $level = $('#block_level');
    var $price = $('#option_price');
    if ($(this).val() === 'Ba') {
        $group.show(300);
        $level.focus();
    } else {
        $group.hide(300);
        $price.focus();
    }
    $level.val('');
    $price.val('');
});

$('#calc_price').click(function () {
    var data = getData();
    $('#price-modal').fadeIn(300);
    getOptionsPrice(data, function (price) {
        $('#output_price').html(price);
    });
});

$('#sell_options').click(function () {
    var data = getData();
    sellOptions(data, function (condition) {
        $('#alert_' + condition).show(300);
        setTimeout(function () {
            $('.alert').hide(300);
        }, 5000);
    });
});

$('.close_alert').click(function () {
    $(this).parent().hide(300);
});

function getData() {
    var id = $('#futures_id').val();
    var type = $('#option_type').val();
    var price = $('#option_price').val() | 0;
    var h = type === 'Ba' ? ($('#block_level').val() | 0) : -1;
    var data = {
        futures_id: id,
        type: type,
        price: price,
        h: h
    };
    console.log(data);
    return data;
}


function updateFuturesId(ids) {
    var $id = $('#futures_id');
    $id.html('');
    ids.map(function (id) {
        $id.append('<option value="' + id.futures_id + '">' + id.futures_name + '</option>');
    });
}

function getFuturesType(callback) {
    var url = 'http://120.27.117.222:8080/api/getFuturesType';
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'json',
        success: function (res) {
            // console.log(res);
            if (res.condition === 'success') {
                callback(res.data);
            }
        }
    });
}

function getFuturesId(type, callback) {
    var url = 'http://120.27.117.222:8080/api/getFuturesId';
    var data = {
        type: type
    }
    $.ajax({
        type: 'GET',
        url: url,
        data: data,
        dataType: 'json',
        success: function (res) {
            if (res.condition === 'success') {
                callback(res.data);
            }
        }
    });
}

function getOptionsPrice(data, callback) {
    var url = 'http://120.27.117.222:8080/api/getOptionsPrice';
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        dataType: 'json',
        success: function (res) {
            // console.log(res);
            if (res.condition === 'success') {
                callback(res.data);
            }
        }
    });
}

function sellOptions(data, callback) {
    var url = 'http://120.27.117.222:8080/api/sellOptions';
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        dataType: 'json',
        success: function (res) {
            // console.log(res);
            callback(res.condition);
        }
    });
}

function loadNews(currentType) {
    var newsUrl = "/api/news";
    var news_template =
        '<div class="box-body">' +
        '<a href="news-link" class="feedback text-black" target="_Blank">news-content ' +
        '<i class="fa fa-arrow-circle-right text-green"></i></a> ' +
        '</div>';

    $.ajax({
        url: window.host + newsUrl,
        data: {
            key_word: currentType
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
function changeTypeNews(type) {
    $('#news-list').text('');
    $('#news-loader').show();
    loadNews(type);
}