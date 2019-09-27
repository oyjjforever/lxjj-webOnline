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
            <span></span>
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
            <span></span>
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
            <span></span>
          </div>
        </div>
      </div>
    </div>
    <el-card>
      <div slot="header">
        <span class="arrow"></span>
        <span class="card-title">
          xxx
          <el-dropdown size="medium" @command="switchServer" style="float: right;">
            <span style="cursor: pointer;color: #409EFF;font-size: 20px;">
              <span style="font-size: 20px;font-weight: bold;color: #4c4c4c;">服务器：</span>
              <i
                class="el-icon-arrow-down el-icon--right"
              ></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>1</el-dropdown-item>
              <el-dropdown-item>2</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </span>
      </div>
      <el-row style="padding-top: 10px" >
        <el-carousel indicator-position="outside" height="400px" :autoplay="false">
          <el-carousel-item key="1" label="商品">
            <div id="saleConditionStatement1" style="height:400px;width: 1000px">
              <span>123</span>
            </div>
          </el-carousel-item>
          <el-carousel-item key="2"  label="品牌">
            <div id="saleConditionStatement2" style="height:400px;width: 1000px">
              <span>123</span>
            </div>
          </el-carousel-item>
          <el-carousel-item key="3"  label="类型">
            <div id="saleConditionStatement3" style="height:400px;width: 1000px">
              <span>123</span>
            </div>
          </el-carousel-item>
        </el-carousel>
      </el-row>
    </el-card>
    <el-card>
      <div slot="header">
        <span class="arrow"></span>
        <span class="card-title">xxx</span>
      </div>
      <el-row style="padding-top: 10px">
        <el-col :span="12">
          <div id="monthStatement" style="height:400px"></div>
        </el-col>
        <el-col :span="12">
          <div id="annualStatement" style="height:400px"></div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import CountTo from 'vue-count-to'
const echarts = require('echarts/lib/echarts')
require('echarts/lib/component/tooltip')
require('echarts/lib/component/legend')
require('echarts/lib/component/title')
require('echarts/lib/component/visualMap')
require('echarts/lib/chart/pie')
require('echarts/lib/chart/line')
require('echarts/lib/chart/bar')
require('echarts/lib/chart/pictorialBar')
require('echarts/lib/component/markLine')
export default {
  name: 'report-sale-condition',
  comments: {
    CountTo
  },
  data () {
    return {
      chartInstances: {},
      cardData: {

      }
    }
  },
  mounted () {
    this.initIssueNumByBillChart()
  },
  methods: {
    initIssueNumByBillChart () {
      let option = {
        legend: {
          bottom: 10,
          data: ['下发数量', '下发平均耗时']
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        calculable: true,
        xAxis: [
          {
            type: 'category',
            data: ['8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00', '00:00', '1:00', '2:00', '3:00', '4:00', '5:00', '6:00', '7:00']
          }
        ],
        yAxis: [
          {
            type: 'value',
            name: '下发数量',
            min: 0,
            max: 180,
            interval: 30,
            axisLabel: {
              formatter: '{value}'
            }
          },
          {
            type: 'value',
            name: '平均耗时',
            min: 0,
            max: 30,
            interval: 5,
            axisLabel: {
              formatter: '{value} ms'
            }
          }
        ],
        series: [
          {
            name: '下发数量',
            type: 'bar',
            color: '#2045B3'
          },
          {
            name: '下发平均耗时',
            type: 'line',
            color: '#397ed8',
            yAxisIndex: 1
          }
        ],
        grid: {
          x: 50, // 左边界距离
          x2: 50, // 右边界距离
          y: 40
        }
      }
      let chart1 = echarts.init(this.$el.querySelector('#saleConditionStatement1'))
      chart1.setOption(option)
      this.chartInstances['saleConditionStatement1'] = chart1
      let chart2 = echarts.init(this.$el.querySelector('#saleConditionStatement2'))
      chart2.setOption(option)
      this.chartInstances['saleConditionStatement2'] = chart2
      let chart3 = echarts.init(this.$el.querySelector('#saleConditionStatement3'))
      chart3.setOption(option)
      this.chartInstances['saleConditionStatement3'] = chart3
      this.setIssueNumByBillChartData()
    },
    setIssueNumByBillChartData (orgName) {
      let chart = this.chartInstances['saleConditionStatement1']
      chart.setOption({
        series: [
          {
            data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
          },
          {
            data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
          }
        ]
      })
    }
  }
}
</script>

<style scoped>

</style>
