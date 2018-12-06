function validate($obj){
	var id = $obj.attr("id");
	var flag = true;
	switch(id){
		case "userName":
			var username = $obj.val();
			var $userNameId = $("#userNameId");
			var regCode = /^[a-zA-Z0-9][0-9a-zA-Z_\.-]{2,16}[0-9a-zA-Z]$/;
			if(username == ""){
				alert("用户名不为空");
				flag = false;
			}
			else if (regCode.test(username) == false) {
				alert("用户名格式错误");
            	flag = false;
        	}else{
        		flag = true;
			}
		break;
		//楠��瀵��
		case "pwd":
			var password = $obj.val();
			var $pwdId = $("#pwdId");
			//var regCode = /^[a-zA-Z0-9][0-9a-zA-Z_\.-]{2,16}[0-9a-zA-Z]$/
			if(password == ""){
				alert("密码不能为空");
				flag = false;
			}
			else if(password.length<6||password.length>16){
				alert("密码格式错误");
				flag = false;
			}
			/*
			else if (regCode.test(password) == false) {
            	$pwdId.removeClass().addClass("error_prompt").html("涓��纭��璇烽��拌���);
            	flag = false;
        	}
			*/else{
				flag = true;
			}
		break;
		
		case "repwd":
			var repwd = $obj.val();
			var pwd = $("#pwd").val();
			var $repwdId = $("#repwdId");
			if(password == ""){
				alert("请先填写密码");
				flag = false;
			}
            else if(pwd ==""){
				alert("二次密码不能为空");
                flag = false;
            }
			else if(repwd==pwd&&pwd!=""){
				alert("两次密码不一致");
				flag = false;
			}
			else{
				flag = true;
			}
		break;
		
		case "form1":
			var flag=true;
			var checks=document.getElementsByName("c");
			n=0;
			for(i=0;i<checks.length;i++){
			if(checks[i].checked){n++;}
			}
			 //如果n=0，说明一个都没选
			if (n==0){
			     alert("请至少选一个兴趣爱好！");
			     flag=false;
			}
			return flag;
		break;
		
       
		
		default: 			
	}
	return flag;
}


$(function($){
    //��氦琛ㄥ�
	$("#registerForm").submit(function(){
		var flag = true;
		$(this).find("input[id]").each(function() {
            if(!validate($(this))){
			flag:false;
			}
        });		
		return flag; 
	})
    //缁���ㄦ����浠�
	$("#userName").blur(function(){
		validate($(this));
	}).focus(function(){
		$("#userNameId").html("正确");
	});
    //缁��瀵��浜�欢
	$("#pwd").blur(function(){
		validate($(this));
	}).focus(function(){
		$("#pwdId").html("正确");
	});
    //���瀵��澶卞����浜�欢
	$("#repwd").blur(function(){
		validate($(this));
	}).focus(function(){
		$("#repwdId").html("正确");
	});
	
  
});