package cueare;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class BitlyManager {
	static String token = "1ebd07ace94b2f80f8ad66273221c7e89b76997f";

	public static String getURL(String full) {

		try {
			HttpPost post = new HttpPost("https://api-ssl.bitly.com/v4/shorten");

			String payload = "{\"long_url\":\"" + full + "\"}";
			payload.replace(" ", "");

			StringEntity entity = new StringEntity(payload);

			post.setHeader("Authorization", "Bearer " + token);
			post.setHeader("Content-Type", "application/json");
			post.setHeader("Accept", "application/json");

			post.setEntity(entity);

			try (CloseableHttpClient httpClient = HttpClients.createDefault();
					CloseableHttpResponse response = httpClient.execute(post)) {

				String unformat = EntityUtils.toString(response.getEntity());
				JSONObject jsonArray = new JSONObject(unformat);
				unformat = jsonArray.get("link").toString();
				unformat = unformat.replace("https://bit.ly/", "");
				return unformat;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
