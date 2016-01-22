/**
 * Created by Philipe on 21/12/2015.
 */
(function(){
    'use strict'

    function EventHandler() {
        var events = {};

        function off( eventName ){
            if( !eventName  ) {
                console.log('Erro');
                return;
            }

            delete events[eventName];
        }

        function on( eventName, action ){
            if( !eventName || !action || typeof action != 'function' ) {
                console.log('Erro');
                return;
            }

            if ( typeof events[eventName] === 'object' ){
                events[eventName].push(action);
            } else {
                events[eventName] = [action];
            }
        }

        function trigger( eventName, scope ) {
            if( !eventName || typeof events[eventName] != 'object' || !events[eventName].length ) return;
            for( var i = 0, l = events[eventName].length; i < l; i++ ){
                scope ? events[eventName][i].apply(scope) : events[eventName][i]();
            }

        }

        return {
            off: off,
            on: on,
            trigger: trigger
        };
    }

    function OmniCTIAdapter(params) {
        //Check params
        if ( !params ) throw Error( 'Mssing adapter parameters' );
        if ( !params.url || typeof params.url != 'string' ) throw Error( 'Missing connection URL' );

        //Check for dependencies
        if ( !SockJS || !Stomp ) throw Error( 'Missing some dependency' );

        var adapter = this,
            eventHandler = new EventHandler(),
            url = params.url + "/sockjs",
            socket = new SockJS( url ),
            stompClient = Stomp.over(socket),
            EVENT_CALLBACK_PATH = "/messages/cti/eventcallback",
            LOG_PATH = "/messages/cti/log",
            SEND_EVENTS_PATH = "/gateway/cti/command/";

        function connect() {
            stompClient.connect({}, function() {
                eventHandler.trigger("OmniCTIConnect");
                stompClient.subscribe(EVENT_CALLBACK_PATH, function(message) {
                    var parsedMessage = JSON.parse(message.body);
                    //todo: implementar eventos vindos do servidor
                    eventHandler.trigger(parsedMessage.eventName, parsedMessage);
                });
                stompClient.subscribe(LOG_PATH, function(message) {
                    var parsedMessage = JSON.parse(message.body);
                    //todo: implementar eventos vindos do servidor
                    eventHandler.trigger("log", parsedMessage);
                });
            });


        }

        function disconnect(){
            stompClient.disconnect();
        }

        function registerMainAction( actionName ){
            adapter[actionName] = buildDispatchAction(actionName);
        }

        function buildDispatchAction( actionName ){
            return function(obj){
                send(actionName, obj);
            }
        }

        function send( event, object ){
            eventHandler.trigger("OmniCTISend", object);
            stompClient.send(SEND_EVENTS_PATH + event, {destination: SEND_EVENTS_PATH + event}, JSON.stringify(object));
        }

        var mainActions = ["answer", "ready", "notReady", "cancel", "conference", "consult", "dropCall", "hold", "login", "logout", "makeCall", "transfer", "unhold", "log"];

        for(var i = 0, l = mainActions.length; i < l; i++) registerMainAction(mainActions[i]);

        connect();

        this.on = eventHandler.on;
        this.off = eventHandler.off;

        return this;

    }

    //Add the constructor to global
    window.OmniCTIAdapter = OmniCTIAdapter;
}());
