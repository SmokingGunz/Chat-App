function checkForEnter(event) {
    if (event.keyCode === 13) { // Check for the 'Enter' key
        sendMessage();
        event.preventDefault(); // Prevent default behavior like creating a newline in the textarea
    }
}

function sendMessage() {
    const messageContent = document.getElementById('messageInput').value;

    // Now, you can use 'fetch' to send this message to the server to be stored.
    // After sending the message, clear the textarea
    // document.getElementById('messageInput').value = '';

    // Additionally, you can implement the logic to refresh the messageArea to show the newly sent message.
}
