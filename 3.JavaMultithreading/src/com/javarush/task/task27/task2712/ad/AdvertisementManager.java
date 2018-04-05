package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().isEmpty()) throw new NoVideoAvailableException();
        int storageSize = storage.list().size();
        List<Advertisement> videoList = getVideoList(timeSeconds, storageSize - 1);

        videoList.sort((o1, o2) -> {
            if (o1.getAmountPerOneDisplaying() > o2.getAmountPerOneDisplaying()) return -1;
            if (o1.getAmountPerOneDisplaying() < o2.getAmountPerOneDisplaying()) return 1;
            return Long.compare(o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration()
                    , o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration());
        });

        long amount = videoList.stream()
                .mapToLong(Advertisement::getAmountPerOneDisplaying)
                .sum();
        int totalDuration = videoList.stream()
                .mapToInt(Advertisement::getDuration)
                .sum();

        StatisticManager.getInstance()
                .register(new VideoSelectedEventDataRow(videoList, amount, totalDuration));


        videoList.forEach(advertisement -> {
            long amountPerOneDisplaying = advertisement.getAmountPerOneDisplaying();
            long amountPerSecond = amountPerOneDisplaying * 1000 / advertisement.getDuration();
//            System.out.println("beforeREVALIDE: " + advertisement.getName() + ": " + advertisement.getAmountPerOneDisplaying() + ": " + advertisement.getHits());
            advertisement.revalidate();
            ConsoleHelper.writeMessage(String
                    .format("%s is displaying... %d, %d"
                            , advertisement.getName()
                            , amountPerOneDisplaying
                            , amountPerSecond));

        });
    }

    private List<Advertisement> getVideoList(int maxTimeSeconds, int videoIndex){

        List<Advertisement> advertisements = new ArrayList<>();
        if (videoIndex < 0) return advertisements;

        List<Advertisement> list = storage.list();
        Advertisement thisVideo = list.get(videoIndex);

        List<Advertisement> videoListWithoutThisVideo = getVideoList(maxTimeSeconds, videoIndex - 1);

        if (thisVideo.getDuration() > maxTimeSeconds || thisVideo.getAmountPerOneDisplaying() <= 0) {
            return videoListWithoutThisVideo;
        }
        List<Advertisement> videoListWithThisVideo = getVideoList(maxTimeSeconds - thisVideo.getDuration(), videoIndex - 1);
        videoListWithThisVideo.add(thisVideo);

        if (checkBetterVideoList(videoListWithoutThisVideo, videoListWithThisVideo)) {
            return videoListWithoutThisVideo;
        } else {
            return videoListWithThisVideo;
        }

    }

    private boolean checkBetterVideoList(List<Advertisement> videoListWithoutThisVideo
            , List<Advertisement> videoListWithThisVideo) {
        long amountWithoutThisVideo = videoListWithoutThisVideo.stream()
                .mapToLong(Advertisement::getAmountPerOneDisplaying)
                .sum();

        long amountWithThisVideo = videoListWithThisVideo.stream()
                .mapToLong(Advertisement::getAmountPerOneDisplaying)
                .sum();

        if (amountWithoutThisVideo == amountWithThisVideo){
            long durationWithoutThisVideo = videoListWithoutThisVideo.stream()
                    .mapToLong(Advertisement::getDuration)
                    .sum();

            long durationWithThisVideo = videoListWithThisVideo.stream()
                    .mapToLong(Advertisement::getDuration)
                    .sum();

            if (durationWithoutThisVideo == durationWithThisVideo) {
                return videoListWithoutThisVideo.size() < videoListWithThisVideo.size();
            }
            return durationWithoutThisVideo > durationWithThisVideo;
        }
        return amountWithoutThisVideo > amountWithThisVideo;
    }


}
