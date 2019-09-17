<template>
  <div class="base-breadcrumb">
    当前位置：
    <el-breadcrumb separator="/">
      <transition-group name="breadcrumb">
        <el-breadcrumb-item v-for="item in ($route.meta.menu || {}).siteMap" :key="item">{{item}}</el-breadcrumb-item>
      </transition-group>
    </el-breadcrumb>
  </div>
</template>
<script>
export default {
  name: 'BaseBreadcrumb',
  props: {
    siteMap: {
      type: String
    }
  }
}

</script>
<style lang="scss">
.base-breadcrumb {
  font-size: 14px;
  display: flex;
  align-items: center;
  .el-breadcrumb__item:last-child .el-breadcrumb__inner {
    font-size: 14px;
  }
}
</style>
