(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5cd75c4e"],{2641:function(t,e,i){"use strict";var s=i("f0bb"),a=i.n(s);a.a},"2bd1":function(t,e,i){"use strict";var s=i("978b"),a=i.n(s);a.a},"585f":function(t,e,i){"use strict";var s=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("div",{staticClass:"lines"},[i("p",[i("span",{staticClass:"tit-icon tit-icon-l ",class:"icon-"+t.type+"-l"}),i("span",{staticClass:"title c1 t2 l2"},[t._v(t._s(t.title))]),i("span",{staticClass:"tit-icon tit-icon-r",class:"icon-"+t.type+"-r"})])])])},a=[],n=(i("089e"),{props:{title:{type:String,default:"null"},type:Number}}),o=n,c=(i("2bd1"),i("0d30")),l=Object(c["a"])(o,s,a,!1,null,"30e32d39",null);l.options.__file="lines.vue";e["a"]=l.exports},"978b":function(t,e,i){},ce2c:function(t,e,i){"use strict";i.r(e);var s=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"p20 root"},[s("el-container",{staticClass:"root-banner hidden-md-and-down"},[s("el-aside",{staticClass:"aside p10"},[s("div",{staticClass:"allMidBox logo",on:{click:function(e){t.isMenuOn=!1}}},[s("img",{staticClass:"logo-img",attrs:{src:i("cf05"),alt:""}})]),s("el-menu",{staticClass:"aside-menu",attrs:{"default-active":"1"}},[s("el-menu-item",{staticClass:"aside-menu-item",attrs:{index:"1"},on:{click:function(e){t.clickMenu("1")}}},[t._v("\n            Cell Phones / Computers / Tablets >\n          ")]),s("el-menu-item",{staticClass:"aside-menu-item",attrs:{index:"2"},on:{click:function(e){t.clickMenu("2")}}},[t._v("\n            Cameras / TV / Home Theater >\n          ")]),s("el-menu-item",{staticClass:"aside-menu-item",attrs:{index:"3"},on:{click:function(e){t.clickMenu("3")}}},[t._v("\n            Office / Home / Car Electronics >\n          ")]),s("el-menu-item",{staticClass:"aside-menu-item",attrs:{index:"4"},on:{click:function(e){t.clickMenu("4")}}},[t._v("\n            Audio / Video / Games >\n          ")])],1)],1),s("el-main",{staticClass:"main"},[t.isMenuOn?t._e():s("el-carousel",{attrs:{interval:6e3,height:"300px"}},t._l(t.bannerProducts,function(e){return s("el-carousel-item",{key:e.id},[s("div",{staticClass:"main-item",style:"background-image:url("+e.pic+");",on:{click:function(i){t.goProductInfo(e.id)}}},[s("div",{staticClass:"main-item-cont allMidBox"},[s("p",{staticClass:"name"},[t._v(t._s(e.name))])])])])})),t.isMenuOn?s("div",{staticClass:"subMenu p20"},[s("el-row",{attrs:{gutter:30}},t._l(t.menuMap[t.menuIndex],function(e){return s("el-col",{key:e.id,attrs:{span:8}},[s("div",{staticClass:"subMenu-item allMidBox p10",on:{click:function(i){t.goCate(e.id)}}},[s("i",{staticClass:"iconfont menuIcon",class:e.icon}),s("p",{staticClass:"t1 l2 c2"},[t._v(t._s(e.name))])])])}))],1):t._e(),s("el-footer",{staticClass:"footer"},[s("p",{staticClass:"c1 l1 t1 mt10 mb10 t_center"},[t._v("Recommend Shops")]),s("el-row",{attrs:{gutter:20}},t._l(t.bannerShops,function(e){return s("el-col",{key:e.id,staticClass:"shopItem",attrs:{span:6}},[s("div",{staticClass:"allMidBox m10",on:{click:function(i){t.goShopInfo(e.id)}}},[s("p",{staticClass:"t1 c_info l2"},[t._v(t._s(e.shopName))]),s("p",{staticClass:"t4 c3 l3"},[t._v("\n                "+t._s(e.shopDesc)+"\n              ")])])])}))],1)],1)],1),s("lines",{staticClass:"mt30 mb30",attrs:{type:1,title:"Cell Phones"}}),s("el-row",{attrs:{gutter:20}},[t._l(t.categoryProducts["CellPhones"]||[],function(t){return s("el-col",{key:t.id,attrs:{xs:24,sm:24,lg:6,xl:6,md:6}},[s("product-card",{attrs:{title:t.name,id:t.id,picUrl:t.pic,intro:t.description,time:t.updateTime,type:"bg"}})],1)}),0===t.categoryProducts["CellPhones"].length?s("el-col",{staticClass:"allMidBox",attrs:{span:24}},[s("i",{staticClass:"iconfont icon-baoguofahuo-xianxing mt10 mb20 c3 t1"}),s("p",{staticClass:"l3 c3 t2"},[t._v("Empty Category")])]):t._e()],2),s("lines",{attrs:{type:2,title:"Computers"}}),s("el-row",{attrs:{gutter:20}},[t._l(t.categoryProducts["Computers"]||[],function(t){return s("el-col",{key:t.id,attrs:{xs:24,sm:24,lg:6,xl:6,md:6}},[s("product-card",{attrs:{title:t.name,id:t.id,picUrl:t.pic,intro:t.description,time:t.updateTime,type:"big"}})],1)}),0===t.categoryProducts["Computers"].length?s("el-col",{staticClass:"allMidBox",attrs:{span:24}},[s("i",{staticClass:"iconfont icon-baoguofahuo-xianxing mt10 mb20 c3 t1"}),s("p",{staticClass:"l3 c3 t2"},[t._v("Empty Category")])]):t._e()],2),s("lines",{staticClass:"mt30 mb30",attrs:{type:3,title:"Tablets "}}),s("el-row",{attrs:{gutter:20}},[t._l(t.categoryProducts["Tablets"]||[],function(t){return s("el-col",{key:t.id,attrs:{xs:24,sm:24,lg:6,xl:6,md:6}},[s("product-card",{attrs:{title:t.name,id:t.id,picUrl:t.pic,intro:t.description,time:t.updateTime,type:"big"}})],1)}),0===t.categoryProducts["Tablets"].length?s("el-col",{staticClass:"allMidBox",attrs:{span:24}},[s("i",{staticClass:"iconfont icon-baoguofahuo-xianxing mt10 mb20 c3 t1"}),s("p",{staticClass:"l3 c3 t2"},[t._v("Empty Category")])]):t._e()],2),s("lines",{staticClass:"mt30 mb30",attrs:{type:4,title:"Cameras"}}),s("el-row",{attrs:{gutter:20}},[t._l(t.categoryProducts["Cameras"]||[],function(t){return s("el-col",{key:t.id,attrs:{xs:24,sm:24,lg:6,xl:6,md:6}},[s("product-card",{attrs:{title:t.name,id:t.id,picUrl:t.pic,intro:t.description,time:t.updateTime,type:"top"}})],1)}),0===t.categoryProducts["Cameras"].length?s("el-col",{staticClass:"allMidBox",attrs:{span:24}},[s("i",{staticClass:"iconfont icon-baoguofahuo-xianxing mt10 mb20 c3 t1"}),s("p",{staticClass:"l3 c3 t2"},[t._v("Empty Category")])]):t._e()],2),s("lines",{staticClass:"mt30 mb30",attrs:{type:5,title:"Games"}}),s("el-row",{attrs:{gutter:20}},[t._l(t.categoryProducts["Games"]||[],function(t){return s("el-col",{key:t.id,attrs:{xs:24,sm:24,lg:6,xl:6,md:6}},[s("product",{attrs:{name:t.name,shopId:t.shopId,productId:t.id,pic:t.pic,description:t.description,updateTime:t.updateTime,attributeList:t.attributeList}})],1)}),0===t.categoryProducts["Games"].length?s("el-col",{staticClass:"allMidBox",attrs:{span:24}},[s("i",{staticClass:"iconfont icon-baoguofahuo-xianxing mt10 mb20 c3 t1"}),s("p",{staticClass:"l3 c3 t2"},[t._v("Empty Category")])]):t._e()],2),s("lines",{staticClass:"mt30 mb30",attrs:{type:6,title:"TV"}}),s("el-row",{attrs:{gutter:20}},[t._l(t.categoryProducts["TV"]||[],function(t){return s("el-col",{key:t.id,attrs:{xs:24,sm:24,lg:6,xl:6,md:6}},[s("product",{attrs:{name:t.name,shopId:t.shopId,productId:t.id,pic:t.pic,description:t.description,updateTime:t.updateTime,attributeList:t.attributeList}})],1)}),0===t.categoryProducts["TV"].length?s("el-col",{staticClass:"allMidBox",attrs:{span:24}},[s("i",{staticClass:"iconfont icon-baoguofahuo-xianxing mt10 mb20 c3 t1"}),s("p",{staticClass:"l3 c3 t2"},[t._v("Empty Category")])]):t._e()],2),s("DFooter")],1)},a=[],n=i("6e39"),o=(i("f089"),i("0aca"),i("232a"),i("585f")),c=i("bfbf"),l=i("bd46"),r=i("076e"),u=i("0ebb"),d={name:"root",components:{lines:o["a"],productCard:c["a"],product:l["a"],DFooter:r["a"]},data:function(){return{isMenuOn:!1,menuIndex:0,menuMap:{1:[{id:3,name:"Cell Phones",icon:"icon-phone"},{id:5,name:"Computers",icon:"icon-computer"},{id:6,name:"Tablets",icon:"icon-pingbandiannao"}],2:[{id:4,name:"Cameras",icon:"icon-camera"},{id:1,name:"TV",icon:"icon-tv"},{id:2,name:"Home Theater",icon:"icon-video"}],3:[{id:12,name:"Office",icon:"icon-office"},{id:11,name:"Home",icon:"icon-home"},{id:8,name:"Car Electronics",icon:"icon-car"}],4:[{id:7,name:"Audio",icon:"icon-audio"},{id:9,name:"Video",icon:"icon-dianyingyuan"},{id:10,name:"Games",icon:"icon-videogames"}]}}},props:{},computed:Object(n["a"])({},Object(u["c"])({bannerProducts:function(t){return t.root.bannerProducts},bannerShops:function(t){return t.root.bannerShops},categoryProducts:function(t){return t.root.categoryProducts}})),created:function(){this.$store.dispatch("rootGetBannerProducts"),this.$store.dispatch("rootGetCategories")},mounted:function(){},methods:{clickMenu:function(t){this.isMenuOn=!0,this.menuIndex=t},goProductInfo:function(t){this.$router.push({name:"detail",query:{id:t}})},goShopInfo:function(t){this.$router.push({name:"shop",query:{id:t}})},goCate:function(t){this.$router.push({name:"search",query:{categoryId:t}})}}},m=d,p=(i("2641"),i("0d30")),C=Object(p["a"])(m,s,a,!1,null,"0dbe5bac",null);C.options.__file="Root.vue";e["default"]=C.exports},f0bb:function(t,e,i){}}]);
//# sourceMappingURL=chunk-5cd75c4e.e99bb48a.js.map