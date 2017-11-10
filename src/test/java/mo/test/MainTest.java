package mo.test;

public class MainTest {

	public static void main(String[] args) {
		String url = "http://webmagic.io/favicon.ico/";
		String[] ls = url.split("/");
		System.out.println(ls[3]);
	}
}
