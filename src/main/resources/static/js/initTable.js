// 初始化表格数据
function initTable(url,columns){
    // 渲染表格数据
    $("#datatable").bootstrapTable('destroy').bootstrapTable({
        url: url, // 请求url
        method: 'GET', // 请求方式
        contentType:'application/x-www-form-urlencoded;charset=UTF-8',
        toolbar: '#toolbar', // 外部数据
        striped: true, //是否显示行间隔色
        cache: false,  //是否使用缓存，默认为true
        sortable: true, //是否启用排序
        sortOrder: "asc", //排序方式
        sidePagination: "server", //分页方式：client客户端分页，server服务端分页
        showHeader: true,     //是否显示列头
        showLoading: true,
        undefinedText: '-',
        pagination: true, //是否显示分页
        pageNumber: 1, // 当前页码
        pageSize: 10, // 每页的记录行数
        smartDisplay: true,
        pageList: [10, 25, 50, 100], //可供选择的每页的行数
        paginationFirstText: "首页",
        paginationPreText: "上一页",
        paginationNextText: "下一页",
        paginationLastText: "末页",
        paginationHAlign: 'right', // 导航栏显示在右侧
        queryParamsType: "limit",//查询参数组织方式
        search: false, // 这是客户端搜索,如果是服务端传数据建议设置为false
        searchAlign: "left",
        strictSearch: true,
        trimOnSearch: true, // 清除搜索框的空格
        searchTimeOut: 1000, // 设置搜索超时时间
        showButtonText: true, // 显示按钮文字
        showSearchButton: true,  // 显示搜索按钮
        buttonsClass: 'btn',  //按钮样式
        showRefresh: true,   // 是否显示刷新按钮
        minimumCountColumns: 2, // 最少允许的列数
        clickToSelect: true, // 是否启用点击选中行
        uniqueId: "id",  // 每一行的唯一标识，一般为主键列
        showToggle: true,    // 是否显示详细视图和列表视图的切换按钮
        cardView: false,      // 是否显示详细视图
        detailView: false,  // 是否显示父子表
        queryParams: function(params) { // 传参
            return {
                offset: params.offset,
                limit: params.limit
            }
        },
        // 表头
        columns: columns,
        onLoadSuccess: function (res) {// 加载成功时
            return res;
        },
        onLoadError: function (statusCode) {
            return "获取数据失败"
        },
        formatLoadingMessage: function () {
            return '<div class="sp sp-circle"></div>';
        },
        formatNoMatches: function () {
            return '暂无记录';
        },
        responseHandler: function(res){
            if (res.status === 200){
                return {
                    "rows": res.data.list,
                    "total": res.data.total
                };
            }else {
                return {};
            }

        },
    });
}