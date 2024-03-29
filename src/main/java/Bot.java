import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }

    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if( message != null && message.hasText()){
            switch (message.getText()){
                case "/help":
                    sendMsg(message, "Чем могу помочь");
                    break;
                case "/setting":
                    sendMsg(message, "Что будем настраивать");
                    break;
                default:
            }
        }
    }

    public String getBotUsername() {
        return "terminal77_bot";
    }

    public String getBotToken() {
        return "1045651148:AAGfp6WKBfPT_8oaxccumvsG8TQ8tv0JUKo";
    }
}
