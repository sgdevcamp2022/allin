import { ChangeEvent, useState } from 'react'
import { signInIdTextState, signInPwTextState } from '../../../Atoms/Sign/SignIn.atom'
import { useRecoilValue } from 'recoil'
import InputForm from '../../../Elements/Input/InputForm.element'
import HomeButtonElement from '../../../Elements/Button/HomeButton.element'
import { signInAxios } from '../sign.axios'

const SignInDesktopPage = () => {
  const signInId = useRecoilValue(signInIdTextState)
  const signInPw = useRecoilValue(signInPwTextState)

  const [isAutoLogin, setIsAutoLogin] = useState(false)
  const [isWrongLogin, setIsWrongLogin] = useState(false)
  const changeAutoLogin = (e: ChangeEvent<HTMLInputElement>) => {
    setIsAutoLogin(e.target.checked)
  }

  const goLogin = () => {
    signInAxios(signInId, signInPw, setIsWrongLogin, isAutoLogin)
  }

  return (
    <div className="w-screen h-screen flex flex-col justify-center items-center">
      <HomeButtonElement />
      <div className="min-w-[32rem] w-[40%] border-border px-12 py-10 border-[1px] rounded-xl mt-8">
        <InputForm
          textState={signInIdTextState}
          formTitle="아이디"
          className="flex my-4 justify-between text-xl items-center"
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          placeholder="example@smilegate.com"
        />
        <InputForm
          textState={signInPwTextState}
          formTitle="비밀번호"
          className="flex my-4 justify-between text-xl items-center"
          inputTextClassName="w-[75%] text-base p-2 rounded-sm"
          type="password"
        />
        <div className="pl-[25%] flex justify-between">
          <div className="flex items-center">
            <input
              type="checkbox"
              name="autoLogin"
              id="autoLogin"
              className="accent-point"
              checked={isAutoLogin}
              onChange={changeAutoLogin}
            />
            <label htmlFor="autoLogin" className="pl-2 text-subText">
              로그인 상태 유지
            </label>
          </div>
          <a className="text-warningText" href="/find-password">
            비밀번호를 잊으셨나요?
          </a>
        </div>
        <div className="text-warningText pl-[25%] mt-4 h-6 text-sm">
          {isWrongLogin ? '아이디 또는 비밀번호가 틀렸습니다.' : ''}
        </div>
        <button
          className="bg-point text-white rounded-sm py-2 px-16 mt-4 ml-[25%]"
          onClick={goLogin}
        >
          로그인
        </button>
        <div className="border-b-[1px] border-border pt-12"></div>
        <a className="text-subText mt-6 block" href="/sign-up">
          이미 회원가입 하지 않으셨나요?
          <span className="text-point font-semibold ml-2">회원가입</span>
        </a>
      </div>
    </div>
  )
}

export default SignInDesktopPage
