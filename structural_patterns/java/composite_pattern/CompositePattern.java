package structural_patterns.java.composite_pattern;

import java.util.*;

interface FileSystem{
    void listFiles();
    Integer countFiles();
}

class File implements FileSystem{
    String name;
    File(String name){
        this.name = name;
    }

    @Override
    public void listFiles(){
        System.out.println("[File]: " + this.name);
    }

    @Override
    public Integer countFiles(){
        return 1;
    }
}

class Directory implements FileSystem{
    String name;
    List<FileSystem> directory;

    Directory(String name){
        this.name = name;
        this.directory = new ArrayList<FileSystem>();
    }

    public void addFileOrFolder(FileSystem fileOrFolder){
        directory.add(fileOrFolder);
    }

    @Override
    public void listFiles(){
        for (FileSystem file : directory) {
            file.listFiles();
        }
    }

    @Override
    public Integer countFiles(){
        Integer total = 0;
        for (FileSystem file : directory) {
            total += file.countFiles();
        }
        return total;
    }
}

class CompositePattern {
    public static void main(String[] args) {
        Directory movies = new Directory("Movies");
        Directory songs = new Directory("Songs");
        Directory games = new Directory("Games");

        movies.addFileOrFolder(new File("KGF-1"));
        movies.addFileOrFolder(new File("KGF-2"));
        movies.addFileOrFolder(new File("PUSHPA"));

        songs.addFileOrFolder(new File("KGF-SONG-1.mp3"));
        songs.addFileOrFolder(new File("KGF-SONG-2.mp3"));
        songs.addFileOrFolder(new File("PUSHPA-SONG.mp3"));

        games.addFileOrFolder(new File("GTA-V"));
        games.addFileOrFolder(new File("TOMB RAIDER"));
        games.addFileOrFolder(new File("COUNTER STRIKE"));

        Directory enterainment = new Directory("Entertainment");
        enterainment.addFileOrFolder(movies);
        enterainment.addFileOrFolder(songs);
        enterainment.addFileOrFolder(games);
        enterainment.addFileOrFolder(new File("VLOG-1"));
        enterainment.addFileOrFolder(new File("VLOG-2"));
        enterainment.addFileOrFolder(new File("VLOG-3"));
        enterainment.addFileOrFolder(new File("VLOG-4"));

        System.out.println("Printing all files in entertainment directory: ");
        enterainment.listFiles();
        System.out.println();
        System.out.println("Total Files Count: " + enterainment.countFiles());
    }
}
