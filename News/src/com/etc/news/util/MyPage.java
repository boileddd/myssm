package com.etc.news.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * �Զ���ķ�ҳ��ǩ
 * @author zyy
 * @version1.0
 * @category 
 */
public class MyPage extends TagSupport {

    private String url = null;
    private int pageIndex;
    private int pageMax;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageMax(int pageMax) {
        this.pageMax = pageMax;
    }

    public int getPageMax() {
        return this.pageMax;
    }


    @Override
    public int doStartTag() throws JspException {
        StringBuffer str = new StringBuffer();
        if (pageIndex == 1) {
            str.append("��ҳ&nbsp;��һҳ ");
        } else {
            str.append(" <a href='" + url + "page=1'>��ҳ</a> "
                    + "<a href='" + url + "page=" + (pageIndex - 1) + "'>��һҳ</a> ");
        }
        if (pageIndex / 6 < 1.0 || pageMax < 10) {
            for (int i = 1; i <= 9; i++) {
                if (i <= pageMax) {
                    if (pageIndex != i) {
                        str.append("<a href='" + url + "page=" + i + "'>[" + i + "]</a> ");
                    } else {
                        str.append("  " + i + " ");
                    }
                }
            }
        } else if (pageIndex / 6 >= 1.0 && pageMax >= 10) {
            int fri = 0;
            int max = 0;
            if (pageMax - pageIndex > 4) {
                fri = pageIndex - 4;
                max = pageIndex + 4;
            } else {
                fri = pageMax - 8;
                max = pageMax;
            }
            for (int i = fri; i <= max; i++) {
                if (i <= pageMax) {
                    if (pageIndex != i) {
                        str.append("<a href='" + url + "page=" + i + "'>[" + i + "]</a> ");
                    } else {
                        str.append("  " + i + " ");
                    }
                }
            }
        }
        if (pageIndex == pageMax || pageMax < 2) {
            str.append("��һҳ&nbsp;βҳ");
        } else {
            str.append("<a href='" + url + "page=" + (pageIndex + 1) + "'>��һҳ</a> "
                    + "<a href='" + url + "page=" + pageMax + "'>βҳ</a>");
        }
        str.append("&nbsp;��ת����<select onchange=\"location='"+url+"page='+this.value;\">");
        for(int i=1;i<=pageMax;i++){
        	str.append("<option value='"+i+"' "+(i==pageIndex?"selected":"")+">��"+i+"ҳ</option>");
        }
        str.append("</select>");
        try {
            if (str.length()>0) {
                pageContext.getOut().write(new String(str));
            }
        } catch (Exception e) {
            throw new JspException(e);
        }
        return EVAL_PAGE;
    }
}