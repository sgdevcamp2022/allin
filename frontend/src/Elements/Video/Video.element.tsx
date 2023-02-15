import { useEffect, useRef } from 'react'
import ChannelElement from '../Channal/Channal.element'
const VideoElement = () => {
  const videoRef = useRef(null)

  useEffect(() => {
    // const video = videoRef.current as HTMLVideoElement
    // const videoSrc = 'http://192.168.0.12:8088/hls/a53d70e0b32bdcb1fc3a674773dd2674_low/index.m3u8'
    // if (video == undefined) return
    // if (Hls.isSupported()) {
    //   const hls = new Hls()
    //   hls.loadSource(videoSrc)
    //   hls.attachMedia(video)
    //   hls.on(Hls.Events.MANIFEST_PARSED, function () {
    //     video.muted = true
    //     video.autoplay = true
    //     video.playsInline = true
    //     video.play()
    //   })
    // } else if (video.canPlayType('application/vnd.apple.mpegurl')) {
    //   video.src = videoSrc
    //   video.addEventListener('loadedmetadata', function () {
    //     video.muted = true
    //     video.autoplay = true
    //     video.playsInline = true
    //     video.play()
    //   })
    // }
  })

  return (
    <div className="pr-8">
      {/* <video ref={videoRef}></video> */}
      <img src="http://via.placeholder.com/1920x1080" alt="" />
      <ChannelElement />
    </div>
  )
}

export default VideoElement
