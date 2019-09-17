<template>
  <div class="base-layout">
    <div class="header" :style="headerStyle">
      <slot name="header"></slot>
    </div>
    <div class="middle">
      <div ref="leftPanel" class="left-panel" :style="leftPanelStyle">
        <slot name="left-panel"></slot>
      </div>
      <div class="main">
        <div class="main-header">
          <slot name="breadcrumb"></slot>
        </div>
        <div class="main-content">
          <div class="module-container">
            <!-- <el-scrollbar> -->
            <transition name="fade-transform" mode="out-in">
              <slot name="main"></slot>
            </transition>
            <!-- </el-scrollbar> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'BaseLayout',
  props: {
    // 头部
    headerStyle: {
      type: Object,
      default () {
        return { height: '80px' }
      }
    },
    // 左侧面板
    leftPanelStyle: {
      type: Object,
      default () {
        return { width: '200px' }
      }
    }
  }
}

</script>
<style lang="scss">
.base-layout {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  .middle {
    flex: 1;
    overflow: auto;
    display: flex;
    .main {
      flex: 1;
      display: flex;
      flex-direction: column;
      overflow: hidden;
      padding: 0 20px 20px 20px;
      background-color: #ececec;
      .main-header {
        height: 50px;
        z-index: 0;
        display: flex;
        line-height: 40px;
        justify-content: space-between;
      }
      .main-content {
        flex: 1;
        z-index: 0;
        overflow: auto;
        // .el-scrollbar{
        //   height: 100%;
        // }
        // .el-scrollbar__wrap{
        //   overflow:auto;
        // }
        .module-container {
          height: 100%;
        }
      }
    }
  }
}
</style>
