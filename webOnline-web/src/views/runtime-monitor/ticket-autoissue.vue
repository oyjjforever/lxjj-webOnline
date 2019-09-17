<template>
  <div class="ticket-autoissue">
    <div class="number-card">
      <div
        style="flex: 1;background-color: #F1483B;cursor:pointer;" @click="showIssueNumTab = false,showFailReasonTab = true,resizeFailReasonChart()"
      >
        <i class="fa fa-3x fa-warning"></i>
        <div class="right" style="flex-direction: row;">
          <div style="flex:1;display: flex;flex-direction: column;justify-content: center;">
            <h2>
              <span class="unit">紧急</span>
            </h2>
          </div>
          <div style="flex: 5;margin: auto 5px;overflow: hidden;display: flex;">
            <span>{{emergencyWarning.data}}</span>
          </div>
        </div>
      </div>
      <div style="width: 20px;"></div>
      <div
        style="flex: 1;background-color: #1891FF;"
      >
        <i class="fa fa-3x fa-warning"></i>
        <div class="right" style="flex-direction: row;">
          <div style="flex: 1;display: flex;flex-direction: column;justify-content: center;">
            <h2>
              <span class="unit">重要</span>
            </h2>
          </div>
          <div style="flex: 6;margin: 20px 10px 20px 0;overflow: hidden;">
            <vue-seamless-scroll :data="importantWarning.data" :class-option="{step: 0.5, limitMoveNum: 3}">
              <ul>
                <li
                  v-for="(item,index) in importantWarning.data"
                  :key="index"
                  style="display:flex;border-bottom:1px dashed #ebeef5;padding: 0 0 5px 0;margin: 0 0 5px 0;"
                >
                  <span style="display: inline-block;">{{(index+1)}}、</span>
                  <template>
                    <span>{{item}}</span>
                  </template>
                </li>
              </ul>
            </vue-seamless-scroll>
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
              <span class="unit">一般</span>
            </h2>
          </div>
          <div style="flex:5;margin: auto 5px;overflow: hidden;">
            <span>{{generalWarning.data}}</span>
          </div>
        </div>
      </div>
    </div>
    <div style="height: 20px;"></div>
    <el-card class="table-card">
      <div slot="header">
        <span class="arrow"></span>
        <span class="card-title">自动发放情况监控</span>
      </div>
      <el-row style="padding-top: 10px">
        <el-col :span="2">
          <div class="issue-condition-card" @click="showIssueNumTab = true,showFailReasonTab = false,resizeIssueNumByTimeChart()">
            <span class="indication">当日发放份数</span>
            <h3>
              <count-to :endVal="issueConditionCard.todayIssueNum"></count-to>
            </h3>
          </div>
          <div class="issue-condition-card" @click="showIssueNumTab = true,showFailReasonTab = false,resizeIssueNumByTimeChart()">
            <span class="indication">发放成功次数</span>
            <h3>
              <count-to :endVal="issueConditionCard.successIssueNum"></count-to>
            </h3>
          </div>
          <div class="issue-condition-card" @click="showIssueNumTab = true,showFailReasonTab = false,resizeIssueNumByTimeChart()">
            <span class="indication">发放平均耗时</span>
            <h3>
              <count-to :endVal="issueConditionCard.issueAverageTime"></count-to>ms
            </h3>
          </div>
          <div class="issue-condition-card" @click="showIssueNumTab = false,showFailReasonTab = true,resizeFailReasonChart()">
            <span class="indication">发放失败次数</span>
            <h3>
              <count-to :endVal="issueConditionCard.failIssueNum"></count-to>
            </h3>
          </div>
        </el-col>
        <el-col :span="22">
          <div v-show="showFailReasonTab">
            <el-col :span="18">
              <el-button plain>导出</el-button>
              <span style="float: right">显示时间
                <el-select v-model="showTimeType" style="width:100px">
                  <el-option v-for="item in options"
                             :key="item.value"
                             :label="item.label"
                             :value="item.value"
                  >
                  </el-option>
                </el-select>
              </span>
              <div>
                <el-table
                  :data="tableData.data"
                  :header-cell-style="{background:'#1891FF',color:'#FFFFFF'}"
                  size="small"
                  border
                  stripe
                  highlight-current-row
                  height="310px">
                  <el-table-column prop="no" label="序号" width="50"></el-table-column>
                  <el-table-column prop="time" label="下发时间" align="center"></el-table-column>
                  <el-table-column prop="reason" label="失败原因" align="center"></el-table-column>
                  <el-table-column prop="org" label="用票机构" align="center"></el-table-column>
                </el-table>
              </div>
            </el-col>
            <el-col :span="6">
              <div id="failReasonChart" style="height:343px;"></div>
            </el-col>
          </div>

          <div v-show="showIssueNumTab">
            <div id="issueNumByTimeChart" style="width:100%;height:343px;"></div>
          </div>
        </el-col>
      </el-row>
    </el-card>
    <div style="height: 20px;"></div>
    <el-card class="table-card">
      <div slot="header">
        <span class="arrow"></span>
        <span class="card-title">自动发放月TOP10</span>
      </div>
      <div>
        <el-row style="background-color:rgba(251,212,25,0.1); padding: 3px">
          <span>提示：高于平均发放次数的用票机构，建议调整其自动申领阈值</span>
        </el-row>
        <el-row style="padding-top: 10px">
          <el-col :span="14">
            <div id="issueNumByOrgChart" style="height: 400px"></div>
          </el-col>
          <el-col :span="10">
            <div id="issueNumByBillChart" style="height: 400px"></div>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import vueSeamlessScroll from 'vue-seamless-scroll'
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
  name: 'ticket-autoissue',
  components: {
    CountTo,
    vueSeamlessScroll
  },
  data () {
    return {
      chartInstances: {},
      intervalInstance: {},
      showFailReasonTab: true,
      showIssueNumTab: false,
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
      },
      issueConditionCard: {
        todayIssueNum: 3000, // 当日发放份数
        successIssueNum: 413, // 发放成功次数
        issueAverageTime: 20, // 下发平均耗时
        failIssueNum: 12 // 发放失败次数
      },
      showTimeType: '', // 显示时间类型
      options: [
        {
          value: 0,
          label: '当日'
        }, {
          value: 1,
          label: '七日'
        }
      ],
      tableData: { // 下发失败原因表格 数据
        data: []
      }
    }
  },
  mounted () {
    this.initIssueNumByTimeChart()
    this.initFailReasonChart()
    this.initIssueNumByOrgChart()
    this.initIssueNumByBillChart()
    this.addPolling()
    this.bindEventListener()
  },
  beforeDestroy () {
    this.clearPolling()
    this.unbindEventListener()
  },
  methods: {
    /**
     * 获取 紧急预警 数据
     */
    async fetchEmergencyWarning () {
      let { data } = await this.$api.queryData({
        params: {
          mapperId: 'com.bosssoft.hbms.dao.xxx.xxx'
        }
      })
      this.emergencyWarning.data = data
    },
    /**
     * 获取 重要预警 数据
     */
    async fetchImportantWarning () {
      let { data } = await this.$api.queryData({
        params: {
          mapperId: 'com.bosssoft.hbms.dao.xxx.xxx'
        }
      })
      this.importantWarning.data = data
    },
    /**
     * 获取 一般预警 数据
     */
    async fetchGeneralWarning () {
      let { data } = await this.$api.queryData({
        params: {
          mapperId: 'com.bosssoft.hbms.dao.xxx.xxx'
        }
      })
      this.generalWarning.data = data
    },
    /**
     * 获取 自动发放情况监控 指标卡片 数据
     */
    async fetchIssueConditionCardData () {
      let { data } = await this.$api.queryData({
        params: {
          mapperId: 'com.bosssoft.hbms.dao.xxx.xxx'
        }
      })
      this.issueConditionCard.todayIssueNum = data
      this.issueConditionCard.successIssueNum = data
      this.issueConditionCard.issueAverageTime = data
      this.issueConditionCard.failIssueNum = data
    },
    /**
     * 初始化 各时间段下发数量 统计图表
     */
    initIssueNumByTimeChart () {
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
      let chart = echarts.init(this.$el.querySelector('#issueNumByTimeChart'))
      chart.setOption(option)
      this.chartInstances['issueNumByTimeChart'] = chart
      this.setIssueNumByTimeChartData()
    },
    /**
     * 设置 各时间段下发数量 统计图表 数据
     */
    setIssueNumByTimeChartData () {
      let chart = this.chartInstances['issueNumByTimeChart']
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
    },
    /**
     * 自适应 各时间段下发数量 统计图表 数据
     */
    resizeIssueNumByTimeChart () {
      setTimeout(() => {
        let chart = this.chartInstances['issueNumByTimeChart']
        chart.resize()
      })
    },
    /**
     * 初始化 自动下发失败原因 统计图表
     */
    initFailReasonChart () {
      let option = {
        title: {
          text: '自动下发失败原因',
          left: 'center'
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
            type: 'category'
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '数量',
            type: 'pictorialBar',
            symbol: 'rect',
            symbolRepeat: true,
            symbolSize: ['100%', 8],
            barWidth: 50, // 柱图宽度
            color: '#fba225',
            itemStyle: {// 上方显示数值
              normal: {
                label: {
                  show: true, // 开启显示
                  position: 'top', // 在上方显示
                  textStyle: { // 数值样式
                    color: 'black',
                    fontSize: 14
                  }
                }
              }
            }
          }
        ],
        grid: {
          y2: 20
        }
      }
      let chart = echarts.init(this.$el.querySelector('#failReasonChart'))
      chart.setOption(option)
      this.chartInstances['failReasonChart'] = chart
      this.setFailReasonChartData()
    },
    /**
     * 设置 自动下发失败原因 统计图表 数据
     */
    setFailReasonChartData () {
      let chart = this.chartInstances['failReasonChart']
      chart.setOption({
        xAxis: {
          data: ['库存不足', '系统异常']
        },
        series: {
          data: [ 10.0, 14.9 ]
        }
      })
    },
    /**
     * 自适应 自动下发失败原因 表格
     */
    resizeFailReasonChart () {
      setTimeout(() => {
        let chart = this.chartInstances['failReasonChart']
        chart.resize()
      })
    },
    /**
     * 设置 自动下发失败原因 表格
     */
    setFailReasonTableData () {
      this.tableData.data = [{
        no: 1,
        time: '2019',
        reason: '555',
        org: 'bs'
      }]
    },
    /**
     * 初始化 各票据下发数量 统计图表
     */
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
      let chart = echarts.init(this.$el.querySelector('#issueNumByBillChart'))
      chart.setOption(option)
      this.chartInstances['issueNumByBillChart'] = chart
      this.setIssueNumByBillChartData()
    },
    /**
     * 设置 各票据下发数量 统计图表 数据
     */
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
      this.chartInstances['issueNumByBillChart'].setOption({
        legend: {
          data: orgBill.legendData
        },
        series: {
          data: orgBill.data
        }
      })
    },
    /**
     * 初始化 各机构下发数量 统计图表
     */
    initIssueNumByOrgChart () {
      let option = {
        legend: {
          data: ['票据下发次数']
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
            axisLabel: {
              rotate: '45'
            }
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '票据下发次数',
            type: 'bar',
            color: '#2045B3',
            markLine: {
              symbol: 'none',
              silent: true, // 不响应鼠标事件
              label: {
                formatter: '{b}: {c}'
              },
              data: [
                {
                  type: 'average',
                  name: '平均值',
                  lineStyle: {
                    color: 'green',
                    type: 'solid'
                  }
                },
                {
                  name: '预警值',
                  yAxis: 100,
                  lineStyle: {
                    color: 'red'
                  }
                }
              ]
            }
          }
        ],
        grid: {
          x: 50, // 左边界距离
          y2: 100
        }
      }
      let chart = echarts.init(this.$el.querySelector('#issueNumByOrgChart'))
      chart.setOption(option)
      this.chartInstances['issueNumByOrgChart'] = chart
      this.setIssueNumByOrgChartData()
    },
    /**
     * 设置 各机构下发数量 统计图表 数据
     */
    setIssueNumByOrgChartData () {
      this.chartInstances['issueNumByOrgChart'].setOption({
        xAxis: {
          data: ['0268 单位1 ', '0267 医院2', '0266 海事局', '0265 吉林大学', '0264 浙江大学', '0263 专利局', '0259 医院5', '0261 医院6', '0258 医院4', '0102 单位4']
        },
        series: {
          data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0]
        }
      })
    },
    /**
     * 统计图表 大小 自适应
     */
    resize () {
      Object.values(this.chartInstances).forEach(chart => chart.resize())
    },
    /**
     * 刷新 数据
     */
    reflashData () {
      this.setIssueNumByBillChartData()
      this.setFailReasonChartData()
      this.setFailReasonTableData()
      this.setIssueNumByOrgChartData()
      this.setIssueNumByTimeChartData()
    },
    /**
     * 添加 轮询
     */
    addPolling () {
      this.intervalInstance['reflashData'] = setInterval(this.reflashData, 30 * 1000)
    },
    /**
     * 清除 轮询
     */
    clearPolling () {
      Object.values(this.intervalInstance).forEach(interval => clearInterval(interval))
    },
    /**
     * 添加 事件监听
     */
    bindEventListener () {
      window.addEventListener('resize', this.resize)
      // 机构下发数量图表阴影部分的点击操作，用于联动票据饼状图
      this.chartInstances['issueNumByOrgChart'].getZr().on('click', params => {
        const pointInPixel = [params.offsetX, params.offsetY]
        if (this.chartInstances['issueNumByOrgChart'].containPixel('grid', pointInPixel)) {
          let xIndex = this.chartInstances['issueNumByOrgChart'].convertFromPixel({ seriesIndex: 0 }, [params.offsetX, params.offsetY])[0]
          this.setIssueNumByBillChartData(xIndex) // 更新票据饼状图数据
        }
      })
    },
    /**
     * 清除 事件监听
     */
    unbindEventListener () {
      window.removeEventListener('resize', this.resize)
    }
  }
}
</script>

<style lang="scss" scoped>
.issue-condition-card {
    background-color: #1891FF;
    height: 70px;
    color: white;
    cursor:pointer;
    padding: 5% 0px 0px 10%;
    margin: 5px 7px 0 0;
   .indication {
    font-size: 14px;
  }
}
</style>
