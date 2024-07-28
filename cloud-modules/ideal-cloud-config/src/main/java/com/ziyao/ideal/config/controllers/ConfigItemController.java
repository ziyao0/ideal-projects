package com.ziyao.ideal.config.controllers;

import com.ziyao.ideal.config.domain.dto.ConfigItemDTO;
import com.ziyao.ideal.config.domain.entity.ConfigItem;
import com.ziyao.ideal.config.services.ConfigItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@RestController
@RequestMapping("/configItem")
@RequiredArgsConstructor
public class ConfigItemController {

    private final ConfigItemService configItemService;


    @GetMapping("/{id}")
    public ConfigItem getConfigItemById(@PathVariable Integer id) {
        return configItemService.getConfigItemById(id);
    }

    @PostMapping
    public void addConfigItem(@RequestBody ConfigItemDTO configItem) {

    }

    @PutMapping
    public void updateConfigItem(@RequestBody ConfigItem configItem) {

    }

    @DeleteMapping
    public void deleteConfigItem(@RequestBody ConfigItemDTO configItem) {

    }
}
