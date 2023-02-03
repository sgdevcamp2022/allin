import { useEffect } from 'react'
import checkAnimationData from '../../../assets/black_check.json'
import Lottie from 'lottie-web'
import HomeButtonElement from '../../../Elements/Button/HomeButton.element'

const SignUpDoneDesktopPage = () => {
  let isLottieDone = false
  const lottieDone = () => {
    if (!isLottieDone) document.querySelectorAll('svg')[0].remove()
    isLottieDone = true
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
    lottieDone()
  }, [])

  return (
    <div className="w-screen h-screen flex flex-col  items-center">
      <HomeButtonElement className="p-16 mt-28" />
      <div id="check" className="w-40 m-12"></div>
      <div className="text-2xl">회원가입을 해주셔서 감사합니다!</div>
      <button
        className="py-2 px-12 bg-point text-white rounded-sm mt-12"
        onClick={() => {
          location.href = '/sign-in'
        }}
      >
        로그인하기
      </button>
    </div>
  )
}

export default SignUpDoneDesktopPage
