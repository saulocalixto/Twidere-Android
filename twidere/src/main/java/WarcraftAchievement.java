import java.io.IOException;

/**
 * Created by Lucas Sampaio Dias on 27/09/2018.
 */

public class WarcraftAchievement {
    public String id;
    public String title;
    public int points;
    public String reward;
    //public Image icon;

    public WarcraftAchievement(String id, WarcraftApi.ApiLocale locale) throws IOException {
        this.id = id;
        ParseJson(locale);
    }

    private void ParseJson(WarcraftApi.ApiLocale locale) throws IOException {
        WarcraftApi api = new WarcraftApi();
        String achievementJson = api.getAchievement(id, locale);

        //Ler o JSON e atribuir as variaveis restantes
    }
}
