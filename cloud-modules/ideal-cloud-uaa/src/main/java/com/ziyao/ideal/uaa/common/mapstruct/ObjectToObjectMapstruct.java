package com.ziyao.ideal.uaa.common.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
@Mapper
public interface ObjectToObjectMapstruct {

    ObjectToObjectMapstruct INSTANCE = Mappers.getMapper(ObjectToObjectMapstruct.class);
    
}
