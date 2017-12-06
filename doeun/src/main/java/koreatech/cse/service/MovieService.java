package koreatech.cse.service;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Scanner;

import koreatech.cse.domain.rest.Movie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    // 사용자 입력을 받기 위한 메서드
    // 메시지를 출력하고 사용자 입력을 받습니다.
    private static String getUserInput(String message) {
        Scanner sc = new Scanner(System.in);

        System.out.print(message);
        String input = sc.nextLine();

        return input;
    }
   public void readUrl(Movie movie){

        BufferedInputStream reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(
                    "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"
                            + "searchDailyBoxOfficeList.json" // JSON 형식
                            + "?key=e02513baa82320de545208781377a3c1" // 인증키
                            + "&targetDt="+movie.getTargetDt()); //조회날짜
            reader = new BufferedInputStream(url.openStream());
            //StringBuffer buffer = new StringBuffer();
            int i;
            byte[] b = new byte[4096];
            while( (i = reader.read(b)) != -1){
                buffer.append(new String(b, 0, i));
            }
            if (reader != null)
                reader.close();
            //return buffer.toString();
        } catch (Exception e){}

        JSONParser jsonparser = new JSONParser();
        JSONObject jsonobject = null;
        try {
            jsonobject = (JSONObject) jsonparser.parse(buffer.toString());
        }catch (Exception e){}
        JSONObject json =  (JSONObject) jsonobject.get("boxOfficeResult");
        JSONArray array = (JSONArray)json.get("dailyBoxOfficeList");

        for(int i = 0 ; i < array.size(); i++){

            JSONObject entity = (JSONObject)array.get(i);
            String rnum = (String) entity.get("rnum");
            String movieNm = (String) entity.get("movieNm");
            String openDt = (String) entity.get("openDt");

            movie.setRnum(rnum);
            movie.setMovieNm(movieNm);
            movie.setOpenDt(openDt);

            System.out.print(rnum + ":");
            System.out.print(movieNm + " ");
            System.out.println(openDt);
        }

    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            new MovieService();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}