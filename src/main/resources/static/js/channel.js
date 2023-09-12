const pathParts = window.location.pathname.split('/');
const channelId = pathParts[2];  // Given the structure, the channelId will be the third part

function checkForEnter(event) {
    if (event.keyCode === 13) { // Check for the 'Enter' key
        sendMessage();
        event.preventDefault(); // Prevent default behavior like creating a newline in the textarea
    }
}

function sendMessage() {
    const messageContent = document.getElementById('messageInput').value;
    const username = sessionStorage.getItem('username');

    fetch(`/channels/${channelId}/sendMessage`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ content: messageContent, sender: username })
    }).then(response => {
        if (response.ok) {
            // Message sent successfully, clear the textarea
            document.getElementById('messageInput').value = '';

            // Refresh the message area to show the newly sent message
            refreshMessageArea();
        } else {
            console.error('Failed to send message.');
        }
    }).catch(error => {
        console.error('Error:', error);
    });
}


function refreshMessageArea() {
    // Use 'fetch' to retrieve the latest messages from the server
    fetch(`/channels/${channelId}/latestMessages?_=${new Date().getTime()}`)
        .then(response => response.json())
        .then(messages => {
            console.log("Received messages:", messages);

            // Update the message area with the new messages
            const messageArea = document.getElementById('messageArea');
            messageArea.innerHTML = '';

            messages.forEach(message => {
                const messageElement = document.createElement('div');
                const senderSpan = document.createElement('span');
                senderSpan.textContent = message.user.username + ': ';
                const contentSpan = document.createElement('span');
                contentSpan.textContent = message.content;

                messageElement.appendChild(senderSpan);
                messageElement.appendChild(contentSpan);
                messageArea.appendChild(messageElement);
            });

        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// Call refreshMessageArea initially to load existing messages
refreshMessageArea();

// Polling: Call refreshMessageArea at regular intervals to check for new messages
setInterval(refreshMessageArea, 5000); // Refresh every 5 seconds (adjust as needed)
