const fullScreen = {
  install (Vue, options) {
    Vue.prototype.$toggleFullScreen = el => {
      const enterFullScreen = el => {
        let rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen
        let wscript

        if (typeof rfs !== 'undefined' && rfs) {
          rfs.call(el)
          return
        }

        if (typeof window.ActiveXObject !== 'undefined') {
          wscript = new window.ActiveXObject('WScript.Shell')
          if (wscript) {
            wscript.SendKeys('{F11}')
          }
        }
      }
      const exitFullscreen = () => {
        if (document.exitFullscreen) {
          document.exitFullscreen()
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen()
        } else if (document.webkitExitFullscreen) {
          document.webkitExitFullscreen()
        }
      }
      if (document.fullscreen ||
        document.mozFullScreen ||
        document.fullscreenElement ||
        document.msFullscreenElement ||
        document.webkitIsFullScreen) {
        exitFullscreen()
      } else {
        enterFullScreen(el)
      }
    }
  }
}
export default fullScreen
