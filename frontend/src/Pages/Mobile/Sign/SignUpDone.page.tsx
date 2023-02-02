import { useEffect } from 'react'
import checkAnimationData from '../../../assets/black_check.json'
import Lottie from 'lottie-web'

const SignUpDonePage = () => {
  let isDone = false
  const done = () => {
    if (!isDone) document.querySelectorAll('svg')[0].remove()
    isDone = true
  }

  useEffect(() => {
    const check = document.querySelector('#check')
    Lottie.loadAnimation({
      container: check!,
      renderer: 'svg',
      loop: true,
      autoplay: true,
      animationData: checkAnimationData,
    })
    done()
  }, [])

  return <div id="check" className="mb-[22px] w-[78px]"></div>
}

export default SignUpDonePage
