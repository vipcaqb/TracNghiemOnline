$(document).ready(function(){
    $('#accept').click(function(){
        var count = 0;
		
        var cbList = $(':checkbox');
        for(let i = 0 ;i <cbList.length;i++){
            console.log(cbList[i]['checked'])
            if(cbList[i]['checked']==true){
                count++;
                break;
            }
        }
        if(count==0){
			
            event.preventDefault();
			$('#correctMsg').removeClass('hide');
        }
    })
})