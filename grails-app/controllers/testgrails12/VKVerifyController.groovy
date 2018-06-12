package testgrails12

import com.vk.api.sdk.client.TransportClient
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.exceptions.ApiException
import com.vk.api.sdk.exceptions.ClientException
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.GroupAuthResponse

class VKVerifyController {

    def index(String code) {
        Random random = new Random();
        int APP_ID = 6474972;
        String CLIENT_SECRET = "PNkYiS79LdyoYMeGGBAV";
        String REDIRECT_URI = "localhost:8080/VKVerify";
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        int groupId = 159158661;
        GroupAuthResponse authResponse = null;

        try {
            authResponse = vk.oauth()
                    .groupAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                    .execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        String access_key = authResponse.getAccessTokens().get(groupId)
        if(access_key != null){
            VKBotDataService.instance.access_string = access_key
            render "ok"
        }else {
            render "error"
        }

        /*GroupActor actor = new GroupActor(groupId, access_key);
        try {
            vk.messages().send(actor).message("TEST 112").userId(107287512).randomId(random.nextInt()).execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println("end!" + access_key);
        render code*/

    }
}
