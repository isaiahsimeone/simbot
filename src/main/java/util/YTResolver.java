package util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Chubby little class just to get a youtube link from a potential video name
public class YTResolver {

    public static boolean isLinkFormatted(String target) {
        return target.contains("youtube.com/watch?v") || target.contains("youtu.be");
    }

    public static String resolveIfRequired(String songID) {
        // If it's just a song name, then resolve it to a video id
        if (!YTResolver.isLinkFormatted(songID))
            songID = YTResolver.getIdentifierFromName(songID);

        return songID;
    }

    // Given some potential youtube video name, find the first video
    // in the result list and return a youtube link to it
    public static String getIdentifierFromName(String query) {
        String targetVidID;
        try {
            query = query.replace(" ", "+"); // proper format for URL
            Document doc = Jsoup.connect("https://www.youtube.com/results?search_query=" + query)
                    .userAgent("Mozilla")
                    .get();

            String pageSrc = doc.outerHtml();
            Pattern videoIDPattern = Pattern.compile("/watch\\?v=\\S{11}");
            Matcher m = videoIDPattern.matcher(pageSrc);

            if (m.find()) {
                targetVidID = m.group(0);
            } else {
                targetVidID = "failure";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }

        return "https://www.youtube.com" + targetVidID;
    }

}
