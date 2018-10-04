package org.mariotaku.microblog.library.wow.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Lucas Sampaio Dias on 26/09/2018.
 */

public class WarcraftApi {

    private static final String API_KEY = "awydkuy9tx3zksd7kkh2g5xchnwv4qy3";
    private static final String BASE_URL = "https://us.api.battle.net/wow/";
    private static StringBuilder stringBuilder;

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
     * Gets the data of the specified achievement in a JSON format (inside of a String)
     * @param id The id of the Achievement. You can test an id using wowhead.com
     *           Example: "https://wowhead.com/achievement=11163".
     * @param locale The language of the response.
     * @return
     * @throws IOException
     */
    public static String getAchievement(String id, ApiLocale locale) throws IOException {
        return getJson(new URL(getAchievementApiLink(id, locale)));
    }

    private static String getAchievementApiLink(String id, ApiLocale locale) {
        ApiLocale responseLocale = parseLocale(locale);

        return getStringBuilder()
            .append(BASE_URL)
            .append("achievement/")
            .append(id)
            .append("&locale=")
            .append(responseLocale)
            .append("&apikey=")
            .append(API_KEY)
            .toString();
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
        ApiLocale responseLocale = parseLocale(locale);

        return getStringBuilder()
            .append(BASE_URL)
            .append("guild/")
            .append(server)
            .append("/")
            .append(guild)
            .append("?fields=")
            .append(fields)
            .append("&locale=")
            .append(responseLocale)
            .append("&apikey=")
            .append(API_KEY)
            .toString();
    }

    private static String getJson(URL url) throws IOException {
        StringBuilder file = getStringBuilder();
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

    private static ApiLocale parseLocale(ApiLocale locale) {
        ApiLocale responseLocale = locale;
        if (responseLocale == null)
            responseLocale = ApiLocale.en_US;

        return responseLocale;
    }

    private static StringBuilder getStringBuilder() {
        return stringBuilder != null
                ? stringBuilder
                : (stringBuilder = new StringBuilder());
    }

    /**
     * All available languages for Battle.net API responses
     */
    public static enum ApiLocale {
        en_US, pt_BR, es_MX;
    }
}
