/**
 * Created by PolarisChen on 2016/9/14.
 */
$('.alert').hide();

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
        h: h
    }
    console.log(data);
    return data;
}

function getFuturesType() {
    var $type = $('#futures_type');

    // var url = '/api/getFuturesType';
    // $.ajax({
    //   type: 'GET',
    //   url: url,
    //   dataType: 'json',
    //   success: function(res) {
    //     $type.html('');
    //     res.data.map(function(type) {
    //       $type.append('<option>' + type + '</option>');
    //     });
    //   }
    // });

    var res = {
        msg: '',
        condition: 'success',
        data: [
            "玉米", "大豆", "豆粕"
        ]
    }
    $type.html('');
    res.data.map(function (type, index) {
        $type.append('<option value="' + type + '">' + type + '</option>');
    });
}

function getFuturesId(type) {
    var $id = $('#futures_id');
    // var url = '/api/getFuturesId';
    // var data = {
    //   type: type
    // }
    // $.ajax({
    //   type: 'GET',
    //   url: url,
    //   data: data,
    //   dataType: 'json',
    //   success: function(res) {
    //     $id.html('');
    //     res.data.map(function(id) {
    //       $type.append('<option>' + id.futures_name + '</option>');
    //     });
    //   }
    // });

    var res = {
        msg: "",
        condition: "success",
        data: [
            {
                futures_id: "M1701",
                futures_name: type + "1701"
            },
            {
                futures_id: "M1702",
                futures_name: type + "1702"
            },
        ]
    }

    $id.html('');
    res.data.map(function (id) {
        $id.append('<option value="' + id.futures_id + '">' + id.futures_name + '</option>');
    });
}

function getOptionsPrice(data) {
    // var url = '/api/getOptionsPrice';
    // $.ajax({
    //   type: 'POST',
    //   url: url,
    //   data: data,
    //   dataType: 'json',
    //   success: function(res) {
    //     $('#output_price').val(res.data.price);
    //   }
    // });

    var res = {
        msg: "",
        condition: "success",
        data: {
            price: 100
        }
    }
    $('#output_price').html(res.data.price);
}

function sellOptions(data) {
    // var url = '/api/sellOptions';
    // $.ajax({
    //   type: 'POST',
    //   url: url,
    //   data: data,
    //   dataType: 'json',
    //   success: function(res) {
    //     if (res.condition === 'success') {
    //       $('#alert_success').show();
    //     }
    //     setTimeout(function () {
    //       $('.alert').hide(300);
    //     }, 5000);
    //   }
    // });

    var res = {
        msg: "",
        condition: "fail",
        data: {}
    }
    if (res.condition === 'success') {
        $('#alert_success').show(300);
    } else {
        $('#alert_fail').show(300);
    }
    setTimeout(function () {
        $('.alert').hide(300);
    }, 5000);
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
