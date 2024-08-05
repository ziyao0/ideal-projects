package com.ziyao.ideal.web.base;

import com.ziyao.ideal.jpa.extension.service.JapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author ziyao
 * @see <a href="https://blog.zziyao.cn">https://blog.zziyao.cn</a>
 */
public class JpaBaseController<S extends JapService<T, ID>, T, ID> {

    @Autowired
    protected S jpaService;

    /**
     * 获取所有数据
     * <p>不分页</p>
     */
    @PostMapping("/list")
    public List<T> list() {
        return jpaService.findAll();
    }

    /**
     * 通过id删除数据，有逻辑删除按照逻辑删除执行
     * <p>不支持联合主键</p>
     *
     * @param id 主键Id
     */
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable("id") ID id) {
        jpaService.deleteById(id);
    }

}
