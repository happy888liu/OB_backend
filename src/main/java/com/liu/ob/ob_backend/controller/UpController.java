package com.liu.ob.ob_backend.controller;

import com.liu.ob.ob_backend.core.Message;
import com.liu.ob.ob_backend.core.Paging;
import com.liu.ob.ob_backend.model.Up;
import com.liu.ob.ob_backend.model.UpData;
import com.liu.ob.ob_backend.service.UpService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/up")
public class UpController {
    @Resource
    private UpService upService;

    /**
     * 观测新用户
     *
     * @param mid 用户id
     */
    @GetMapping("/watch")
    public ResponseEntity<?> addUp2Watch(@RequestParam("mid") Long mid, @RequestParam("interval") Long interval) {
        upService.addUp2Watch(mid);
        return new ResponseEntity<>(new Message(200, "观测UP主成功"), HttpStatus.OK);
    }

    /**
     * 将用户从观测队列软删除
     *
     * @param mid 用户id
     */
    @GetMapping("/removeWatch/{mid}")
    public ResponseEntity<?> removeUp2Watch(@PathVariable("mid") Long mid) {
        upService.removeUp2Watch(mid);
        return new ResponseEntity<>(new Message(200, "移除UP主观测"), HttpStatus.OK);
    }

    /**
     * 根据分类并分页Top up主
     */
    @GetMapping("/top")
    public Paging<Up> getTopUps(@RequestParam(defaultValue = "0") Integer sort,
                                @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "20") Integer size) {
        Page<Up> topUps = upService.getTopUps(sort, page, size);
        return new Paging<>(topUps);
    }

    @GetMapping("/detail")
    public Up getUpDetail(@RequestParam("mid") Long mid) {
        return upService.getUpDetail(mid);
    }

    @GetMapping("/history")
    public List<UpData> getHistory(@RequestParam("mid") Long mid, @RequestParam(value = "days", defaultValue = "1") int days) {
        return upService.getHistory(mid, days);
    }
}
