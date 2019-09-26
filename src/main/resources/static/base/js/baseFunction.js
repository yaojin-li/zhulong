
	   var dataTablesFunction = {
			   
			    /**
			     * dataTables数据转换函数,将接受到的后端数组转换为datatables可以识别的数据
			     * 并且对_time后缀的列名识别为日期格式
			     * @para	columNameArray     数据库表列名数组(数据库表名，一般英文) 
			     * @para    dataArray	       数据库返回数组(返回时候请将data.result设置为list)
			     * @Return	data               dataTable可以识别的数组对象
			     * */
	    		dataTransform:function (columNameArray,dataArray){
	    	    	 if(columNameArray&&columNameArray.length>0)
	    	         {
	    	    		 if(dataArray&&dataArray.length>0)
	    	    		 {
	    	       	    	 var data = new Array();
	    	    	    	 for(var i=0;i<dataArray.length;i++){
	    	    	    		 
	    	    	    		 var tempArray = new Array();
	    	    	    		 
	    	    	    		 for(var j=0;j<columNameArray.length;j++){
	    	    	    			 
	    	    	    			 //没有设置数值的，定义为""
	    	    	    			 if(!dataArray[i][columNameArray[j]]){
	    	    	    				 tempArray[j] = "";
	    	    	    				 continue;
	    	    	    			 }
	    	    	    			 
	    	    	    			 //字段名称_time表示时间,现在将日期格式化
	    	    	    			 if(columNameArray[j].match("_time")){
	    	    	    				 var timeString  = dataArray[i][columNameArray[j]];
	    	    	          			 var value      = dataTablesFunction.numberToDateString(timeString);
	    	    	          			 tempArray[j] = value;
	    	    	          			 continue;
	    	    	    			 }
	    	    	    			 
	    	    	    			 //定义数值的，再进行赋值
	    	    	    			 tempArray[j] = dataArray[i][columNameArray[j]];
	    	    	    		 }
	    	    	    		 data[i] = tempArray;
	    	    	    	 }
	    	    	    	 
	    	    			 return data;
	    	    		 }
	    	         }
	    	         console.error("dataTransform请输入两个参数并且这两个参数应当是数组类型,并且数组长度不为0");
	    	         console.info(columNameArray);
	    	         console.info(dataArray);
	    	    },
			    /**
			     * dataTables数据转换函数,datatables表格中的数据转换成为ajax传输的数据
			     * 并且对_time后缀的列名识别为日期格式
			     * @para	columNameArray     数据库表列名数组(数据库表名，一般英文) 
			     * @para    dataArray	       datatables获取的行数据数组
			     * @Return	data               dataTable可以识别的数组对象
			     * */
	    	    transfromToSend: function(columNameArray,dataArray){
	    	    	 if(columNameArray&&columNameArray.length>0)
	    	         {
	    	    		 if(dataArray&&dataArray.length>0)
	    	    		 {
	    	       	    	 var data = new Array();
	    	    	    	 for(var i=0;i<dataArray.length;i++){
	    	    	    		 
	    	    	    		 var tempObject = {};
	    	    	    		 for(var j=0;j<columNameArray.length;j++){
		 
	    	    	    			 //字段名称_time表示时间,现在将字符串日期转换成为日期
	    	    	    			 if(columNameArray[j].match("_time")){
	    	    	    				 var timeStamp = dataArray[i][j];
	    	    	    				 if(typeof(timeStamp) == "string"&&timeStamp!=""){
	    	    	    				     var timeString = timeStamp.split("-");
	    	    	    				     var date      = new Date(0);
	    	    	    				     date.setFullYear(Number(timeString[0]));
	    	    	    				     date.setMonth(Number(timeString[1])-1);
	    	    	    				     date.setDate(Number(timeString[2]));
	    	    	    				     tempObject[columNameArray[j]] = date.getTime();
	    	    	    					 continue;
	    	    	    				 }
	    	    	    			 }
	    	    	    			 //转换成对象
	    	    	    			 tempObject[columNameArray[j]] = dataArray[i][j];
	    	    	    		 }
	    	    	    		 
	    	    	    		 data[i] = tempObject;
	    	    	    	 }
	    	    			 return data;
	    	    		 }
	    	         }
	    	         console.error("transfromToSend请输入两个参数并且这两个参数应当是数组类型");
	    	         console.info(columNameArray);
	    	         console.info(dataArray);
	    	    },
	    		/**
	    		* 将输入的标准UTC毫秒时间转换成为,日期字符串,例如：45234520321转换成为	2017-04-06
	    		* @param  timeString  输入的UTC毫秒时间
	    		* @return value       格式化之后的时间
	    		**/
	    	    numberToDateString:function(timeString){
	    			 var time    = Number(timeString);
	    			 if(typeof time =="number" && time)
	    		     {
	    					var date   = new Date(time);
	    				    var value  = date.getFullYear()+"-";
	    				    if((date.getMonth()+1)<10){
	    				    	value  += "0"+(date.getMonth()+1)+"-";
	    				    }else{
	    				    	value  += (date.getMonth()+1)+"-";
	    				    }
	    				    
	    				    if(date.getDate()<10){
	    				    	value  += "0"+ date.getDate();
	    				    }else{
	    				    	value  +=  date.getDate();
	    				    }
	    				    return value;
	    		     }else{
	    		    	 return "";
	    		     } 
	    		} 
	   }
       //dataTables配置
       var dataTablesSetting = {
    
       	    //汉化
            ChinesesSupport:{
				                "sProcessing":   "处理中...",
				                "sLengthMenu":   "每页显示 _MENU_ 项结果",
				                "sZeroRecords":  "没有匹配结果",
				                "sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				                "sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
				                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
				                "sInfoPostFix":  "",
				                "sSearch":       "搜索:",
				                "sUrl":          "",
				                "sEmptyTable":     "表中数据为空",
				                "sLoadingRecords": "载入中...",
				                "sInfoThousands":  ",",
				                "oPaginate": {
				                    "sFirst":    "首页",
				                    "sPrevious": "上页",
				                    "sNext":     "下页",
				                    "sLast":     "末页"
				                },
				                "oAria": {
				                    "sSortAscending":  ": 以升序排列此列",
				                    "sSortDescending": ": 以降序排列此列"
				                }
            }
       }
	   
		/***
		 *    显示     弹出层
		 *    stauts  传入状态
		 *    message 显示内容
		 *    time    弹出层持续时间，单位毫秒，超时关闭
		 *            默认5秒           
		 * */

	    function showMessage(stauts,message,time){
			var layerSetting = {
					time:5000,
					offset: '40%',
					skin: 'layer-ext-espresso'
		    }
			
			if(time && typeof(Number(time)) == "number" ){
				layerSetting.time = time;
			}
			
			var value = Number(stauts);
			//正确
			if(value>0){
				layerSetting.icon = 1;
			}else if(value==0){
				layerSetting.icon = 0;
			}else if(value<0){
				layerSetting.icon = 2;
			}else{
				layerSetting.icon = 3;
				layer.alert("输入状态号码不是数字，检查showMessage("+stauts+")",layerSetting);
				return;
			}
			layer.alert(message,layerSetting);
		}
       
		//datePicker设置
		var dateTimePickerSetting = {
		  language: 'zh-CN',//显示中文
		  format: 'yyyy-mm-dd',//显示格式
		  minView: "month",//设置只显示到月份
		  initialDate: new Date(),//初始化当前日期
		  autoclose: true,//选中自动关闭
		  todayBtn: true,//显示今日按钮
		  forceParse:true
		 };
		
       //modal设置
		var modalSetting = {
			    show    :false,
			    showShadow:true,
				backdrop:false,    //点击模态框外部关闭
				keyboard:false,    //按下ESC关闭
				remote  :false    //引用其他HTML进行设置
	   };
    
