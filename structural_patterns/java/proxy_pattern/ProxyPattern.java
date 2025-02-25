package structural_patterns.java.proxy_pattern;

import java.util.*;

// Internet Interface
interface Internet{
    void connectTo(String site);
}

class PublicInternet implements Internet{
    @Override
    public void connectTo(String site){
        System.out.println("[OK]: Connecting to " + site);
    }
}

class ProxiedInternet implements Internet{
    private static List<String> blockedSites;
    PublicInternet internet;
    static{
        blockedSites = new ArrayList<>();
        blockedSites.add("facebook.com");
        blockedSites.add("youtube.com");
        blockedSites.add("games.com");
    }

    ProxiedInternet(){
        internet = new PublicInternet();
    }

    public void connectTo(String site){
        if( blockedSites.contains(site) ){
            System.out.println("[FAILED]: Access denied to " + site);
        }else{
            internet.connectTo(site);
        }
    }
}

// Client
class ProxyPattern {
    public static void main(String[] args) {
        Internet pi = new PublicInternet();
        pi.connectTo("games.com");

        Internet proxy = new ProxiedInternet();
        proxy.connectTo("games.com");
    }
}
