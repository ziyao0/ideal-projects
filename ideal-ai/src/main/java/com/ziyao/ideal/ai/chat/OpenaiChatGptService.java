package com.ziyao.ideal.ai.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

/**
 * @author ziyao zhang
 */
@Service
@RequiredArgsConstructor
public class OpenaiChatGptService implements ChatService {

    private final OpenAiChatModel openAiChatModel;

    @Override
    public Object chat(String problem) {

        ChatResponse response = openAiChatModel.call(new Prompt(problem,
                OpenAiChatOptions.builder()
                        .withModel(OpenAiApi.ChatModel.GPT_3_5_TURBO.getValue())
                        .build()));

        return  response.getResult().getOutput().getContent();
    }
}
