package structural_patterns.java.bridge_pattern;

// Cache Interface
interface Cache{
    void put();
    void get();
}

class Redis implements Cache{
    @Override
    public void put(){
        System.out.println("[Redis]: adding value");
    }

    @Override
    public void get(){
        System.out.println("[REDIS]: getting value.");
    }
}

class Memcahced implements Cache{
    @Override
    public void put(){
        System.out.println("[MEM_CACHED]: adding value");
    }

    @Override
    public void get(){
        System.out.println("[MEM_CACHED]: getting value.");
    }
}

class CouchBase implements Cache{
    @Override
    public void put(){
        System.out.println("[COUCH_BASE]: adding value");
    }

    @Override
    public void get(){
        System.out.println("[COUCH_BASE]: getting value.");
    }
}

// Some Microservice
abstract class Service{
    protected Cache cache;
    Service(Cache cache){
        this.cache = cache;
    }

    abstract public void put();
    abstract public void get();
}

class MediaService extends Service{
    MediaService(Cache cache){
        super(cache);
    }

    @Override
    public void put(){
        cache.put();
    }

    @Override
    public void get(){
        cache.get();
    }
}

class MemeService extends Service{
    MemeService(Cache cache){
        super(cache);
    }

    @Override
    public void put(){
        cache.put();
    }

    @Override
    public void get(){
        cache.get();
    }
}

// Client
class BridgePattern {
    public static void main(String[] args) {
        Service mediaService = new MediaService(new Redis());
        Service memeService = new MemeService(new CouchBase());

        mediaService.put();
        memeService.put();
        mediaService.get();
        memeService.get();
    }
}
