package com.my.test.controller;

import com.my.test.TestStarter;
import com.my.test.controller.domain.UrlInfo;
import com.my.test.controller.domain.UrlPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestStarter .class)
@Slf4j
//@SpringBootTest
//@WebAppConfiguration

public class UrlTest {
    @Autowired
    private ApplicationContext context;


    @Test
    public void getPcUrl() throws IOException, ClassNotFoundException {

        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
        Resource[] resources = pathMatchingResourcePatternResolver.getResources("aa");
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        List<UrlPage> pcPages = new LinkedList<>();
        List<UrlPage> mobilePages = new LinkedList<>();

        List<String> classNames = new LinkedList<>();
        for (Resource resource : resources) {

            MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
            Class<?> clz = systemClassLoader.loadClass(metadataReader.getClassMetadata().getClassName());//1每一个类
            try {
//1.1每一个类的基本url菜单名
                Api apiAnnotation = clz.getAnnotation(Api.class);
                String urlPageName = "";
                if (apiAnnotation != null && apiAnnotation.tags() != null) {

                    urlPageName = apiAnnotation.tags()[0];
                }
                String basePageUrl = "";
                RequestMapping RequestMappingTemp = clz.getDeclaredAnnotation(RequestMapping.class);
                if (RequestMappingTemp != null) {
                    String[] basePageUrlValues = (RequestMappingTemp).value();
                    basePageUrl = ArrayUtils.isNotEmpty(basePageUrlValues) ? basePageUrlValues[0] : "";
                }


                if (StringUtils.isNotBlank(basePageUrl)) {
                    basePageUrl = (basePageUrl.startsWith("/") ? basePageUrl : ("/" + basePageUrl));
                }


                UrlPage urlPage = UrlPage.builder().pageName(urlPageName).basePagePath(basePageUrl).urlInfoList(new LinkedList<>()).build();
                Method[] declaredMethods = clz.getDeclaredMethods();

                for (Method declaredMethod : declaredMethods) {
                    Annotation[] methodDeclaredAnnotations = declaredMethod.getDeclaredAnnotations();

                    for (Annotation methodDeclaredAnnotation : methodDeclaredAnnotations) {
                        UrlInfo urlInfoTemp = null;
                        String thisMethodUrl;

                        if (methodDeclaredAnnotation.annotationType().getDeclaredAnnotation(RequestMapping.class) != null) {



            /*                Class<? extends Annotation> annotationTypeClz = methodDeclaredAnnotation.annotationType();
                            Field valueField = annotationTypeClz.getDeclaredField("value");*/

                            thisMethodUrl = getPathUrl(methodDeclaredAnnotation);
                            thisMethodUrl = basePageUrl + (thisMethodUrl.startsWith("/") ? thisMethodUrl : "/" + thisMethodUrl);
                            urlInfoTemp = new UrlInfo(declaredMethod.getDeclaredAnnotation(ApiOperation.class).notes(), basePageUrl + thisMethodUrl, getMethodName(methodDeclaredAnnotation));
                        }


                        if (methodDeclaredAnnotation instanceof RequestMapping) {

                            String[] value = ((RequestMapping) methodDeclaredAnnotation).value();
                            thisMethodUrl = ArrayUtils.isNotEmpty(value) ? value[0] : "";
                            thisMethodUrl = basePageUrl + (thisMethodUrl.startsWith("/") ? thisMethodUrl : "/" + thisMethodUrl);
                            urlInfoTemp = new UrlInfo(declaredMethod.getDeclaredAnnotation(ApiOperation.class).notes(), basePageUrl + thisMethodUrl, getMethodName(methodDeclaredAnnotation));

                        }

                        if (urlInfoTemp != null) {
                            urlPage.getUrlInfoList().add(urlInfoTemp);
                        }

                    }

                }


                pcPages.add(urlPage);

            } catch (Exception e) {

                log.error("", e);
            }
//            classNames.add(clz.getName());

        }
//        System.out.println(classNames);

//        System.out.println(pcPages);
        StringBuilder sb = new StringBuilder(1000);

        pcPages.forEach(o -> {
                    sb.append(o.getPageName()).append("  ").append(o.getBasePagePath()).append("\n");
                    o.getUrlInfoList().forEach(p -> {
                        sb.append("  ").append(p.getName()).append("  ").append(p.getUrl()).append("  ").append(p.getMethod()).append("\n");
                    });
                }
        );
        System.out.println(sb);

    }

    private String getMethodName(Annotation annotation) {

        Class<? extends Annotation> annotationClz = annotation.annotationType();

        RequestMapping requestMappingAnno = annotationClz.getDeclaredAnnotation(RequestMapping.class);

        if (requestMappingAnno != null) {
            return requestMappingAnno.method()[0].name().toLowerCase();
        } else if (annotation instanceof RequestMapping) {
            return ((RequestMapping) annotation).method()[0].name().toLowerCase();
        }
        return "";
    }

    /**
     * 根据几个Mapping 的注解返回path
     *
     * @param annotation
     * @return
     */
    private String getPathUrl(Annotation annotation) {

        String pathUrl = "";

        if (annotation.annotationType().getDeclaredAnnotation(RequestMapping.class) != null) {

            String[] valuesTemp = null;
            if (annotation instanceof GetMapping) {
                valuesTemp = ((GetMapping) annotation).value();
            }
            if (annotation instanceof PostMapping) {
                valuesTemp = ((PostMapping) annotation).value();
            }
            if (annotation instanceof PutMapping) {
                valuesTemp = ((PutMapping) annotation).value();
            }
            if (annotation instanceof DeleteMapping) {
                valuesTemp = ((DeleteMapping) annotation).value();
            }
            if (annotation instanceof PatchMapping) {
                valuesTemp = ((PatchMapping) annotation).value();
            }
            pathUrl = valuesTemp != null && valuesTemp.length > 0 ? valuesTemp[0] : "";
        }

        return pathUrl;

    }


}
