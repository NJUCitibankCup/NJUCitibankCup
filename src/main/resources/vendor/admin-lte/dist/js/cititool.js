/**
 * Created by Alan on 2016/9/7.
 */
/**
 * Ìæ»»htmlÄ£°æ
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