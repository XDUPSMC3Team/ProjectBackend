(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0129f1aa"],{"2bd1":function(t,e,s){"use strict";var i=s("978b"),a=s.n(i);a.a},"520b":function(t,e,s){"use strict";s.r(e);var i=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"p10"},[0===t.detail.stepNum?s("div",{staticClass:"allMidBox cancelIcon c_error"},[s("i",{staticClass:"icon el-icon-error"}),s("p",{staticClass:"mt10 c2 t2 l3"},[t._v("Order Canceled")])]):t._e(),s("div",{staticClass:"p30 stepBox"},[s("el-row",{attrs:{type:"flex",justify:"center"}},[t.detail.stepNum>=1&&t.detail.stepNum<4?s("el-col",{attrs:{span:24}},[s("el-steps",{attrs:{"align-center":!0,active:t.detail.stepNum,"finish-status":"success"}},[s("el-step",{attrs:{title:"Preparing"}}),s("el-step",{attrs:{title:"Shipped"}}),s("el-step",{attrs:{title:"Sending"}}),s("el-step",{attrs:{title:"Complete"}})],1)],1):t._e(),t.detail.stepNum>=4&&t.detail.stepNum<=5?s("el-col",{attrs:{span:24}},[s("el-steps",{attrs:{"align-center":!0,active:t.detail.stepNum-3,"finish-status":"success"}},[s("el-step",{attrs:{title:"Complete"}}),s("el-step",{attrs:{title:"Comment"}})],1)],1):t._e(),t.detail.stepNum>=6&&t.detail.stepNum<=7?s("el-col",{attrs:{span:24}},[s("el-steps",{attrs:{"align-center":!0,active:t.detail.stepNum-5,"finish-status":"success"}},[s("el-step",{attrs:{title:"Return"}}),s("el-step",{attrs:{title:"Return Complete"}})],1)],1):t._e()],1)],1),s("el-row",{attrs:{gutter:40}},[s("el-col",{attrs:{xs:24,sm:24,lg:12,md:24}},[s("lines",{staticClass:"mb20 mt20",attrs:{type:8,title:"Receiver Info"}}),s("div",{staticClass:"infoBox p20"},[s("p",{staticClass:"t3 l3 c1 border mb15 ",on:{click:t.goMy}},[t._v("Name: "),s("span",{staticClass:"l2 pl20 name"},[t._v(t._s(t.detail.receiverName))])]),s("p",{staticClass:"t3 l3 c1 border mb15"},[t._v("Address: "),s("span",{staticClass:"l2 pl20"},[t._v(t._s(t.detail.address))])]),s("p",{staticClass:"t3 l3 c1 border mb15"},[t._v("Total: "),s("span",{staticClass:"l2 pl20 c_like"},[t._v("$ "+t._s(t.detail.money))])]),s("p",{staticClass:"t3 l3 c1 border mb15"},[t._v("Create Time: "),s("span",{staticClass:"l2 pl20"},[t._v(t._s(t.detail.createTime))])]),s("p",{staticClass:"t3 l3 c1 border mb15"},[t._v("Update Time: "),s("span",{staticClass:"l2 pl20"},[t._v(t._s(t.detail.updateTime))])]),s("p",{staticClass:"t3 l3 c1 border mb15"},[t._v("Order Code: "),s("span",{staticClass:"l2 pl20"},[t._v(t._s(t.detail.id))])]),t.detail.receiveTime?s("p",{staticClass:"t3 l3 c1 border mb15"},[t._v("Receive Time: "),s("span",{staticClass:"l2 pl20"},[t._v(t._s(t.detail.receiveTime))])]):t._e()])],1),s("el-col",{attrs:{xs:24,sm:24,lg:12,md:24}},[s("lines",{staticClass:"mb20 mt20",attrs:{type:4,title:"Product Info"}}),s("div",{staticClass:"infoBox p20"},[s("p",{staticClass:"t3 l3 c1 border mb15",on:{click:t.goShop}},[t._v("Shop Name: "),s("span",{staticClass:"l2 pl20 shop"},[t._v(t._s(t.detail.shopName))])]),s("el-row",t._l(t.detail.orderDetailList,function(e){return s("el-col",{key:e.productId,attrs:{span:24}},[s("p",{staticClass:"t2 l1 c1 border mb15 rowBox"},[s("span",{on:{click:function(s){t.goProduct(e.productId)}}},[t._v(t._s(e.productName))]),s("span",{staticClass:"pl20 pr40"},[t._v("*")]),s("span",{staticClass:"pr40"},[t._v(t._s(e.amount))]),s("span",{staticClass:"l2 c_like t_end"},[t._v("$ "+t._s(e.price))]),3!==t.detail.status||e.commentContent?t._e():s("el-button",{staticClass:"ml20",attrs:{type:"primary",size:"small"},on:{click:function(s){t.comment(e.orderDetailId)}}},[t._v("Comment")]),e.commentContent?s("span",{staticClass:"ml20 t2 l3 c3"},[t._v(t._s(e.commentContent))]):t._e()],1)])}))],1)],1)],1),s("div",{staticClass:"allMidBox"},[s("el-row",{staticClass:"mt20",attrs:{gutter:40,type:"flex",justify:"center"}},[2===t.detail.status?s("el-col",{attrs:{span:24}},[s("el-button",{attrs:{type:"primary"},on:{click:t.confirm}},[t._v("Confirm Received")])],1):t._e(),3===t.detail.status||4===t.detail.status?s("el-col",{attrs:{span:12}},[s("el-button",{attrs:{type:"warning"},on:{click:t.sendback}},[t._v("SendBack")])],1):t._e()],1)],1),s("el-dialog",{attrs:{title:"Comment",visible:t.dialogTableVisible},on:{"update:visible":function(e){t.dialogTableVisible=e}}},[s("el-input",{attrs:{type:"text"},model:{value:t.content,callback:function(e){t.content=e},expression:"content"}}),s("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{on:{click:function(e){t.dialogTableVisible=!1,t.content=""}}},[t._v("取 消")]),s("el-button",{attrs:{type:"primary"},on:{click:t.commentConfirm}},[t._v("确 定")])],1)],1)],1)},a=[],l=s("585f"),n={name:"OrderDetail",components:{lines:l["a"]},props:{},data:function(){return{content:"",dialogTableVisible:!1,orderDetailId:0}},methods:{sendback:function(){},comment:function(t){this.orderDetailId=t,this.dialogTableVisible=!0},commentConfirm:function(){var t=this;this.$store.dispatch("orderComment",{orderDetailId:this.orderDetailId,content:this.content}).then(function(){t.$store.dispatch("orderGetOrderDetail",t.$route.query.id),t.$success("Comment Success"),t.dialogTableVisible=!1})},confirm:function(){var t=this;this.$store.dispatch("orderConfirm",this.detail.id).then(function(){t.$store.dispatch("orderGetOrderDetail",t.$route.query.id),t.$success("Confirm Recevied Success")})},goShop:function(){this.$router.push({name:"shop",query:{id:this.detail.shopId}})},goMy:function(){this.$router.push("/my")},goProduct:function(t){this.$router.push({name:"detail",query:{id:t}})}},created:function(){this.$store.dispatch("orderGetOrderDetail",this.$route.query.id)},mounted:function(){},computed:{detail:function(){return this.$store.state.order.showOrderDetail}}},r=n,o=(s("b5b0"),s("0d30")),c=Object(o["a"])(r,i,a,!1,null,"7adeeefd",null);c.options.__file="OrderDetail.vue";e["default"]=c.exports},"585f":function(t,e,s){"use strict";var i=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[s("div",{staticClass:"lines"},[s("p",[s("span",{staticClass:"tit-icon tit-icon-l ",class:"icon-"+t.type+"-l"}),s("span",{staticClass:"title c1 t2 l2"},[t._v(t._s(t.title))]),s("span",{staticClass:"tit-icon tit-icon-r",class:"icon-"+t.type+"-r"})])])])},a=[],l=(s("089e"),{props:{title:{type:String,default:"null"},type:Number}}),n=l,r=(s("2bd1"),s("0d30")),o=Object(r["a"])(n,i,a,!1,null,"30e32d39",null);o.options.__file="lines.vue";e["a"]=o.exports},"615d":function(t,e,s){},"978b":function(t,e,s){},b5b0:function(t,e,s){"use strict";var i=s("615d"),a=s.n(i);a.a}}]);
//# sourceMappingURL=chunk-0129f1aa.c6cb078d.js.map