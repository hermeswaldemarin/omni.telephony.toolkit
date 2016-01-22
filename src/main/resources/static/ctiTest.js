$(document).ready(function(){


    $.when(
        $.getScript( "http://localhost:8080/stomp.js" ),
        $.getScript( "http://localhost:8080/sockjs-1.0.3.js" ),
        $.getScript( "http://localhost:8080/OmniCTIAdapter.js" ),
        $.Deferred(function( deferred ){
            $( deferred.resolve );
        })
    ).done(function(){
        alert('carregou!!!');
        //Instantiate adapter
        adapter = new OmniCTIAdapter({
            url: "http://localhost:8080",
            eventCallbackPath: "/messages/cti/eventcallback",
            logPath: "/messages/cti/log",
            sendEventsPath: "/gateway/cti/command/"
        });


        function sendTestData(action){
            adapter[action]({
                arguments : {
                    arg1 : "teste",
                    arg2 : "teste2"
                }
            })
        }


        //apply binds
        $('.login').click(function(){sendTestData("login")});
        $('.logout').click(function(){sendTestData("logout")});
        $('.xxx').click(function(){sendTestData("xxx")});
        $('.becomeAvailable').click(function(){sendTestData("becomeAvailable")});
        $('.becomeUnavailable').click(function(){sendTestData("becomeUnavailable")});
        $('.answer').click(function(){sendTestData("answer")});
        $('.dropCall').click(function(){sendTestData("dropCall")});
        $('.connect').click(function(){adapter.connect();});



        adapter.on("OmniCTIConnect", function(){
            $('#mensagem').text('Conectado!');
        });

        adapter.on("answer", function(){
            $('#mensagem').text('Chamada em curso');
            $('.answer').attr('disabled','disabled');
            $('.dropCall').removeAttr('disabled');
        });

        adapter.on("ring", function(){
            console.log(this.eventName)
            console.log(this.returnCode)
            console.log(this.returnMessage)
            console.log(this.arguments)
            $('#mensagem').text('Está tocando!!!');
            $('.answer').removeAttr('disabled');
        });
        adapter.on("answer", function(){
            $('#mensagem').text('Chamada em curso');
            $('.answer').attr('disabled','disabled');
            $('.dropCall').removeAttr('disabled');
        });
        adapter.on("dropCall",function(){
            $('#mensagem').text('Aguardando chamada');
            $('.answer').attr('disabled','disabled');
            $('.dropCall').attr('disabled','disabled');
        })

        adapter.on("log",function(){
            console.log(this.level);
            console.log(this.logger);
            console.log(this.message);
            $('#error').text(this.level+" :: "+this.message);
        })


    });




});