package com.wangfan.study.service.impl.future;

import com.wangfan.study.service.future.StoreService;
import org.springframework.stereotype.Service;

@Service
public class CatStoreServiceImpl implements StoreService {

    @Override
    public Double getPrice(String name){
        try {
            Thread.sleep(100L);
        } catch (Exception e) {
        }
        System.out.println("cat:1.5D");
        return 1.5D;
    }

    @Override
    public String getMessage(String name) {
        try {
            Thread.sleep(100L);
        } catch (Exception e) {
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("cat:").append(name);
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
