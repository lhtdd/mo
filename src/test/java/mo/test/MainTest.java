package mo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyao.mo.bottom.bean.to.SmsResponse;

import java.io.IOException;

public class MainTest {

	public static void main(String[] args) {
		String jsonStr = "{\"Message\":\"asdf\",\"Code\":\"OK\",\"RequestId\":\"df\",\"BizId\":\"ddd\"}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			SmsResponse smsResponse = mapper.readValue(jsonStr, SmsResponse.class);
			System.out.println(smsResponse.getCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
