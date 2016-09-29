(function(exports){

    var errorField;

    var STEPS;

    $(function(){
        $('body').append('<div id="game_field"></div><div id="error_field"></div>');
        errorField = $('#error_field');
        $.ajax({
            url: '/resources/template/game_field.html',
            async: false,
            success: function(response){
                $('#game_field').html(response);
            }
        });
    });

    exports.play = function(game_json){
        var json;
        try {
            json = JSON.parse(game_json);
            errorField.empty();
        } catch (e){
            errorField.html(e.toString());
            return;
        }
        STEPS = test();
        step();
    };

    function test(){
        return [
            {from: '#A3', to: '#B4'},
            {from: '#H6', to: '#G5'},
            {from: '#B4', to: '#C5'}
        ];
    }

    function step(){
        var _next = STEPS.shift();
        if(!_next)
            return;

        move(_next.from, _next.to);
    }

    function move(from_id, to_id){
        var f = $(from_id);
        var to = $(to_id).offset();
        var from = f.offset();

        var img = $(f.html());
        img.css('left', from.left).css('top', from.top).css('position', 'fixed').prop('id', 'fly').css('width', '70px').css('height', '70px');

        $('body').append(img);
        f.empty();

        $('#fly').animate({
            top: to.top+'px',
            left: to.left+'px'
        }, {
            duration: 500,
            complete: function() {
                $(to_id).html($(this).attr('id', '').css('position', ''));
                step();
            }
        });
    }

})(window);