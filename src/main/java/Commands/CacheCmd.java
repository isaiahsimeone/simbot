package Commands;

import com.github.kiulian.downloader.Config;
import com.github.kiulian.downloader.YoutubeDownloader;
import com.github.kiulian.downloader.downloader.YoutubeProgressCallback;
import com.github.kiulian.downloader.downloader.request.RequestVideoFileDownload;
import com.github.kiulian.downloader.downloader.request.RequestVideoInfo;
import com.github.kiulian.downloader.downloader.response.Response;
import com.github.kiulian.downloader.model.videos.VideoInfo;
import com.github.kiulian.downloader.model.videos.formats.Format;
import com.github.kiulian.downloader.model.videos.formats.VideoWithAudioFormat;
import simbot.SimPlayer;

import java.io.File;
import java.util.List;

public class CacheCmd implements Command {
    private SimPlayer simplayer;
    private String songID;

    public CacheCmd(SimPlayer simplayer, String songID) {
        this.simplayer = simplayer;
        this.songID = songID;
    }

    public String getCachedSongID() {
        return songID;
    }

    @Override
    public boolean execute() {
        Config config = new Config.Builder()
                .maxRetries(1)
                .header("Accept-Language", "en-AU,en;")
                .build();

        YoutubeDownloader downloader = new YoutubeDownloader(config);

        String videoID = "UO_QuXr521I";

        RequestVideoInfo req = new RequestVideoInfo(videoID);
        Response<VideoInfo> vinfo_response = downloader.getVideoInfo(req);
        VideoInfo video = vinfo_response.data();

        List<VideoWithAudioFormat> videoWithAudioFormats = video.videoWithAudioFormats();
        videoWithAudioFormats.forEach(it -> {
            System.out.println(it.audioQuality() + ", " + it.videoQuality() + " : " + it.url());
        });



        File outputDir = new File("my_videos");
        Format format = videoWithAudioFormats.get(0);

        // async downloading with callback
        RequestVideoFileDownload request = new RequestVideoFileDownload(format)
                .callback(new YoutubeProgressCallback<File>() {
                    @Override
                    public void onDownloading(int progress) {
                        System.out.printf("Downloaded %d%%\n", progress);
                    }

                    @Override
                    public void onFinished(File videoInfo) {
                        System.out.println("Finished file: " + videoInfo);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("Error: " + throwable.getLocalizedMessage());
                    }
                })
                .async();
        Response<File> response = downloader.downloadVideoFile(request);
        File data = response.data(); // will block current thread

        // Output help info
        return true;
    }

    @Override
    public String get_command_name() {
        return "cache";
    }
}
