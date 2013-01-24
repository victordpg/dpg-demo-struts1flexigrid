var GCIB = new Object();

$jQuery().ready(function() {
	//bind search button to FLEXIGRID.
	$jQuery("#queryBtn").bind("click",GCIB.getList);
});

GCIB.getList = function(){
	//search conditions parameters.
	var params = [	{"name" : "idPara", "value" : $jQuery("#idPara").val()},
	              			{"name" : "namePara", "value" : $jQuery("#namePara").val()} ];        
	$jQuery('#gcibTable').flexOptions({params : params, newp : 1}).flexReload();
	
	$("#gcibTable").flexigrid(
	        {
	            url: webpath+'/SearchAction.do?method=getList',
	            dataType: 'json',
	            colModel : [
	                {display: '编号', name : 'id', width: 100, sortable: true, align: 'center'},        
	                {display: '文件名称', name : 'name', width: 200, sortable: true, align: 'center'},
	                {display: '文件类型', name : 'filetype', width: 200, sortable: true, align: 'center'}, 
	                {display: '操作', name : 'url', width: 100, sortable: false, align: 'center'}
	                ],    
	            checkbox : false,
	            sortname: "id",
	            sortorder: "asc",
	            usepager: true,
	            title: 'Results.',
	            useRp: true,
	            rp: 10,            
	            showTableToggleBtn: true,
	            height:400,
	            width:800
	            }
	        );
};

//show the file which from database.	
GCIB.showObject= function(picId){
	var url = webpath + '/SearchAction.do?method=showPDFandIMG&fileID=' + picId;
    url=encodeURI(url); 
    //注释掉下面两行模态窗口代码，因为IE无法下载或显示流文件（其他浏览器ModalDialog可以）
    //var openStyle = "dialogHeight:600px; dialogWidth:800px; status:no; help:no; scroll:auto";
    //window.showModalDialog(url, window.document, openStyle);	
    var name = 'ShowPDF'; //open的网页名称，可为空; 但是不能有特殊字符，甚至连空格都不能有。否则会包参数无效JS错误。
    var iWidth = 800; ;
    var iHeight = 600; 
    var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
    var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
    var properties = "height="+iHeight+",width="+iWidth+",top="+iTop+",left="+iLeft+",toolbar=no,menubar=no,scrollbars=auto,resizable=no,location=no,status=no";
    window.open(url, name, properties);
};