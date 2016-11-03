var main = function() {

 $(".btn-slide").click(function(){
	$(".container_1").slideToggle("slow");
		$('.btn-slide').toggleClass("active"); return false;
	});

}

$(document).ready(main);

