<template>
  <div id="app">
 <calc/>
  <calc_view/>
  </div>
 

</template>

<script>
import calc from './components/calc.vue'
import calc_view from './components/calc_view.vue'
export default {
  name: 'App',
  components: {
    calc,
    calc_view
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 30px;
}
</style>
