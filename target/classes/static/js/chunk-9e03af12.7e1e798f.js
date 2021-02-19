(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-9e03af12","chunk-2d0aad94"],{1384:function(t,e,n){"use strict";n.r(e);var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"projectTracking"},[n("h1",[t._v("This is projectTracking page")]),n("TrackingList",{attrs:{msg:"리스트 출력"}})],1)},c=[],a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"TrackingList"},[n("router-link",{attrs:{tag:"a",to:"/projectTrackingInsert"}},[t._v("이동")]),n("h4",[t._v(t._s(t.msg))]),n("table",{attrs:{border:"1"}},[t._m(0),t._l(t.trackings,(function(e,o){return n("tr",{key:o},[n("router-link",{attrs:{tag:"td",to:{name:"ProjectTrackingDetail",params:{track:e}}}},[t._v(t._s(e.project.pjTitle))]),n("td",[t._v(t._s(e.project.pjCategory))]),n("td",[t._v(t._s(e.project.pjDuration))]),n("td",[t._v(t._s(e.trackImage))])],1)}))],2)],1)},r=[function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("tr",[n("th",[t._v("제목")]),n("th",[t._v("카테고리")]),n("th",[t._v("기간")]),n("th",[t._v("이미지")])])}],s={name:"TrackingList",props:{msg:String},data:function(){return{trackings:[]}},methods:{trackList:function(){var t=this;this.axios.get("/gettrackinglistaxios").then((function(e){t.trackings=e.data,console.log("==========list=========="),console.log(e),console.log(t.trackings),console.log("==========list==========")})).catch((function(t){console.log("에러"+t)}))}},mounted:function(){this.trackList()}},i=s,l=n("2877"),u=Object(l["a"])(i,a,r,!1,null,null,null),d=u.exports,m={name:"ProjectTracking",components:{TrackingList:d}},f=m,g=Object(l["a"])(f,o,c,!1,null,null,null);e["default"]=g.exports},"27db":function(t,e,n){"use strict";n("730d")},"730d":function(t,e,n){},"7b5d":function(t,e,n){"use strict";n.r(e);var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"projectTrackingDetail"},[n("tracking-detail"),n("comment")],1)},c=[],a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"TrackingDetail"},[n("h2",[t._v("Tracking Detail")]),n("center",[n("table",{attrs:{border:"1"}},[n("tr",[n("td",[t._v("Title")]),n("td",[t._v(t._s(t.trackInfo.project.pjTitle))])]),n("tr",[n("td",[t._v("Duration")]),n("td",[t._v(t._s(t.trackInfo.project.pjDuration))])]),n("tr",[n("td",[t._v("Tools")]),n("td",[t._v(t._s(t.trackInfo.project.pjTools))])]),n("tr",[n("td",[t._v("Category")]),n("td",[t._v(t._s(t.trackInfo.project.pjCategory))])]),n("tr",[n("td",[t._v("Language")]),n("td",[t._v(t._s(t.trackInfo.project.pjLang))])]),n("tr",[n("td",[t._v("DBMS")]),n("td",[t._v(t._s(t.trackInfo.project.pjDbms))])]),n("tr",[n("td",[t._v("Description")]),n("td",[t._v(t._s(t.trackInfo.project.pjDescription))])]),n("tr",[n("td",[t._v("Stage")]),n("td",[t._v(t._s(t.trackInfo.stage))])]),n("tr",[n("td",[t._v("Issue")]),n("td",[t._v(t._s(t.trackInfo.issue))])]),n("tr",[n("td",[t._v("Output")]),n("td",[t._v(t._s(t.trackInfo.output))])]),n("tr",[n("td",[t._v("Image")]),n("td",[t._v(t._s(t.trackInfo.trackImage))])])]),n("br"),n("button",{on:{click:t.deleteTracking}},[t._v("삭제")]),n("router-link",{attrs:{tag:"button",to:{name:"ProjectTrackingUpdate",params:{trackSeq:t.trackInfo}}}},[t._v("수정")])],1)],1)},r=[],s=n("1384"),i={name:"TrackingDetail",data:function(){return{trackInfo:[]}},methods:{tracking:function(){var t=this;console.log("이곳은 detail처음"),this.axios.get("/getprojecttracking",this.trackInfo.trackSeq).then((function(e){t.trackInfo=e.data,console.log("--------detail---------"),console.log(e),console.log(t.trackInfo),console.log("--------detail---------")})).catch((function(t){console.log("에러"+t)}))},deleteTracking:function(){var t=this;console.log("1"),this.axios.delete("/deleteprojecttracking",{params:{trackSeq:this.trackInfo.trackSeq}}).then((function(e){console.log("2"),console.log(e),t.$router.push(s["default"])})).catch((function(t){console.warn("ERROR!!!!! : ",t)}))}},mounted:function(){this.trackInfo=this.$route.params.track,this.tracking()}},l=i,u=n("2877"),d=Object(u["a"])(l,a,r,!1,null,null,null),m=d.exports,f=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("div",{staticClass:"header",attrs:{id:"header"}},[n("comment-input")],1),n("commentList")],1)},g=[],h=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"commentInput"},[n("hr"),n("h2",[t._v("Comment")]),n("center",[n("input",{directives:[{name:"model",rawName:"v-model",value:t.cmt,expression:"cmt"}],staticClass:"input",attrs:{type:"text",id:"task",placeholder:"댓글을 입력하세요"},domProps:{value:t.cmt},on:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.addcomment(e)},input:function(e){e.target.composing||(t.cmt=e.target.value)}}}),n("span",{staticClass:"addbutton",on:{click:t.addcomment}},[t._v("작성")])])],1)},p=[],k=n("2b0e"),_=new k["a"],v=_,j={name:"CommentInput",data:function(){return{cmt:""}},methods:{addcomment:function(){v.$emit("add-comment",this.cmt),this.cmt=""}}},I=j,b=(n("a7df"),Object(u["a"])(I,h,p,!1,null,"353fa82f",null)),T=b.exports,x=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("ul",t._l(t.commentlist,(function(e){return n("li",{key:e.cmtSeq},[n("span",[t._v(t._s(e.cmt))]),n("span",{staticClass:"close",on:{click:function(n){return n.stopPropagation(),t.deletecomment(e.cmtSeq)}}},[t._v("×")])])})),0)},C=[],S=(n("c740"),n("a434"),{name:"CommentList",data:function(){return{commentlist:[]}},created:function(){v.$on("add-comment",this.addcomment)},methods:{showComment:function(){var t=this;this.axios.get("/getcommentlist",{params:{trackSeq:this.$route.params.track.trackSeq}}).then((function(e){console.log(e),t.commentlist=e.data})).catch((function(t){console.log("에러"+t)}))},addcomment:function(t){""!==t&&(this.commentlist.push({cmt:t,userId:"",cmtLike:0,parentId:"",depth:0,trackSeq:this.$route.params.track}),console.log("---------------test--------------------"),console.log(this.commentlist),console.log(this.commentlist[this.commentlist.length-1]),console.log("----------------test-------------------"),this.axios.post("/insertcomment",this.commentlist[this.commentlist.length-1]).then((function(t){console.log("==========add=========="),console.warn(t.data),console.log("==========add==========")})).catch((function(t){console.warn("ERROR!!!!! : ",t)})))},deletecomment:function(t){var e=this;console.log(t);var n=this.commentlist.findIndex((function(e){return e.cmtSeq===t}));console.log("이곳은 삭제입니다."),console.log(t),this.axios.delete("/deletecomment",{params:{cmtSeq:t}}).then((function(t){console.log("delete(then)"),console.log(t),e.commentlist.splice(n,1)})).catch((function(t){console.warn("ERROR!!!!! : ",t)}))}},mounted:function(){this.showComment()}}),w=S,y=(n("27db"),Object(u["a"])(w,x,C,!1,null,"140e1ec0",null)),D=y.exports,E={name:"Comment",components:{CommentInput:T,CommentList:D}},$=E,O=(n("f4bd"),Object(u["a"])($,f,g,!1,null,"396f12e0",null)),L=O.exports,q={name:"ProjectTrackingDetail",components:{TrackingDetail:m,Comment:L}},R=q,P=Object(u["a"])(R,o,c,!1,null,null,null);e["default"]=P.exports},a434:function(t,e,n){"use strict";var o=n("23e7"),c=n("23cb"),a=n("a691"),r=n("50c4"),s=n("7b0b"),i=n("65f0"),l=n("8418"),u=n("1dde"),d=n("ae40"),m=u("splice"),f=d("splice",{ACCESSORS:!0,0:0,1:2}),g=Math.max,h=Math.min,p=9007199254740991,k="Maximum allowed length exceeded";o({target:"Array",proto:!0,forced:!m||!f},{splice:function(t,e){var n,o,u,d,m,f,_=s(this),v=r(_.length),j=c(t,v),I=arguments.length;if(0===I?n=o=0:1===I?(n=0,o=v-j):(n=I-2,o=h(g(a(e),0),v-j)),v+n-o>p)throw TypeError(k);for(u=i(_,o),d=0;d<o;d++)m=j+d,m in _&&l(u,d,_[m]);if(u.length=o,n<o){for(d=j;d<v-o;d++)m=d+o,f=d+n,m in _?_[f]=_[m]:delete _[f];for(d=v;d>v-o+n;d--)delete _[d-1]}else if(n>o)for(d=v-o;d>j;d--)m=d+o-1,f=d+n-1,m in _?_[f]=_[m]:delete _[f];for(d=0;d<n;d++)_[d+j]=arguments[d+2];return _.length=v-o+n,u}})},a7df:function(t,e,n){"use strict";n("ad96")},ad96:function(t,e,n){},b0dd:function(t,e,n){},c740:function(t,e,n){"use strict";var o=n("23e7"),c=n("b727").findIndex,a=n("44d2"),r=n("ae40"),s="findIndex",i=!0,l=r(s);s in[]&&Array(1)[s]((function(){i=!1})),o({target:"Array",proto:!0,forced:i||!l},{findIndex:function(t){return c(this,t,arguments.length>1?arguments[1]:void 0)}}),a(s)},f4bd:function(t,e,n){"use strict";n("b0dd")}}]);
//# sourceMappingURL=chunk-9e03af12.7e1e798f.js.map