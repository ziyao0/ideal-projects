package com.ziyao.ideal.usercenter.domain.entity;

import com.ziyao.ideal.web.AbstractTreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ziyao
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class MenuTree extends AbstractTreeNode {
    public MenuTree() {
    }

    public MenuTree(String name) {
        setName(name);
    }
}