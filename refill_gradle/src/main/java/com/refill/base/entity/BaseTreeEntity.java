package com.refill.base.entity;

import com.refill.base.constant.Const;

/**
 * Created by peter on 5/10/17.
 */
public class BaseTreeEntity extends BaseEntity {

    /**
     * 数结构自增长ID
     */
    private int baseTreeId;

    /**
     * 树结构父级ID
     */
    private int baseTreeParentId;

    /**
     * 树结构父级路径
     */
    private String baseTreeParentPath;

    public int getBaseTreeId() {
        return baseTreeId;
    }

    public void setBaseTreeId(int baseTreeId) {
        this.baseTreeId = baseTreeId;
    }

    public int getBaseTreeParentId() {
        return baseTreeParentId;
    }

    public void setBaseTreeParentId(int baseTreeParentId) {
        this.baseTreeParentId = baseTreeParentId;
    }

    public String getBaseTreeParentPath() {
        return baseTreeParentPath;
    }

    public void setBaseTreeParentPath(String baseTreeParentPath) {
        this.baseTreeParentPath = baseTreeParentPath;
    }

    /**
     * 获取子级路径前缀
     * @return
     */
    public String getChildsPathPrefix(){
        return this.baseTreeParentPath+ Const.SEPARATOR+this.baseTreeId;
    }
}
