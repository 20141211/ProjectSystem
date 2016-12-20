function editfun(edit,names,action){
	var td=edit.parentNode;
	var tr=td.parentNode;
	//添加一个form表单，提交form表单
	var form=document.createElement("form");
	edit.style.visibility="collapse";
	var submit=document.createElement("input");
	submit.type="submit";
	submit.value="确认修改";
	form.appendChild(submit);
	form.action=action;
	form.method="post";
	var i;
	for(i=0;i<tr.cells.length-1;i++){
		var input=document.createElement("input");
		input.type="text";
		input.value=tr.cells[i].innerHTML;
		input.id=i;
		input.name=names[i];
		input.style.width="100%";
		//alert(input.style.width)
		if(i!=0){
			tr.cells[i].innerHTML="";
			tr.cells[i].appendChild(input);
		}
		var inputClone=input.cloneNode(true);
		inputClone.type="hidden";
		
		//根据是否隐藏了input文本框为标志，来决定是否赋值
		input.onblur=function(){
			var inputs=document.getElementsByName(this.name);
			for(var i=0;i<inputs.length;i++){
				if(inputs[i].type=="hidden"){
					inputs[i].value=this.value;
				}
			}
			
		};
		form.appendChild(inputClone);
		
	}
	td.innerHTML="";
	td.appendChild(form);  			
}