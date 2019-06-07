<template>
  <div id="content" v-bind:class="{openContent: isOpen, closeContent: !isOpen}">


    <b-button @click="isOpen=!isOpen" class="icon-btn menu-btn"  >
      <font-awesome-icon icon="bars"/>
    </b-button>


    <div id="menu" class="nav boring" v-bind:class="{openMenu: isOpen, closeMenu: !isOpen}">
    <VuePerfectScrollbar class="scroll-area" :settings="settings">
        <b-card
          :title="user.username"
          :img-src="'data:;base64,'+user.photo"
          img-top
          tag="article"
          class="profile"
          style="text-align:center"
        >
          <b-card-text v-html="user.profile"
                       style="padding: 0"
                       class=" ck ck-content ck-editor__editable ck-rounded-corners ck-editor__editable_inline ck-blurred">
          </b-card-text>
        </b-card>

    </VuePerfectScrollbar>
    </div>

  </div>
</template>

<script>
  import VuePerfectScrollbar from 'vue-perfect-scrollbar'
  export default {
    name: "SideMenu",
    components:{
      VuePerfectScrollbar
    },
    props: ['user'],
    data: function () {
      return {
        isOpen: false,
        imgurl: '',
        username:'',
        profile:'',
        settings: {
          maxScrollbarLength: 60
        }
      };
    },
    created() {
      this.username=this.user.username;

    }
  }
</script>

<style scoped>
  .scroll-area {
    position: relative;

    height: 100%;
    padding:15px;
  }
  #menu {
    width: 300px;
    height: 100%;
    left: -300px;

  }

  .boring.nav {
    -webkit-transform: translateX(0);
    transform: translateX(0);
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
    z-index: 1;
    -webkit-transition: all 300ms cubic-bezier(1, 0, .61, .15);
    transition: all 300ms cubic-bezier(1, 0, .61, .15);


  }

  .openMenu.boring.nav {
    -webkit-transition: all 300ms cubic-bezier(1, 0, .61, .15);
    transition: all 300ms cubic-bezier(1, 0, .61, .15);

    -webkit-transform: translateX(300px);
    transform: translateX(300px);

    -webkit-transform: translate3d(300px, 0, 0);
    transform: translate3d(300px, 0, 0);
  }


  #content {
    box-shadow: inset -5px 0 5px -5px rgba(0, 0, 0, 0.2);
    overflow: hidden;
    width: 100%;
    background-color: white;
  }




  .nav {
    height: 100%;
    display: block;
    position: fixed;
    /*z-index: 1;*/
    /*top: 0;*/
    /*left: -2px;*/
    background-color: white;
    overflow-x: hidden;
    /*transition: 0.7s;*/
    border-right: 2px solid lightgrey;
  }



.profile{
  border:none;
}
</style>
