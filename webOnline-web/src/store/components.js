/**
 * 异步组件库
 */
export default {
  namespaced: true,
  state: {
    asyncComponents: {
      'report-sale-condition': () =>
        import('@/views/report-sale-condition' /* webpackChunkName: "report-sale-condition" */)
    }
  }
}
