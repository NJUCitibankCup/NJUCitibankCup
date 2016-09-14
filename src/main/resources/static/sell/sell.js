/**
 * Created by PolarisChen on 2016/9/14.
 */

$(function() {
  $('.alert').hide();

  var types = getFuturesType();
  var $type = $('#futures_type');
  $type.html('');
  types.map(function(type, index) {
    $type.append('<option value="' + type + '">' + type + '</option>');
  });

  var currentType = $('#futures_type').val();
  var ids = getFuturesId(currentType);
  updateFuturesId(ids);
})

$('#futures_type').change(function () {
  var type = $(this).val();
  var ids = getFuturesId(type);
  updateFuturesId(ids);
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
  var price = getOptionsPrice(data);
  $('#output_price').html(price);
})

$('#sell_options').click(function () {
  var data = getData();
  var isSuccess = sellOptions(data);
  if (isSuccess) {
    $('#alert_success').show(300);
  } else {
    $('#alert_fail').show(300);
  }
  setTimeout(function () {
    $('.alert').hide(300);
  }, 5000);
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
    h: h
  }
  console.log(data);
  return data;
}

function updateFuturesId(ids) {
  var $id = $('#futures_id');
  $id.html('');
  ids.map(function(id) {
    $id.append('<option value="' + id.futures_id + '">' + id.futures_name + '</option>');
  });
}

function getFuturesType() {
  var url = '/api/getFuturesType';
  $.ajax({
    type: 'GET',
    url: url,
    dataType: 'json',
    success: function(res) {
      if (res.condition === 'success') {
        return res.data;
      } else {
        return [];
      }
    }
  });
}

function getFuturesId(type) {
  var url = '/api/getFuturesId';
  var data = {
    type: type
  }
  $.ajax({
    type: 'GET',
    url: url,
    data: data,
    dataType: 'json',
    success: function(res) {
      if (res.condition === 'success') {
        return res.data;
      } else {
        return [];
      }
    }
  });
}

function getOptionsPrice(data) {
  var url = '/api/getOptionsPrice';
  $.ajax({
    type: 'POST',
    url: url,
    data: data,
    dataType: 'json',
    success: function(res) {
      if (res.condition === 'success') {
        return res.data.price;
      } else {
        return -1;
      }
    }
  });
}

function sellOptions(data) {
  var url = '/api/sellOptions';
  $.ajax({
    type: 'POST',
    url: url,
    data: data,
    dataType: 'json',
    success: function(res) {
      if (res.condition === 'success') {
        return true;
      } else {
        return false;
      }
    }
  });
}
