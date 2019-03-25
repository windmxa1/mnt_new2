var i = 0;
window.isApp = true;
exports.callback0 = function () {
    console.log(i + ": " + window.location);
    window.alert ("i = " + i);
    i = i + 1;
}
window.appConfig = function(){
	this.serverUrl = "";
	this.userNameInfo = {};
	this.lastRouterid = "dashboard";
	
	var reqPath = require('path');
	var nwPath = process.execPath;
	
	//nw.exe运行地址
	var nwDir = reqPath.dirname(nwPath);
	
	//应用地址
	//process.cwd()
	//数据存储地址  
	//var dataPath = require('nw.gui').App.dataPath;
	
	this.fileName = nwDir + "\init.config";
	var fs = require("fs");
	
	var defaultCfgData = {
		serverUrl:"",
		userNameInfo:{
			name:'',
			account:'',
			pwd:''
		},
		lastRouterid:"dashboard"
	};

	fs.exists(this.fileName, function(exists) {
		if(exists){
			this.cfg = this.readCfg(this.fileName);
		}else{
			this.cfg = defaultCfgData;
			fs.writeFile(this.fileName, JSON.stringify(defaultCfgData), function(err) {  
				console.log("文本创建成功:"+JSON.stringify(defaultCfgData));  
			});
		}
	});
}
window.appConfig.prototype.readCfg = function(fileName){
	var fs = require("fs");
	var rdata = null;
	fs.readFile(fileName function(err, data) {  
		console.log("读取文本内容:" + data.toString());
		rdata = JSON.parse(data.toString());
	});
	return rdata;
}
window.appConfig.prototype.save = function(){
	
	//fs.appendFile(nwDir5 + "/" + configFileName, "{serverUrl:'www.sohu.com'}", function(err) {  
	//	console.log("文本追加成功");  
	//}); 
}