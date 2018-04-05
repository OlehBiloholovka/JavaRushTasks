package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AdvertisementStorage {
    private final List<Advertisement> videos = new ArrayList<>();
    private static AdvertisementStorage ourInstance = new AdvertisementStorage();

    public static AdvertisementStorage getInstance() {
        return ourInstance;
    }

    private AdvertisementStorage() {
        Object someContent = new Object();
        Advertisement first_video
                = new Advertisement(someContent, "First Video", 5000, 100, 3 * 60);// 3 min
        Advertisement second_video
                = new Advertisement(someContent, "Second Video", 100, 10, 15 * 60);//15 min
        Advertisement third_video
                = new Advertisement(someContent, "Third Video", 400, 2, 10 * 60);//10 min
        Advertisement forth_video = new Advertisement(someContent, "Forth Video", 5000, 100, 10 * 60);//5 min
        Collections.addAll(videos, first_video, second_video, third_video, forth_video);

    }

    public List<Advertisement> list(){
        return videos;
    }

    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }
}
