import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Lucas Sampaio Dias on 26/09/2018.
 */

public class BlizzApiHelper {

    private static final String API_KEY = "awydkuy9tx3zksd7kkh2g5xchnwv4qy3";
    private static final String BASE_URL = "https://us.api.battle.net/wow/";

    /**
     * Gets all the recent achievements of the specified guild in a JSON format (inside of a String)
     * @param server The game server the guild is located. Example: "goldrinn", "sargeras".
     * @param guild The name of the guild.
     * @param locale The language of the response.
     * @return
     * @throws IOException
     */
    public static String getGuildAchievements(String server, String guild, ApiLocale locale)
            throws IOException {
        URL url = new URL(getGuildApiLink("achievements", server, guild, locale));
        return getJson(url);
    }

    /**
     * Gets all the recent news of the specified guild in a JSON format (inside of a String)
     * @param server The game server the guild is located. Example: "goldrinn", "sargeras".
     * @param guild The name of the guild.
     * @param locale The language of the response.
     * @return
     * @throws IOException
     */
    public static String getGuildNews(String server, String guild, ApiLocale locale)
            throws IOException {
        URL url = new URL(getGuildApiLink("news", server, guild, locale));
        return getJson(url);
    }

    /**
     * Gets the proper URL for API calls for guild data.
     *
     * @param fields A value to tell the API to include the guild's specified data
     *               in the response. Example: "achievements", "members".
     * @param server The game server the guild is located. Example: "goldrinn", "sargeras".
     * @param guild The name of the guild.
     * @param locale The language of the response.
     * @return
     */
    private static String getGuildApiLink(String fields, String server, String guild, ApiLocale locale) {
        ApiLocale responseLocale = locale;
        if (responseLocale == null)
            responseLocale = ApiLocale.en_US;

        StringBuilder builder = new StringBuilder();
        builder.append(BASE_URL);
        builder.append("guild/");
        builder.append(server);
        builder.append("/");
        builder.append(guild);
        builder.append("?fields=");
        builder.append(fields);
        builder.append("&locale=");
        builder.append(responseLocale);
        builder.append("&apikey=");
        builder.append(API_KEY);
        return builder.toString();
    }

    private static String getJson(URL url) throws IOException {
        StringBuilder file = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " +responsecode);
        }
        else {
            Scanner sc = new Scanner(url.openStream());
            while (sc.hasNext()) {
                file.append(sc.nextLine());
            }
        }
        return file.toString();
    }

    public static enum ApiLocale {
        en_US, pt_BR, es_MX;
    }
}
