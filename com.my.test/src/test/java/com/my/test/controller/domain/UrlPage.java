package com.my.test.controller.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlPage {

    private String pageName;

    List<UrlInfo> urlInfoList;

    private String basePagePath;
}
