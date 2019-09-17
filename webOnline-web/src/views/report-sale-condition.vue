<template>
  <div class="report-sale-condition">
    <div class="number-card">
      <div
        style="flex: 1;background-color: #F1483B;cursor:pointer;" @click="showIssueNumTab = false,showFailReasonTab = true,resizeFailReasonChart()"
      >
        <i class="fa fa-3x fa-warning"></i>
        <div class="right" style="flex-direction: row;">
          <div style="flex:1;display: flex;flex-direction: column;justify-content: center;">
            <h2>
              <span class="unit">XX</span>
            </h2>
          </div>
          <div style="flex: 5;margin: auto 5px;overflow: hidden;display: flex;">
            <span>{{emergencyWarning.data}}</span>
          </div>
        </div>
      </div>
      <div style="width: 20px;"></div>
      <div
        style="flex: 1;background-color: #6f7278;cursor:pointer;" @click="showIssueNumTab = true,showFailReasonTab = false,resizeIssueNumByTimeChart()"
      >
        <i class="fa fa-3x fa-warning"></i>
        <div class="right" style="flex-direction: row;">
          <div style="flex:1;display: flex;flex-direction: column;justify-content: center;">
            <h2>
              <span class="unit">XX</span>
            </h2>
          </div>
          <div style="flex:5;margin: auto 5px;overflow: hidden;">
            <span>{{generalWarning.data}}</span>
          </div>
        </div>
      </div>
      <div style="width: 20px;"></div>
      <div
        style="flex: 1;background-color: #6f7278;cursor:pointer;" @click="showIssueNumTab = true,showFailReasonTab = false,resizeIssueNumByTimeChart()"
      >
        <i class="fa fa-3x fa-warning"></i>
        <div class="right" style="flex-direction: row;">
          <div style="flex:1;display: flex;flex-direction: column;justify-content: center;">
            <h2>
              <span class="unit">XX</span>
            </h2>
          </div>
          <div style="flex:5;margin: auto 5px;overflow: hidden;">
            <span>{{generalWarning.data}}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'report-sale-condition',
  data () {
    return {
      emergencyWarning: { //  紧急预警
        data: '当前存在2个用票机构自动发放失败！'
      },
      importantWarning: { // 重要预警
        data: [
          '发放失败次数占比总发放次数通过20%，请优化处理。',
          '当日下发次数超过半数申领机构，建议修改供票单位库存阀值。',
          '票据种类0101医疗门诊收入票据发放次数高于平均值的20%，请调整此票种类的自动申领阈值。',
          '日平均耗时超过2s，请优化处理。'
        ]
      },
      generalWarning: { // 一般预警
        data: 'AM8-9点发放平均耗时高于日平均耗时的20%。'
      }
    }
  }
}
</script>

<style scoped>

</style>
