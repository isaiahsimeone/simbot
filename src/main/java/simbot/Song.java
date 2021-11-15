package simbot;

public class Song {
    private String song_name;
    private String song_url;

    public Song(String song_name, String song_url) {
        this.song_name = song_name;
        this.song_url = song_url;
    }

    public String get_song_name() {
        return song_name;
    }

    public String get_song_url() {
        return song_url;
    }
}
