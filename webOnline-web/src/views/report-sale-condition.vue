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
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} 份 ({d}%)'
        },
        legend: {
          orient: 'vertical',
          x: 'left'
        },
        calculable: true,
        series: [
          {
            name: '票据类型',
            type: 'pie',
            radius: '70%',
            center: ['50%', '60%'],
            label: {
              normal: {
                show: true,
                position: 'left',
                formatter: '{d}%',

                textStyle: {
                  align: 'center',
                  baseline: 'middle',
                  fontFamily: '微软雅黑',
                  fontSize: 15,
                  fontWeight: 'bolder'
                }
              }
            }
          }
        ]
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
      var orgBill = {}
      if (orgName === 6) {
        orgBill = {
          legendData: ['医疗门诊收费票据', '医疗住院收费票据', '捐赠票据', '单位资金往来数据'],
          data: [
            { value: 335, name: '医疗门诊收费票据' },
            { value: 310, name: '医疗住院收费票据' },
            { value: 234, name: '捐赠票据' },
            { value: 1548, name: '单位资金往来数据' }
          ]
        }
      } else if (orgName === 7) {
        orgBill = {
          legendData: ['医疗住院收费票据', '捐赠票据', '单位资金往来数据'],
          data: [
            { value: 1310, name: '医疗住院收费票据' },
            { value: 234, name: '捐赠票据' },
            { value: 546, name: '单位资金往来数据' }
          ]
        }
      } else {
        orgBill = {
          legendData: ['电子票据', '医疗票据', '医疗住院收费票据', '捐赠票据', '单位资金往来数据'],
          data: [
            { value: 810, name: '电子票据' },
            { value: 510, name: '医疗票据' },
            { value: 1310, name: '医疗住院收费票据' },
            { value: 234, name: '捐赠票据' },
            { value: 546, name: '单位资金往来数据' }
          ]
        }
      }
      this.chartInstances['saleConditionStatement1'].setOption({
        legend: {
          data: orgBill.legendData
        },
        series: {
          data: orgBill.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
