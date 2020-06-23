package com.inventec.entity;

import android.test.suitebuilder.annotation.MediumTest;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 21737367 on 2018/10/20.
 */

public class pos_shopinfo extends LitePalSupport {
    public pos_shopinfo(String shopid, String shopname) {
        this.shopid = shopid;
        this.shopname = shopname;
    }

    private String shopid;
    private String shopname;
    private String companyid;
    private String shopbusinessname;
    private String shopoutsideid;
    private String businesslicensenumber;
    private boolean shopstatus;
    private int shopproperties;
    private int shoptype;
    private String agentlevel;
    private int shoplevel;
    private String sourcesupply;
    private String contact;
    private String contactphone;
    private String shopphone;
    private int expectachievetime;
    private String serviceradius;
    private String servicearea;
    private BigDecimal begindistfee;
    private BigDecimal distributefee;
    private String workdaysbusinesstime;
    private String holidaybusinesstime;
    private String orderdaterange;
    private boolean enableelectbusiness;
    private boolean enabletakout;
    private boolean enablebooking;
    private boolean enablescan;
    private boolean enablebookingonline;
    private boolean enableelectmembercard;
    private boolean enablerealcardbind;
    private BigDecimal outgoingamount;
    private BigDecimal deliverfee;
    private BigDecimal freedeliveramount;
    private int orderdelivermethod;
    private int takeoutdelivermethod;
    private boolean iselectinvoice;
    private int safeamount;
    private int warnamount;
    private String shopnotice;
    private String promotioninfo;
    private String shoplogo;
    private String shopvista;
    private String shophomepage;
    private String shopqrcode;
    private Date modifydate;
    private String shopdistricts;
    private String shopstreetaddress;
    private Date createdate;
    private String shoppoint;
    private int configsync;
    private int menusync;

    private String mediumtext;
}
