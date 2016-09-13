/**
 * Created by PolarisChen on 2016/9/14.
 */

$(function() {
  getFuturesType();
  var currentType = $('#futures_type').val();
  getFuturesId(currentType);
})

$('#futures_type').change(function() {
  var type = $(this).val();
  getFuturesId(type);
})

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
  res.data.map(function(type) {
    $type.append('<option>' + type + '</option>');
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
  res.data.map(function(id) {
    $id.append('<option>' + id.futures_name + '</option>');
  });
}

function changeOptionType(select) {
 if ($(select).val() === "障碍期权") {
   $('#block-level').show(300);
 } else {
   $('#block-level').hide(300);
 }
}
