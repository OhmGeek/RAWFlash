<template>
  <div class="area">
    <nav class="main-menu">
      <ul>
        <sbtitle text="example">
        </sbtitle>
      </ul>
    </nav>
  </div>
</template>

<script>
import SidebarButton from '../components/SidebarButton.vue'
import SidebarTitle from '../components/SidebarTitle.vue'
export default {
  name: 'sidebar',
  components: {
    "sbbtn": SidebarButton,
    "sbtitle": SidebarTitle
  },
  data() {
    return {
      example_text: "Brightness",
    };
  },
  props: ['items']
}

</script>
<style scoped>
  .main-menu:hover,nav.main-menu.expanded {
    width:250px;
    overflow:visible;
  }

  .main-menu {
    background:#fbfbfb;
    border-right:1px solid #e5e5e5;
    position:absolute;
    top:0;
    bottom:0;
    height:100%;
    left:0;
    width:60px;
    overflow:hidden;
    -webkit-transition:width .05s linear;
    transition:width .05s linear;
    -webkit-transform:translateZ(0) scale(1,1);
    z-index:1000;
  }



  .main-menu>ul.logout {
    position:absolute;
    left:0;
    bottom:0;
  }

  .no-touch .scrollable.hover {
    overflow-y:hidden;
  }

  .no-touch .scrollable.hover:hover {
    overflow-y:auto;
    overflow:visible;
  }

  a:hover,a:focus {
    text-decoration:none;
  }

  nav {
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    -o-user-select:none;
    user-select:none;
  }

  nav ul,nav li {
    outline:0;
    margin:0;
    padding:0;
  }
  .main-menu li:hover>a,nav.main-menu li.active>a,.dropdown-menu>li>a:hover,.dropdown-menu>li>a:focus,.dropdown-menu>.active>a,.dropdown-menu>.active>a:hover,.dropdown-menu>.active>a:focus,.no-touch .dashboard-page nav.dashboard-menu ul li:hover a,.dashboard-page nav.dashboard-menu ul li.active a {
    color:#fff;
    background-color:#5fa2db;
  }
  .area {
    float: left;
    background: #e2e2e2;
    width: 100%;
    height: 100%;
  }
  .section {
    background-color: #141414;
  }
  @font-face {
    font-family: 'Titillium Web';
    font-style: normal;
    font-weight: 300;
    src: local('Titillium WebLight'), local('TitilliumWeb-Light'), url(http://themes.googleusercontent.com/static/fonts/titilliumweb/v2/anMUvcNT0H1YN4FII8wpr24bNCNEoFTpS2BTjF6FB5E.woff) format('woff');
  }

</style>
