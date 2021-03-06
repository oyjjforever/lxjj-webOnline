export default {
  globalAxiosOptions: {
    baseURL: process.env.VUE_APP_BASE_URL
  },
  api: [{
    name: '通用查询接口',
    method: 'queryData',
    url: '/api/v1/basic/queryData',
    type: 'get'
  }, {
    name: '通用分页查询接口',
    method: 'pagingData',
    url: '/api/v1/basic/pagingData',
    type: 'get'
  }, {
    name: '用户登录',
    method: 'login',
    url: '/api/v1/user/login',
    type: 'post'
  }, {
    name: '菜单接口',
    method: 'menu',
    url: '/api/v1/basic/queryData?mapperId=com.lxjj.wo.dao.MenuMapper.userMenu'
  }, {
    name: '硬件信息接口',
    method: 'hardware',
    url: '/api/v1/sysMonitor/showHardwareInfo'
  }, {
    name: '告警接口',
    method: 'warn',
    url: '/api/v1/showUnprocessedAlarmNum'
  }, {
    name: '最新告警接口',
    method: 'lastWarn',
    url: '/api/v1/showLastAlarm'
  }, {
    name: '表空间接口',
    method: 'tableSpace',
    url: '/api/v1/showTableSpace'
  }, {
    name: '端口连通性',
    method: 'portConnection',
    url: '/api/v1/showPortConn'
  }, {
    name: '埋点数据次数、金额统计',
    method: 'buriedPointData',
    url: '/queryCountAndAmount'
  }]
}
