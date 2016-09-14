/**
 * Created by Alan on 2016/9/7.
 */

/*-------------------------------URL操作-------------------------------------*/
window.host = "http://114.212.242.193:8080";
window.proxy = "http://localhost:63342/resources/static";
window.capitalUrl = proxy + "/capital/capital.html";
window.logUrl = proxy + "/log/log.html";

function addParams(url, key, value) {
    if (url.charAt(url.length - 1) != "?" && url.charAt(url.length - 1) != "&") {
        url += "?";
    }
    return url + transMap(key, value);
}

/**
 * 转换map
 * @param key
 * @param value
 * @returns {string}
 */
function transMap(key, value) {
    return key + "=" + value + "&";
}

/**
 * 获得参数
 * @param sHref
 * @param sArgName
 * @returns {string}
 * @constructor
 */
function getParam(sHref, sArgName) {
    var args = sHref.split("?");
    var retval = "";

    if (args[0] == sHref) {
        return retval;
    }
    var str = args[1];
    args = str.split("&");
    for (var i = 0; i < args.length; i++) {
        str = args[i];
        var arg = str.split("=");
        if (arg.length <= 1) continue;
        if (arg[0] == sArgName) retval = arg[1];
    }
    return retval;
}

function back() {
    history.go(-1);
}

/*-------------------------------DOM操作-------------------------------------*/
/**
 * 替换html模版
 * @param resource
 * @param params
 * @param data
 */
function replaceTemplate(resource, params, data) {
    var newSource = resource;
    for (var i = 0; i < params.length && i < data.length; i++) {
        newSource = newSource.replace(params[i], data[i]);
    }
    return newSource;
}

/*-------------------------------Http操作-------------------------------------*/
function response(json) {
    //打印返回
    console.log(json);

    var result = json.condition;
    var msg = json.msg;
    var data = json.data;

    if (result == "success") {
        return data;
    } else {
        alert(msg);
        return null;
    }
}