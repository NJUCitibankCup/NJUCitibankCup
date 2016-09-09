/**
 * Created by Alan on 2016/9/7.
 */

/*-------------------------------URL����-------------------------------------*/
window.host = "http://";
window.capitalUrl = host + "capital/capital.html";
window.logUrl = host + "log/log.html";

function addParams(url, key, value) {
    if (url.charAt(url.length - 1) != "?" && url.charAt(url.length - 1) != "&") {
        url += "?";
    }
    return url + transMap(key, value);
}

/**
 * ת��map
 * @param key
 * @param value
 * @returns {string}
 */
function transMap(key, value) {
    return key + "=" + value + "&";
}

/**
 * ��ò���
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

/*-------------------------------DOM����-------------------------------------*/
/**
 * �滻htmlģ��
 * @param resource
 * @param params
 * @param data
 */
function replaceTemplate(resource, params, data) {
    for (var i = 0; i < params.length && i < data.length; i++) {
        resource.replace(params[i], data[i]);
    }
    return resource;
}

/*-------------------------------Http����-------------------------------------*/
function response(json) {
    //��ӡ����
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