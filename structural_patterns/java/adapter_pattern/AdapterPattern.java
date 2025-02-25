package structural_patterns.java.adapter_pattern;

// Media Player Interface
interface IMediaPlayer{
    void play();
}

// Concrete Implementations of IMediaPlayer
class MP4Player implements IMediaPlayer{
    String file;
    MP4Player(String file){
        this.file = file;
    }

    @Override
    public void play(){
        if( file.contains("mp4") ){
            System.out.println("Playing Mp4 Video");
        }else{
            System.out.println("Unable to play video.");
        }
    }
}

// Adapter class - converts HVEC TO MP4
class HVECAdapater implements IMediaPlayer{
    MP4Player mp4Player;
    HVECAdapater(String file){
        file += "mp4";
        mp4Player = new MP4Player(file);
    }

    @Override
    public void play(){
        mp4Player.play();
    }
}

// Client
class AdapterPattern {
    public static void main(String[] args) {
        // MP4 example
        IMediaPlayer mp4 = new MP4Player("mp4");
        mp4.play();

        // Will Not Play HVEC file.
        IMediaPlayer hvec = new MP4Player("hvec");
        hvec.play();

        // Converts HVEC to MP4 and file plays.
        hvec = new HVECAdapater("hvec");
        hvec.play();
    }
}
