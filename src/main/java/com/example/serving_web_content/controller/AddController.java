package com.example.serving_web_content.controller;

import com.example.serving_web_content.Entity.Wallpaper;
import com.example.serving_web_content.service.WallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/addWallpapers")
public class AddController {

    private final WallpaperService wallpaperService;

    @Autowired
    public AddController(WallpaperService wallpaperService) {
        this.wallpaperService = wallpaperService;
    }

    @PostMapping
    public ResponseEntity<Wallpaper> addWallpaper(@RequestParam String name, @RequestParam Long number, @RequestParam String description, @RequestParam String img) {
        // Создаем объект Item и заполняем данными
        Wallpaper wallpaper = new Wallpaper();
        wallpaper.setName(name);
        wallpaper.setAmount(number);
        wallpaper.setDescription(description);
        wallpaper.setSrc(img);

        // Сохраняем объект через сервис
        Wallpaper savedWallpaper = wallpaperService.saveItem(wallpaper);

        // Возвращаем сохраненный объект
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/main"))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wallpaper> updateItemAmount(@PathVariable Long id, @RequestBody UpdateAmount request) {
        // Получаем существующий товар
        Wallpaper wallpaper = wallpaperService.findById(id);
        if (wallpaper == null) {
            return ResponseEntity.notFound().build();
        }

        // Обновляем количество
        wallpaper.setAmount(request.getAmount());
        Wallpaper updatedWallpaper = wallpaperService.saveItem(wallpaper);

        // Возвращаем обновленный товар
        return ResponseEntity.ok(updatedWallpaper);
    }
}

// DTO для приема количества
class UpdateAmount {
    private Long amount;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}