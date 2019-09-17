// 路由配置
export default [{
  path: '/',
  redirect: { name: 'login' }
}, {
  path: '/login/:module?',
  name: 'login',
  component: () => import('@/views/base/base-login' /* webpackChunkName: "base-login" */)
}]
