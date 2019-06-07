<template>
  <div id="content" v-bind:class="{openContent: isOpen, closeContent: !isOpen}">


    <b-button @click="isOpen=!isOpen" class="menu-btn" style="z-index:1000; position:fixed; ">
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
          <b-card-text v-html="user.profile"  >
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

  .menu-btn, .menu-btn:focus, .menu-btn:hover, #content .menu-btn:active {
    color: #000;
    margin: 10px;
    font-size: 30px;
    background: rgba(255, 255, 255, .5);
    border: none;
    border-radius: 10%;
    box-shadow: none;
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

  .nav a.item {
    display: flex;
    flex-direction: row;
    padding: 18px 30px;
    font-size: 30px;
    text-decoration: none;
    color: #ccc;
    background-color: #3d3d3d;
    border-top: 1px solid #ccc;
  }

  .nav .last {
    border-bottom: 1px solid #ccc;
  }

  .nav a.item:hover {
    color: #fff;
    background-color: #cf0000;
    transition: 0.4s;
  }

  .nav .icon {
    padding-right: 10px;
    font-size: 35px;
  }

  .nav ul {
    display: flex;
    position: absolute;
    width: 100%;
    margin-top: 50px;
  }

  .nav ul li {
    list-style: none;
  }

  .nav ul li a.inline {
    font-size: 26px;
    color: #ccc;
    padding: 6px 5px 3px;
  }

  .nav ul li a.inline:hover {
    color: #cf0000;
  }

.profile{
  border:none;
}
</style>
