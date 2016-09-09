/**
 * Created by Alan on 2016/9/7.
 */

/**
 * 监听是否选择障碍期权
 * @param select
 */
function changeOptionType(select) {
    if ($(select).val() == "障碍期权") {
        $('#block-level').show(300);
    } else {
        $('#block-level').hide(300);
    }
}