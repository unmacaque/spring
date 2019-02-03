window.onload = function () {
  var client;

  var connectCallback = function (frame) {
    $('#chat-content').append(
      $('<div>')
        .append($('<span>')
        .text('Connected')));
    updateButtonState(true);
    console.log(frame);
    client.subscribe('/topic/chat', onMessageReceive);
  };

  var errorCallback = function (error) {
    console.error(error);
    $('#chat-content').append(
    $('<div>')
      .addClass('chat-status')
      .append($('<span>')
      .text('Connection lost')));
    updateButtonState(false);
  };

  var onMessageReceive = function (json) {
      var message = JSON.parse(json.body);
      var formattedDate = new Date(message.postedOn).toLocaleString();
      $('#chat-content').append(
        $('<div>')
          .addClass('chat-message')
          .append($('<span>').css('font-weight', 'bold').text(message.author))
          .append($('<span>').css('text-decoration', 'italic').text(formattedDate))
          .append($('<span>').text(message.text))
      );
  };

  var updateButtonState = function (connected) {
    if (connected) {
      $('#connect').attr('disabled', 'disabled');
      $('#disconnect, #post').removeAttr('disabled');
    } else {
      $('#connect').removeAttr('disabled');
      $('#disconnect, #post').attr('disabled', 'disabled');
    }
  };

  $('#connect').click(function (e) {
    client = webstomp.client('ws://localhost:8080/socket-endpoint');
    client.connect({}, connectCallback, errorCallback);
  });

  $('#disconnect').click(function (e) {
    if (client !== null) {
      $('#chat-content').append(
        $('<div>')
          .addClass('chat-status')
          .append($('<span>')
          .text('Disconnected')));
      updateButtonState(false);
    }
  });

  $('#post').click(function (e) {
    var name = $('#name').val();
    var text = $('#message').val();
    if (name.length === 0 || text.length === 0) {
      return;
    }
    var message = { 'author': name, 'postedOn': new Date(), 'text': text };
    client.send('/chat/post', JSON.stringify(message));
    $('#message').val('');
  });
}
