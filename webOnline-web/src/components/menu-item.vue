<template>
  <el-menu-item
    class="menu-item"
    :class="{'collapse-menu': menu.level === 1 && collapse}"
    v-if="!menu.routeConfig.hidden && (!menu.childMenu || menu.childMenu.length === 0)"
    :index="menu.code"
    :key="menu.code"
    v-touch-ripple
  >
    <i v-if="menu.icon ||  menu.level === 1" :class="menu.icon || 'el-icon-menu'">
      <div v-if="menu.level === 1 && collapse" class="menu-title">{{menu.name}}</div>
    </i>
    <template v-if="menu.level !== 1 && !menu.icon">
      &nbsp;
    </template>
    <span slot="title">{{menu.name}}</span>
  </el-menu-item>
  <el-submenu
    class="menu-item"
     :class="{'collapse-menu': menu.level === 1 && collapse}"
    v-else-if="!menu.routeConfig.hidden "
    :index="menu.code"
    :key="menu.code"
  >
    <template slot="title">
      <i v-if="menu.icon ||  menu.level === 1" :class="menu.icon || 'el-icon-menu'">
        <div v-if="menu.level === 1 && collapse" class="menu-title">{{menu.name}}</div>
      </i>
      <span slot="title">{{menu.name}}</span>
    </template>
    <menu-item v-for="item in menu.childMenu" :key="item.code" :menu="item"></menu-item>
  </el-submenu>
</template>

<script>
export default {
  name: 'MenuItem',
  props: {
    menu: {
      type: Object,
      required: true
    },
    collapse: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style lang="scss" scoped>
.menu-item {
  &.collapse-menu {
    height: 64px;
    line-height: 64px;
    /deep/ .el-submenu__title {
      height: 64px;
      line-height: 64px;
    }
  }
  .menu-title {
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    font-size: 12px;
    width: 50px;
    position: relative;
    right: 7px;
    top: -6px;
    color: #f8f8f8;
  }
}
</style>
