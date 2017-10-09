<template>
  <li>
    <a href="#">
      <span class="nav-text">
        <b>RAW Flash</b>
      </span>
    </a>
  </li>
</template>

<script>
export default {
  name: 'sbbtn',
  components: {

  },
  props: ['text'],
  methods: {
    content() {
      return this.text;
    }
  }
}
</script>

<style>

@import url(//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css);
@import url(https://fonts.googleapis.com/css?family=Titillium+Web:300);
  .fa-2x {
    font-size: 2em;
  }
  .fa {
    position: relative;
    display: table-cell;
    width: 60px;
    height: 36px;
    text-align: center;
    vertical-align: middle;
    font-size:20px;
  }

  .main-menu>ul {
    margin:7px 0;
  }

  .main-menu li {
    position:relative;
    display:block;
    width:250px;
  }

  .main-menu li>a {
    position:relative;
    display:table;
    border-collapse:collapse;
    border-spacing:0;
    color:#999;
     font-family: arial;
    font-size: 14px;
    text-decoration:none;
    -webkit-transform:translateZ(0) scale(1,1);
    -webkit-transition:all .1s linear;
    transition:all .1s linear;

  }

  .main-menu .nav-icon {
    position:relative;
    display:table-cell;
    width:60px;
    height:36px;
    text-align:center;
    vertical-align:middle;
    font-size:18px;
  }

  .main-menu .nav-text {
    position:relative;
    display:table-cell;
    vertical-align:middle;
    width:190px;
    font-family: 'Titillium Web', sans-serif;
  }

</style>
