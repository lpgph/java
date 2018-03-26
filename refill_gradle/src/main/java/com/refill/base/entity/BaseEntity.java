package com.refill.base.entity;

import com.alibaba.fastjson.JSONObject;
import java.io.*;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName BaseEntity
 * @Description 基类实体
 * <br>实现Serializable接口  启用其序列化功能。
 * <br>Cloneable  启用克隆功能（深拷贝）。
 * @date 17/05/02
 */
public abstract class BaseEntity implements Serializable, Cloneable {

    /**
     * 深度克隆方法１：
     * @return
     */
//    public BaseEntity clone() {
//        BaseEntity baseEntity = null;
//        try {
//            baseEntity = (BaseEntity) super.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return baseEntity;
//    }

    //深度克隆方法２：
//    protected BaseEntity() {
//        throw new AssertionError();
//    }


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    /**
     *
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();

        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }
}
