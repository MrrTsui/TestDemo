package com.taotao.povo;

import java.io.Serializable;
import java.util.List;

/**
 * * 第一层：u、n（包含a标签）、i
 * * 第二层：u、n、i
 * * 第三层：字符串
 * <p>
 * 菜单栏的viewVo
 * <p>
 * u代表 连接
 * n代表支付
 * i代表子类（由于子类是多类型，所以用泛型方法）
 */
public class MenuNode implements Serializable {

    private String menuName;

    private String url;

    private List<?> itme;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getItme() {
        return itme;
    }

    public void setItme(List<?> itme) {
        this.itme = itme;
    }
}
