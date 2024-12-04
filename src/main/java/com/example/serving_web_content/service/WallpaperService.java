package com.example.serving_web_content.service;


import com.example.serving_web_content.Entity.Wallpaper;
import com.example.serving_web_content.repository.WallpaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WallpaperService {

    private final WallpaperRepository wallpaperRepository;

    @Autowired
    public WallpaperService(WallpaperRepository wallpaperRepository) {
        this.wallpaperRepository = wallpaperRepository;
    }

    public List<Wallpaper> getAllItems() {
        return wallpaperRepository.findAll();
    }

    public Wallpaper saveItem(Wallpaper wallpaper) {
        return wallpaperRepository.save(wallpaper);
    }

    public void deleteItem(Long id) {
        wallpaperRepository.deleteById(id);
    }

    public Wallpaper findById(Long id) {
        return wallpaperRepository.findById(id).orElse(null);
    }
}
