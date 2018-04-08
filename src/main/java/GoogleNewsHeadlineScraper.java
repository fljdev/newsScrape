
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class GoogleNewsHeadlineScraper {


    /**
     * Website URL & Class Name of headline header.
     * These can very simply be swapped out for alternative sites and sections
     */
    private final static String URL = "https://news.google.com/news/?ned=en_ie&gl=IE&hl=en-IE";
    private final static String CLASS_NAME = "nuEeue hzdq5d ME7ew";

    /**
     * Map will hold the title and the url as a key value pair
     */
    private static Map<String, String> map = new HashMap<String, String>();


    /**
     * To randomly access a news story I decided to store a reference to each article in an index based Collection
     */
    private static ArrayList<String> linkKeys = new ArrayList<String>();


    /**
     * This method uses JSoup to connect to and parse the page
     * @throws Exception
     */

    private static void loadLinks()throws Exception {

        Document doc = Jsoup.connect(URL).userAgent("Mozilla").get();

        Elements headlineLinks = doc.getElementsByClass(CLASS_NAME);

        for (Element e: headlineLinks) {

            addLinksToArrayList(e.text());
            addLinksAndHREFToMap(e.text(),e.attr("href"));
        }
    }

    /**
     * link Keys added to ArrayList, which is index based and allows for random access
     */
    private static void addLinksToArrayList(String link){
        linkKeys.add(link);
    }

    /**
     * @param link
     * link Keys added to HashMap
     * @param href
     * link Value added to Hashmap
     */
    private static void addLinksAndHREFToMap(String link,String href){
        map.put(link,href);
    }




    /**
     *This method will generate a random int in the range from 1 to size of the HashMap
     */
    private static int generateRandomNumber(int min, int max){

        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private static int chooseRandomLink(){
        return generateRandomNumber(1,map.size());
    }





    private static void init()throws Exception {
        loadLinks();

        int linkPosition = chooseRandomLink();
        System.out.println(linkKeys.size()+" news headlines");
        System.out.println("Headline no. "+linkPosition+ " randomly selected");

        String randomlySelectedKey = linkKeys.get(linkPosition);
        String value = map.get(randomlySelectedKey);

        System.out.println(randomlySelectedKey);
        System.out.println(value);
    }


    public static void main(String[] args) throws Exception {
        init();
    }

}
