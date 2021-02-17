<style scoped>
* {
    box-sizing: border-box;
}
ul {
  margin: 0;
  padding: 0;
  width: 75%;
}
ul li {
  cursor: pointer;
  position: relative;
  padding: 8px 8px 8px 40px;
  background: #eee;
  font-size: 14px;
}
ul li:hover {
  background: #ddd;
}
.close {
  position: absolute;
  right: 0;
  top: 0;
  padding: 12px 16px 12px 16px;
}
.close:hover {
  background-color: #F44336;
  color: white;
}
</style>
<template id="list-template">
  <ul>
    <li
        v-for="value in commentlist"
        :key="value.cmtSeq"
    >
      <span>{{ value.cmt }}</span>
      <span class="close" v-on:click.stop="deletecomment(value.cmtSeq)">&#x00D7;</span>
    </li>
  </ul>
</template> 
<script>
import eventBus from '../../../EventBus'
export default {
    name: "CommentList",
    data: function () {
        return {
          commentlist:[]
        };
    },
    // create와 mounted?????????????????????????????????????????????????????????????????????????????????????????????????
    created: function () {
        eventBus.$on('add-comment', this.addcomment);
    },

    methods: {
      showComment: function() {
          this.axios.get('/getcommentlist', {
            params: {
              trackSeq: this.$route.params.track.trackSeq
            }
          }).then(response => {
            console.log(response)
            this.commentlist = response.data
          }).catch(error => {
              console.log("에러" + error);
          })
      },
      addcomment: function (cmt) {
        if (cmt !== "") {
          // 입력 값 list에 추가
            this.commentlist.push({
              cmt: cmt,
            userId:'',
            cmtLike: 0,
            parentId:'',
            depth: 0,
            trackSeq: this.$route.params.track
        });
              console.log("---------------test--------------------")
              console.log(this.commentlist)
              console.log(this.commentlist[this.commentlist.length-1])
              console.log("----------------test-------------------")

          // server 통신부
          this.axios.post('/insertcomment', 
          this.commentlist[this.commentlist.length-1]
          ).then(response => {
              console.log("==========add==========")
              console.warn(response.data)
              console.log("==========add==========")
          }).catch((ex) => {
              console.warn("ERROR!!!!! : ",ex)
          });
        }
      },
      
      deletecomment: function (id) {
        console.log(id)
          var index = this.commentlist.findIndex(function (item) {
              return item.cmtSeq === id;
          });
          console.log("이곳은 삭제입니다.")
          console.log(id)
          this.axios.delete('/deletecomment',  {
                params: {
                    cmtSeq: id
                }
           }).then(response => {
                console.log("delete(then)")
                console.log(response)
                this.commentlist.splice(index, 1); //(start, 삭제하고자 하는 개수)
            }).catch((ex) => {
                console.warn("ERROR!!!!! : ",ex)
            });

      },
    },

    mounted(){
      this.showComment()
    }
};
</script>