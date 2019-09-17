<template>
  <div id="app">
    <!-- 未登录 -->
    <router-view
      v-if="$route.name==='login' || $route.meta.needLogin === false"
      v-show="!$store.state.user.isLogining"
    />
    <!-- 已登录 -->
    <base-layout v-else>
      <base-navigation slot="header" />
      <base-breadcrumb slot="breadcrumb" />
      <base-menu
        slot="left-panel"
        :collapse="collapse"
        :menu-data="$store.getters['menu/treeMenu']"
        :default-active="$route.meta.menu && $route.meta.menu.code"
      />
      <router-view slot="main" :key="$route.path" />
    </base-layout>
  </div>
</template>
<script>
import {
  BaseMenu,
  BaseLayout,
  BaseBreadcrumb,
  BaseNavigation
}
  from '@/views/base'
export default {
  components: {
    BaseMenu,
    BaseLayout,
    BaseBreadcrumb,
    BaseNavigation
  },
  data () {
    return {
      collapse: false
    }
  }
}
</script>
<style lang="scss">
#app {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
</style>
