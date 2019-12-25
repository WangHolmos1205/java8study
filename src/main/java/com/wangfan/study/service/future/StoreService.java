package com.wangfan.study.service.future;

public interface StoreService {

    /**
     * java8默认方法 获取商品价格
     * @param name
     * @return
     */
    /**
     * 默认方法是为了让接口的实现类，自动拥有默认实现方法， 这样接口迭代更新加入新的方法后，
     * 以前的失联类能够自动继承
     *
     * @param name
     * @return
     */
    default Double getPrice(String name) {
        try {
            Thread.sleep(200L);
        } catch (Exception e) {
        }
        System.out.println("1D");
        return 1D;
    }

    /**
     * java8默认方法 获取商品信息
     *
     * @param name
     * @return
     */
    default String getMessage(String name) {
        try {
            Thread.sleep(200L);
        } catch (Exception e) {
        }
        System.out.println(name);
        return name;
    }

}
