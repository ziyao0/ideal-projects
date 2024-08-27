package com.ziyao.ideal.generator.mybatisplus;


/**
 * Json类型处理器接口(实现类确保为多例状态).
 * <p>
 * 注意:查询返回时需要使用resultMap
 *
 * <pre>
 * Example:
 *     &lt;result property="xx" column="xx" javaType="list" typeHandler="com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler"/&gt;
 *     &lt;result property="xx" column="xx" typeHandler="com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler"/&gt;
 * </pre>
 * </p>
 */
public interface IJsonTypeHandler<T> {

    /**
     * 反序列化json
     *
     * @param json json字符串
     * @return T
     */
    T parse(String json);

    /**
     * 序列化json
     *
     * @param obj 对象信息
     * @return json字符串
     */
    String toJson(T obj);

}
