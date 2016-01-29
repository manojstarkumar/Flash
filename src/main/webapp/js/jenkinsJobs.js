/**
 * 
 */

function get_jenkins_success(expectedBuild,shouldCallAgain){
   
    var feedback = $.ajax({
        type: "POST",
        url: "updateJobDetail.mdo",
        data: { buildNumber: expectedBuild},
        async: false
    })
    
    .error(function(jqXHR,textStatus,errorThrown) {
    	$('#jenkinsOutput').append(errorThrown);
    })
    .success(function(){
    	if(shouldCallAgain)
        setTimeout(function(){get_jenkins_status(expectedBuild);}, 5000);
    }).responseText;

    $('#jenkinsOutput').text(feedback);
    $("#jenkinsOutput").animate({ scrollTop: $('#jenkinsOutput').prop("scrollHeight")}, 1000);
}

function get_jenkins_status(expectedBuild){
	
	//alert(expectedBuild);
	   
    var feedback = $.ajax({
        type: "POST",
        url: "getBuildStatus.mdo",
        data: { buildNumber: expectedBuild},
        async: false
    }).responseText;
    
    	//alert(feedback);
	if(feedback=="false") {
		$("#loader").removeClass("hidden");
		$('#status').addClass("hidden");			
		$('#testStatus').text("RUNNING");	
		setTimeout(function(){get_jenkins_success(expectedBuild,true);}, 1000);
	}
	else if(feedback=="queued") {
		$("#loader").addClass("hidden");
		$('#status').removeClass("hidden");	
		$('#jenkinsOutput').text("Test is queued. Status will be updated here once the test starts");
		$('#status').html("<h4>Test is queued. Please wait while for your test to start</h4>");
		$('#testStatus').text("QUEUED");
        setTimeout(function(){get_jenkins_status(expectedBuild);}, 5000);  
	}
	else {
		$("#loader").addClass("hidden");
		$('#status').removeClass("hidden");	
		$('#status').html("<h4>Test Complete.</h4>");
		$('#testStatus').text(feedback);
		$('#reportLink').html("<h4><a href='testResult.mdo?testId="+expectedBuild+"' target='_blank'>Reports Link</a></h4>");
		if(feedback=='SUCCESS')
		$('#reportLink').removeClass('hidden');
	setTimeout(function(){get_jenkins_success(expectedBuild,false);}, 10000); //wait for 10 seconds and do one final call
	}
    //$('#jenkinsOutput').text(feedback);
}