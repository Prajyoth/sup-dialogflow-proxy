package com.university.supdialogflowproxy.Controller;


import com.university.supdialogflowproxy.Data.Article;
import com.university.supdialogflowproxy.Service.DialogflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sup-proxy/rest")
public class DialogflowController {

    @Autowired
    DialogflowService dialogflowService;

    @GetMapping("/query")
    public List<Article> getresult(@RequestParam String query) {
        return dialogflowService.getQueryResult(query);
    }
}