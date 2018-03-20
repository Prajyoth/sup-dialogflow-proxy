package com.university.supdialogflowproxy.Service;

import com.university.supdialogflowproxy.Data.Article;

import java.util.List;

public interface DialogflowService {
    List<Article> getQueryResult(String query);
}
