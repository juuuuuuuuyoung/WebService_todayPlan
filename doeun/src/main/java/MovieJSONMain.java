import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MovieJSONMain {

    public MovieJSONMain() throws Exception{

        JSONParser jsonparser = new JSONParser();
        JSONObject jsonobject = (JSONObject)jsonparser.parse(readUrl());
        JSONObject json =  (JSONObject) jsonobject.get("boxOfficeResult");
        JSONArray array = (JSONArray)json.get("dailyBoxOfficeList");

        for(int i = 0 ; i < array.size(); i++){

            JSONObject entity = (JSONObject)array.get(i);
            String rnum = (String) entity.get("rnum");
            String movieNm = (String) entity.get("movieNm");
            String openDt = (String) entity.get("openDt");

            System.out.print(rnum + ":");
            System.out.print(movieNm + " ");
            System.out.println(openDt);
        }


    }
    // 사용자 입력을 받기 위한 메서드
    // 메시지를 출력하고 사용자 입력을 받습니다.
    private static String getUserInput(String message) {
        Scanner sc = new Scanner(System.in);

        System.out.print(message);
        String input = sc.nextLine();

        return input;
    }
    private static String readUrl() throws Exception {
        // 사용자 입력을 받는 메서드를 호출해서 targetDt 값을 받아옴
        String targetDate = getUserInput("조회할 날짜를 입력해주세요(format : yyyymmdd) : ");
        BufferedInputStream reader = null;
        try {
            URL url = new URL(
                    "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
                            + "searchDailyBoxOfficeList.json" // JSON 형식
                            + "?key=e02513baa82320de545208781377a3c1" // 인증키
                            + "&targetDt="); //조회날짜
            reader = new BufferedInputStream(url.openStream());
            StringBuffer buffer = new StringBuffer();
            int i;
            byte[] b = new byte[4096];
            while( (i = reader.read(b)) != -1){
                buffer.append(new String(b, 0, i));
            }
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            new MovieJSONMain();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}