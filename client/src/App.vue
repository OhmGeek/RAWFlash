<template>
  <div id="app">
    <sidebar></sidebar>
    <workspace></workspace>
  </div>
</template>

<script>
import Sidebar from './components/Sidebar'
import Workspace from './components/Workspace'
import SidebarButton from './components/SidebarButton'
export default {
  name: 'app',
  components: {
    Sidebar, Workspace
  }
}
</script>

<style>
body {
  overflow: hidden;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  overflow: hidden;
}
</style>
