class WebSocketClient {
	
	constructor() {
		let socket = new SockJS('/websockets');
		
		this.topicSubscribed = [];
		this.stompClient = Stomp.over(socket);
		
		// Disable logging
	    this.stompClient.debug = null;

		// Enable auto reconnect
		this.stompClient.reconnect_delay = 1000;
		
		// Connect stompClient
		this.connect();
	}
	
	connect = () => {
		this.stompClient.connect({}, this.connectCallback);
	}
	
	// Function called after websocket connected
	connectCallback = () => {
		console.log("WebSocket connected");
		
		this.topicSubscribed.forEach((topic) => {
			this.subscribe(topic.topicName, topic.onMessageCallback);
		});
	}
	
	// Subscribe to the given topic
	subscribe = (topicName, onMessageCallback) => {
		
		// If websocket isn't connected yet
		if(!this.stompClient.connected) {
			// Delay subscription from 100ms
			setTimeout(() => {
				this.subscribe(topicName, onMessageCallback);
			}, 100);
			
		} else {
			// Perform subscription
			let subscription = this.stompClient.subscribe("/topic/" + topicName, onMessageCallback);

			// Save subscribed topic
			this.topicSubscribed.push({"topicName": topicName, "onMessageCallback": onMessageCallback, "subscription": subscription});
			
			console.log("WebSocket subscribed to topic " + topicName);
		}
	}
	
	// Send a message to the given topic
	sendMessage = (topicName, message) => {
		this.stompClient.send("/message/" + topicName, {}, message);
			
		console.log("Message send to topic " + topicName);
	}
	
	// Function to disconnect the websocket
	disconnect = (topicName) => {
	    if (this.stompClient !== null) {
			if(topicName == undefined) {
				this.stompClient.disconnect();

			    console.log("WebSocket disconnected");
			} else {
				let topic = this.topicSubscribed
									.filter(t => t.topicName == topicName)
									.pop();
				if(topic == undefined) {
					console.log("No topic found with name " + topicName);
				} else {
					topic.subscription.unsubscribe();

					console.log("Disconnected from topic " + topicName);
				}
			}
	    }
	}
}