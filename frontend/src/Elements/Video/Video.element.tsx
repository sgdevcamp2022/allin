import ChannelElement from '../Channal/Channal.element'

const VideoElement = () => {
  // var video = document.getElementById('video');
  // var videoSrc = 'http://192.168.0.12:8088/hls/a53d70e0b32bdcb1fc3a674773dd2674_low/index.m3u8';
  // if (Hls.isSupported()) {
  //     var hls = new Hls();
  //     hls.loadSource(videoSrc);
  //     hls.attachMedia(video);
  //     hls.on(Hls.Events.MANIFEST_PARSED, function () {
  //         video.muted = 'muted';
  //         video.autoplay = 'autoplay';
  //         video.playsinline = 'true';
  //         video.play();
  //     });
  // } else if (video.canPlayType('application/vnd.apple.mpegurl')) {
  //     video.src = videoSrc;
  //     video.addEventListener('loadedmetadata', function () {
  //         video.muted = 'muted';
  //         video.autoplay = 'autoplay';
  //         video.playsinline = 'true';
  //         video.play();
  //     });
  // }

  return (
    <div className="pr-8">
      <img src="http://via.placeholder.com/1920x1080" alt="" />
      <ChannelElement />
    </div>
  )
}

export default VideoElement
