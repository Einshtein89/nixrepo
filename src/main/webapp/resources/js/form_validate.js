$(document).ready(function(){

    $("#registerform").validate({

        rules:{
            login:{
                minlength: 5,
                maxlength: 15,
                pattern:'^[A-Za-z0-9_-]+$',
            },

           password:{
                minlength: 5,
                maxlength: 15,
                pattern:'^[A-Za-z0-9_-]+$',
            },

           email:{
               email:true,
               pattern:'\\w+\\@\\w+\\.\\w{2,3}',
           },
           birthday:{
               pattern:'\\d{0,4}\\-\\d{0,2}\\-\\d{0,2}',
           },

            firstName:{
                pattern:'^[a-zA-Z]+$',
            },

            lastName:{
                pattern:'^[a-zA-Z]+$',
            },

       },
       
       messages:{
           firstName:{
               pattern:'must be only a-zA-Z',
           },
           lastName:{
               pattern:'must be only a-zA-Z',
           },

           birthday:{
               pattern:"Date format is YYYY-MM-DD",
           },
       }
        
    });

}); //end of ready