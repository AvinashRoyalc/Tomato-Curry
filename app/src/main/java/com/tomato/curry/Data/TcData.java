package com.tomato.curry.Data;

import com.tomato.curry.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by CompIndia on 25-06-2016.
 */
public class TcData {
    public String[] CitiesArray = {"TIRUPATHI", "NELLORE", "CHENNAI", "HYDERABAD", "BANGALORE", "VIJAYAWADA", "VISAKAPATNAM"};
    public int[] bannerpics_men = {R.drawable.naturals_lounge, R.drawable.green_trends, R.drawable.jawed_habib, R.drawable.naturals2, R.drawable.eemos, R.drawable.larken, R.drawable.chandu_s, R.drawable.cut_cut, R.drawable.gokul_sri, R.drawable.mrchange, R.drawable.voguefamilysalon};
    public int[] bannerpics_women = {R.drawable.naturals_lounge, R.drawable.green_trends, R.drawable.jawed_habib, R.drawable.naturals2, R.drawable.eemos, R.drawable.larken, R.drawable.chandu_s, R.drawable.chaitu_s, R.drawable.clouds_nine_women, R.drawable.missnmisses};
    public String[] SaloonsMen = {"naturals_lounge", "green_trends", "jawed_habib", "naturals", "eemos", "larken", "chandu_s", "cut_cut", "gokul_sri", "mrchange", "voguefamilysalon"};
    public String[] SaloonsWoMen = {"naturals_lounge", "green_trends", "jawed_habib", "naturals", "eemos", "larken", "chandu_s", "chaitu_s", "clouds_nine_women", "missnmisses"};
    public static boolean IsMen;
    public HashMap<String, String[]> SaloonHashmap = new HashMap<>();
    public String[] naturals_lounge_data = {"1", "Naturals Lounge", "Dr No:20-3-5E1,Tirumala bypass road,Sivajoythi\nNagar,Tirupati-517501,Andhra Pradesh,India", "4"};
    public String[] eemos_data = {"1", "eemos SPA", "Hotel Fortune Grand Ridge,Tiruchanoor road,\nTirupati-517501,Andhra pradesh,India", "5"};
    public String[] green_trends1_data = {"1", "Green Trends", "Plot No.161,first floor air by pass road(above State bank of Hyderabad),Tirupati-517501,Andhra Pradesh,India", "4"};
    public String[] green_trends2_data = {"2", "Green Trends", "Beside Kalanikethan,above Samsung showroom,1st floor,V.V.Mahal road,Tirupati-517501,Andhra Pradesh,India", "5"};
    public String[] jawed_habib_data = {"1", "Jawed Habib", "mr.palli circle,above ICICI Bank,3rd floor,Tirupati-517501,Andhra Pradesh,India", "4"};
    public String[] naturals_data1 = {"1", "Naturals", "Hathiramji colony 1st floor air by pass road\nopposite to HDFC Bank,Tirupati-517501,\nAndhra Pradesh,India", "3"};
    public String[] naturals_data2 = {"2", "Naturals", "Dr No:3/13/19b,Ramanuja circle,upstairs\nDominos Pizza,2nd floor,Tirupati-517501,\nAndhra Pradesh,India", "4"};
    public String[] larken_data = {"1", "Larken", "Dr No:10/116/2,near west church,maruthi nagar,Tirupati-517501,Andhra Pradesh,India", "5"};
    public String[] chandu_smen_data = {"1", "chandu's Mens Salon", "Dr No:16/153,Nethaji road,Tirupati-517501,\nAndhra Pradesh,India", "4"};
    public String[] cute_cut_data = {"1", "Cute & Cut", "Dr No:6-8-1252,shop no.7,Patta complex,\nAnnarao circle,n.g.o's colony, K.T.road,\nTirupati-517501,Andhra Pradesh,India", "3"};
    public String[] gokul_sri_data = {"1", "Gokul Sri", "Dr No:6-2-77,first floor,opposite Srinivasa theatre,Tirupati-517501,Andhra Pradesh,India", "4"};
    public String[] mrchange_data = {"1", "Mr Change", "Dr No:1-1-33,Berri street,Tirupati-517501,Andhra Pradesh,India", "4"};
    public String[] voguefamilysalon_data = {"1", "Vogue Family Salon", "Dr No:19-8-115/a1,1st floor Hathiramji colony,Air bypass road,Tirupati-517501,Andhra Pradesh,India", "4"};
    public String[] chaitu_s_data = {"1", "Chaitu's Beauty & SPA", "opp. Radhanarayana hospital,Hathiramji colony,Tirupati-517501,Andhra Pradesh,India", "5"};
    public String[] clouds_nine_women_data = {"1", "Cloud9", "opp. Radhanarayana hospital,Hathiramji colony,Tirupati-517501,Andhra Pradesh,India", "5"};
    public String[] missnmisses_data = {"1", "Miss n Misses", "Dr No:19-4-146/c,stv nagar,Tirupati-517501,\nAndhra Pradesh,India", "3"};

    public HashMap<String, String[]> getSaloonwomenHashmap(int position) {
        switch (position) {
            case 0:
                SaloonHashmap.put("naturals_lounge", naturals_lounge_data);
                break;
            case 1:
                SaloonHashmap.put("green_trends1", green_trends1_data);
                SaloonHashmap.put("green_trends2", green_trends2_data);
                break;
            case 2:
                SaloonHashmap.put("jawed_habib", jawed_habib_data);
                break;
            case 3:
                SaloonHashmap.put("naturals1", naturals_data1);
                SaloonHashmap.put("naturals2", naturals_data2);
                break;
            case 4:
                SaloonHashmap.put("eemos", eemos_data);
                break;
            case 5:
                SaloonHashmap.put("larken", larken_data);
                break;
            case 6:
                SaloonHashmap.put("chandu_s", chandu_smen_data);
                break;
            case 7:
                SaloonHashmap.put("chaitu_s", chaitu_s_data);
                break;
            case 8:
                SaloonHashmap.put("clouds_nine_women", clouds_nine_women_data);
                break;
            case 9:
                SaloonHashmap.put("missnmisses", missnmisses_data);
                break;

        }
        return SaloonHashmap;

    }

    public HashMap<String, String[]> getSaloonmenHashmap(int position) {
        switch (position) {
            case 0:
                SaloonHashmap.put("naturals_lounge", naturals_lounge_data);
                break;
            case 1:
                SaloonHashmap.put("green_trends1", green_trends1_data);
                SaloonHashmap.put("green_trends2", green_trends2_data);
                break;
            case 2:
                SaloonHashmap.put("jawed_habib", jawed_habib_data);
                break;
            case 3:
                SaloonHashmap.put("naturals1", naturals_data1);
                SaloonHashmap.put("naturals2", naturals_data2);
                break;
            case 4:
                SaloonHashmap.put("eemos", eemos_data);
                break;
            case 5:
                SaloonHashmap.put("larken", larken_data);
                break;
            case 6:
                SaloonHashmap.put("chandu_s", chandu_smen_data);
                break;
            case 7:
                SaloonHashmap.put("cut_cut", cute_cut_data);
                break;
            case 8:
                SaloonHashmap.put("gokul_sri", gokul_sri_data);
                break;

            case 9:
                SaloonHashmap.put("mrchange", mrchange_data);
                break;
            case 10:
                SaloonHashmap.put("voguefamilysalon", voguefamilysalon_data);
                break;
        }
        return SaloonHashmap;

    }

    //UniSex Saloon pics
    String[] naturals_lounge =
            {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals+Lounge/nl1.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals+Lounge/nl2.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals+Lounge/nl3.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals+Lounge/nl4.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals+Lounge/nl5.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals+Lounge/nl6.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals+Lounge/nl7.png"};
    String[] green_trends1 =
            {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/Air+BypassRoad/gtabr1.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/Air+BypassRoad/gtabr2.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/Air+BypassRoad/gtabr3.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/Air+BypassRoad/gtabr4.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/Air+BypassRoad/gtabr5.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/Air+BypassRoad/gtabr6.png"};
    String[] green_trends2 =
            {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/VV+Mahal+Road/gtvvm1.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/VV+Mahal+Road/gtvvm2.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/VV+Mahal+Road/gtvvm3.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/VV+Mahal+Road/gtvvm4.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/VV+Mahal+Road/gtvvm5.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Greed+Trends/VV+Mahal+Road/gtvvm6.png"};
    String[] jawed_habib =
            {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Javid+Habbib/jh.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Javid+Habbib/jh1.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Javid+Habbib/jh2.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Javid+Habbib/jh3.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Javid+Habbib/jh4.png"
            };
    String[] naturals1 =
            {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals/nat1hrcabr.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals/nat2hrcabr.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals/nat3hrcabr.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals/nat4hrcabr.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals/nat5hrcabr.png",
                    "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Naturals/nat6hrcabr.png"};
    String[] larken = {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Common/shop-images/Larken/lk.png"};
    String[] emos = {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Emos/em1.png"};

    //Mens Saloon pics
    String[] cut_cut = {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Cut+and+Cut/candc1.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Cut+and+Cut/candc2.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Cut+and+Cut/candc3.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Cut+and+Cut/candc4.png"
    };
    String[] gokul_sri = {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Gokul+Sree/gs1.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Gokul+Sree/gs2.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Gokul+Sree/gs3.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Gokul+Sree/gs4.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Gokul+Sree/gs5.png"
    };
    String[] mrchange = {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/MrChange/mrc1.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/MrChange/mrc2.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/MrChange/mrc3.png"
    };
    String[] voguefamilysalon = {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Voulgue+Family+saloon/vfs1.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Voulgue+Family+saloon/vfs2.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Voulgue+Family+saloon/vfs3.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Voulgue+Family+saloon/vfs4.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Male/shop-images/Voulgue+Family+saloon/vfs5.png"};

    //Womens Saloon pics
    String[] chandu_sw = {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chandu+womens/chsw1.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chandu+womens/chsw2.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chandu+womens/chsw3.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chandu+womens/chsw4.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chandu+womens/chsw5.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chandu+womens/chsw6.png"
    };
    String[] chaitu_s = {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chait%27s/chts1.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chait%27s/chts2.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chait%27s/chts3.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chait%27s/chts4.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chait%27s/chts5.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chait%27s/chts6.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Chait%27s/chts7.png"
    };
    String[] clouds_nine_women = {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Emos/em1.png"};
    String[] missnmisses = {"https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Miss+and+Misses/mnm1.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Miss+and+Misses/mnm2.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Miss+and+Misses/mnm3.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Miss+and+Misses/mnm4.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Miss+and+Misses/mnm5.png",
            "https://s3.ap-south-1.amazonaws.com/tomato-curry/Tirupathi/Female/shop-images/Miss+and+Misses/mnm6.png"
    };


    public ArrayList<String> getImageURLS(String name) {
        ArrayList<String> urls = new ArrayList<>();
        switch (name) {
            case "Dr No:20-3-5E1,Tirumala bypass road,Sivajoythi\\nNagar,Tirupati-517501,Andhra Pradesh,India":
                Collections.addAll(urls, naturals_lounge);
                break;
            case "Hotel Fortune Grand Ridge,Tiruchanoor road,\\nTirupati-517501,Andhra pradesh,India":
                Collections.addAll(urls, emos);
                break;
            case "Plot No.161,first floor air by pass road(above State bank of Hyderabad),Tirupati-517501,Andhra Pradesh,India":
                Collections.addAll(urls, green_trends1);
                break;
            case "Beside Kalanikethan,above Samsung showroom,1st floor,V.V.Mahal road,Tirupati-517501,Andhra Pradesh,India":
                Collections.addAll(urls, green_trends2);
                break;
            case "mr.palli circle,above ICICI Bank,3rd floor,Tirupati-517501,Andhra Pradesh,India":
                Collections.addAll(urls, jawed_habib);
                break;
            case "Hathiramji colony 1st floor air by pass road\\nopposite to HDFC Bank,Tirupati-517501,\\nAndhra Pradesh,India":
                Collections.addAll(urls, naturals1);
                break;
            case "Dr No:3/13/19b,Ramanuja circle,upstairs\\nDominos Pizza,2nd floor,Tirupati-517501,\\nAndhra Pradesh,India":
                Collections.addAll(urls, naturals1);
                break;
            case "Dr No:10/116/2,near west church,maruthi nagar,Tirupati-517501,Andhra Pradesh,India":
                Collections.addAll(urls, larken);
                break;
            case "Dr No:16/153,Nethaji road,Tirupati-517501,\\nAndhra Pradesh,India":
                Collections.addAll(urls, chandu_sw);
                break;
            case "Dr No:6-8-1252,shop no.7,Patta complex,\\nAnnarao circle,n.g.o's colony, K.T.road,\\nTirupati-517501,Andhra Pradesh,India":
                Collections.addAll(urls, cut_cut);
                break;
            case "Dr No:6-2-77,first floor,opposite Srinivasa theatre,Tirupati-517501,Andhra Pradesh,India":
                Collections.addAll(urls, gokul_sri);
                break;
            case "Dr No:1-1-33,Berri street,Tirupati-517501,Andhra Pradesh,India":
                Collections.addAll(urls, mrchange);
                break;
            case "Dr No:19-8-115/a1,1st floor Hathiramji colony,Air bypass road,Tirupati-517501,Andhra Pradesh,India":
                Collections.addAll(urls, voguefamilysalon);
                break;
            case "opp. Radhanarayana hospital,Hathiramji colony,Tirupati-517501,Andhra Pradesh,India":
                Collections.addAll(urls, chaitu_s);
                break;
            case "Dr No:19-4-146/c,stv nagar,Tirupati-517501,\\nAndhra Pradesh,India":
                Collections.addAll(urls, missnmisses);
                break;
        }
        return urls;
    }

}
