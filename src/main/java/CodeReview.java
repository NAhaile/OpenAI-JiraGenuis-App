import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;

public class CodeReview
{
    public static void main(String[] args)
    {
        String apiKey = "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";
        int apiTimeout = 10;
        /** 
        OpenAiService service = new OpenAiService("your_token");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Somebody once told me the world is gonna roll me")
                .model("ada")
                .echo(true)
                .build();
        */

        List<ChatMessage> chatMessage = new ArrayList<ChatMessage>();
        chatMessage.add(new ChatMessage("system", "SYSTEM_TASK_MESSAGE"));
        chatMessage.add(new ChatMessage("user", String.format("I want to visit and have a budget of dollars")));
        
        OpenAiService openAiService = new OpenAiService(
        apiKey, Duration.ofSeconds(apiTimeout));

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
            .builder()
            .model("gpt-3.5-turbo")
            .temperature(0.8)
            .messages(
                chatMessage)
             .build();

        StringBuilder builder = new StringBuilder();
        openAiService.createChatCompletion(chatCompletionRequest)
            .getChoices().forEach(choice -> { 
            builder.append(choice.getMessage().getContent());
        });
         
         String jsonResponse = builder.toString();

        System.out.println(jsonResponse);
    }
}