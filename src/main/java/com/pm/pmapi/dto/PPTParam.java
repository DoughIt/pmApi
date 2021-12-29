package com.pm.pmapi.dto;

import com.pm.pmapi.mbg.model.TabLesson;
import com.pm.pmapi.mbg.model.TabUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/**
 * @program: pmApi
 * @description: ppt parameters
 * @author: Shen Zhengyu
 * @create: 2021-12-07 13:47
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PPTParam {
    private String filename;
    private String name;
    private Long lessonId;
    private String chapters;
    private String paperSize;
    private String newDegree;
    private String content;
    private Double price;
    private Boolean singlePrint;
}
