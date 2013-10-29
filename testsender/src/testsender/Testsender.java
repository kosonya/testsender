/**
 * 
 */
package testsender;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author maxikov
 *
 */
public class Testsender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Hello");
		testpost();
	}
	
	public static void testget() {
		System.out.println("One");
		HttpClientBuilder builder = HttpClientBuilder.create();
		System.out.println("Two");
		HttpClient client = builder.build();

		System.out.println("Three");
		HttpGet method = new HttpGet("http://cmu.edu/");


		System.out.println("four");
		try {

			HttpResponse response = client.execute(method);
			System.out.println(response.toString());
			System.out.println(response.getStatusLine().getReasonPhrase());
			System.out.println("five");
			HttpEntity entity = response.getEntity();
			System.out.println("six");
			InputStream istream = entity.getContent();
			System.out.println("seven");
			BufferedReader rd = new BufferedReader(new InputStreamReader(istream));
			String res = "", line;
			while( (line = rd.readLine()) != null) {
				
				res += line + "\n";

			}
			istream.close();
			System.out.println(res);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		
	}
	
	public static void testpost() {
		String json_str = "{\"id\": \"mob1fake\", \"timestamp\": 1000, \"temp\": 123}";
		System.out.println("sending: " + json_str);
		String server_uri = "http://einstein.sv.cmu.edu/sensors";

		HttpClientBuilder builder = HttpClientBuilder.create();
		System.out.println("builder created");
		HttpClient client = builder.build();
		System.out.println("client built");

		HttpPost postMethod = new HttpPost(server_uri);
		postMethod.addHeader("content-type", "application/json");
		System.out.println("httpost created");

		try {
			postMethod.setEntity(new StringEntity(json_str, "UTF-8"));
			System.out.println("entity set");

			HttpResponse response = client.execute(postMethod);
			System.out.println(response.toString());
			System.out.println(response.getStatusLine().getReasonPhrase());
			HttpEntity entity = response.getEntity();
			InputStream istream = entity.getContent();
			BufferedReader rd = new BufferedReader(new InputStreamReader(istream));
			String res = "", line;
			while( (line = rd.readLine()) != null) {
				
				res += line + "\n";

			}
			istream.close();
			System.out.println(res);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
