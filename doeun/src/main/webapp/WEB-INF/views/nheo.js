$(function(){
    $('#btnnheo').click(function(){
        $.ajax({
            //url: 'https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyA6Ru1bMnTyQ1hesbxnucSjmP4Im30RE',
            url: 'https://maps.googleapis.com/maps/api/geocode/json',
            data: $('.form-nheo').serialize(),
            type: 'GET',
            success: function(response){
                console.log(response);
                procMapControl(response);
            },
            error: function(error){
                console.log(error);
            }
        });
    });
});
