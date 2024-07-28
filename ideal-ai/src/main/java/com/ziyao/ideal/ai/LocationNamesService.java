package com.ziyao.ideal.ai;

import java.util.function.Function;

/**
 * @author ziyao zhang
 */
public class LocationNamesService
        implements Function<LocationNamesService.Request, LocationNamesService.Response> {


    @Override
    public Response apply(Request request) {

        String name =  request.name();
        String location =  request.location();
        if (location == null || name == null) {
            return new Response("参数确实，无需function-call，正常响应即可..");
        }
        /*LiveData liveData = RestClient.builder()
                .baseUrl("https://api.themeparks.wiki/v1/entity")
                .build()
                .get()
                .uri("/{entityId}/live", entityId)
                .retrieve()
                .body(LiveData.class);
        String waitTime = liveData.liveData().get(0).queue().STANDBY().waitTime();
        waitTime = waitTime != null ? waitTime : "unknown";*/
        System.out.println(location);
        return new Response(location+"有10个！");
    }

    public record Request(
            String name,
            String location) {}
    public record Response(String message) {}


}