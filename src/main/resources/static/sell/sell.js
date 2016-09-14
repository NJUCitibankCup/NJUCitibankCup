/**
 * Created by PolarisChen on 2016/9/14.
 */

$(function() {
  $('.alert').hide();
  getFuturesType(function(types) {
    // console.log(types);
    var $type = $('#futures_type');
    $type.html('');
    types.map(function(type, index) {
      $type.append('<option value="' + type + '">' + type + '</option>');
    });
  });

  var currentType = $('#futures_type').val();
  getFuturesId(currentType, updateFuturesId);
})

$('#futures_type').change(function () {
  var type = $(this).val();
  getFuturesId(type, updateFuturesId);
})

$('#option_type').change(function () {
  var $level = $('#block_level_group');
  if ($(this).val() === 'Ba') {
    $level.show(300);
  } else {
    $level.hide(300);
  }
})

$('#calc_price').click(function () {
  var data = getData();
  getOptionsPrice(data, function (price) {
    $('#output_price').html(price);
  });
})

$('#sell_options').click(function () {
  var data = getData();
  sellOptions(data, function (condition) {
    $('#alert_' + condition).show(300);
    setTimeout(function () {
      $('.alert').hide(300);
    }, 5000);
  });
})

$('.close_alert').click(function () {
  $(this).parent().hide(300);
})

function getData() {
  var id = $('#futures_id').val();
  var type = $('#option_type').val();
  var price = $('#option_price').val() | 0;
  var h = type === 'Ba' ? ($('#block_level').val() | 0) : -1;
  var data = {
    futures_id: id,
    type: type,
    price: price,
    H: h
  }
  // console.log(data);
  return data;
}

function updateFuturesId(ids) {
  var $id = $('#futures_id');
  $id.html('');
  ids.map(function(id) {
    $id.append('<option value="' + id.futures_id + '">' + id.futures_name + '</option>');
  });
}

function getFuturesType(callback) {
  var url = 'http://120.27.117.222:8080/api/getFuturesType';
  $.ajax({
    type: 'GET',
    url: url,
    dataType: 'json',
    success: function(res) {
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
    success: function(res) {
      // console.log(res);
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
    success: function(res) {
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
    success: function(res) {
      // console.log(res);
      callback(res.condition);
    }
  });
}
